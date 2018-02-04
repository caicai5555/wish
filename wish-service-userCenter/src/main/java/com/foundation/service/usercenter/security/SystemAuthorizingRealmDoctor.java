package com.foundation.service.usercenter.security;

import com.foundation.cache.redis.JedisTemplate;
import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.cache.CacheUtils;
import com.foundation.common.config.Global;
import com.foundation.common.io.SerializeUtil;
import com.foundation.common.security.PwdSha1Util;
import com.foundation.common.servlet.ValidateCodeServlet;
import com.foundation.common.utils.Encodes;
import com.foundation.common.utils.SpringContextHolder;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.dao.security.Principal;
import com.foundation.service.usercenter.LogUtils;
import com.foundation.service.usercenter.UserUtils;
import com.foundation.service.usercenter.service.impl.SystemServiceDoctorImpl;
import com.foundation.service.usercenter.service.impl.SystemServiceImpl;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 系统安全认证实现类
 * @author fqh
 * @version 2016-7-5
 */
@Service
public class SystemAuthorizingRealmDoctor extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private SystemServiceDoctorImpl systemServiceDoctor;

	private static JedisTemplate template = RedisUtils.getTemplate();

	private static String redisName = "shiro_redis_userRedis";

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		int activeSessionSize = getSystemService().getSessionDao().getActiveSessions(false).size();
		if (logger.isDebugEnabled()){
			logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());
		}

		// 校验登录验证码
		if (isValidateCodeLogin(token.getUsername(), true, false)){
			Session session = UserUtils.getSession();
			String code = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}

		// 校验用户名密码
		User user = getSystemService().getUserByLoginName(token.getUsername());
		if (user != null) {
//			if(null!=user.getUserType() && !(user.getUserType()==3)){
//				throw new AuthenticationException("msg:该系统只允许医生账户登录.");
//			}
			if (Global.NO.equals(user.getLoginFlag())){
				throw new AuthenticationException("msg:该已帐号禁止登录.");
			}
			byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
			return new SimpleAuthenticationInfo(new Principal(user, token.isMobileLogin()),
					user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
			Collection<Session> sessions = getSystemService().getSessionDao().getActiveSessions(true, principal, UserUtils.getSession());
			if (sessions.size() > 0){
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()){
					for (Session session : sessions){
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				else{
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
				}
			}
		}
		User user = getSystemService().getUserByLoginName(principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list){
				if (StringUtils.isNotBlank(menu.getPermission())){
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu.getPermission(),",")){
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()){
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals, Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * @param permission
	 */
	private void authorizationValidate(Permission permission){
		// 模块授权预留接口
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(PwdSha1Util.HASH_ALGORITHM);
		matcher.setHashIterations(PwdSha1Util.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

//	/**
//	 * 清空用户关联权限认证，待下次使用时重新加载
//	 */
//	public void clearCachedAuthorizationInfo(Principal principal) {
//		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
//		clearCachedAuthorizationInfo(principals);
//	}

	/**
	 * 清空所有关联认证
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
//		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
//		if (cache != null) {
//			for (Object key : cache.keys()) {
//				cache.remove(key);
//			}
//		}
	}

	/**
	 * 获取系统业务对象
	 */
	public SystemServiceDoctorImpl getSystemService() {
		if (systemServiceDoctor == null){
			systemServiceDoctor = SpringContextHolder.getBean(SystemServiceDoctorImpl.class);
		}
		return systemServiceDoctor;
	}

	/**
	 * 授权用户信息
	 *//*
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean mobileLogin; // 是否手机登录

//		private Map<String, Object> cacheMap;

		public Principal(User user, boolean mobileLogin) {
			this.id = user.getId();
			this.loginName = user.getLoginName();
			this.name = user.getName();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

		*//**
		 * 获取SESSIONID
		 *//*
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}

		@Override
		public String toString() {
			return id;
		}

	}*/

	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){

		//(User) SerializeUtil.toObject(template.hget(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key)))

//		Map<String, Integer> loginFailMap = (Map<String, Integer>) template.get("loginFailMap");

		Map<String, Integer> loginFailMap = (Map<String, Integer>) SerializeUtil.toObject(template.hget(SerializeUtil.getBytesKey(redisName), SerializeUtil.getBytesKey("loginFailMap")));
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
//			CacheUtils.put("loginFailMap", loginFailMap);
			template.hset(SerializeUtil.getBytesKey(redisName),SerializeUtil.getBytesKey("loginFailMap"), SerializeUtil.toBytes(loginFailMap));
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
			template.hset(SerializeUtil.getBytesKey(redisName),SerializeUtil.getBytesKey("loginFailMap"), SerializeUtil.toBytes(loginFailMap));
		}
		if (clean){
			if(null!=loginFailMap && loginFailMap.size()>0){
				loginFailMap.remove(useruame);
			}

			if (loginFailMap==null){
				loginFailMap = Maps.newHashMap();
			}
			template.hset(SerializeUtil.getBytesKey(redisName),SerializeUtil.getBytesKey("loginFailMap"), SerializeUtil.toBytes(loginFailMap));

		}
		return loginFailNum >= 3;
	}
}
