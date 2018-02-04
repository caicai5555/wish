package com.foundation.service.usercenter.service.impl;

import com.foundation.common.cache.CacheUtils;
import com.foundation.common.config.Global;
import com.foundation.common.persistence.Page;
import com.foundation.common.security.PwdSha1Util;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.entity.sys.Office;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.dao.modules.read.sys.MenuDaoR;
import com.foundation.dao.modules.read.sys.RoleDaoR;
import com.foundation.dao.modules.read.sys.UserDaoR;
import com.foundation.dao.modules.write.sys.MenuDao;
import com.foundation.dao.modules.write.sys.RoleDao;
import com.foundation.dao.modules.write.sys.UserDao;
import com.foundation.service.usercenter.LogUtils;
import com.foundation.service.usercenter.UserUtils;
import com.foundation.service.usercenter.common.shiro.session.SessionDAO;
import com.foundation.service.usercenter.security.Servlets;
import com.foundation.service.usercenter.security.SystemAuthorizingRealm;
import com.foundation.service.usercenter.security.SystemAuthorizingRealmDoctor;
import com.foundation.service.usercenter.service.ISystemService;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author fqh
 * @version 2013-12-05
 */
@Service("systemServiceDoctor")
@Transactional(readOnly = true)
public class SystemServiceDoctorImpl extends BaseServiceImpl implements InitializingBean {


	@Autowired(required = false)
	private UserDao userDao;

	@Autowired(required = false)
	private UserDaoR userDaoR;
	@Autowired(required = false)
	private RoleDao roleDao;
	@Autowired(required = false)
	private MenuDao menuDao;
	@Autowired(required = false)
	private SessionDAO sessionDao;
	@Autowired
	private SystemAuthorizingRealmDoctor systemRealmDoctor;

	@Autowired(required = false)
	private RoleDaoR roleDaoR;
	@Autowired(required = false)
	private MenuDaoR menuDaoR;

	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	//-- User Service --//

	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return UserUtils.get(id);
	}
	public User getUserInfoById(Map<String,Object> params){
		return userDaoR.getUserInfoById(params);
	}

	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName) {
		return UserUtils.getByLoginName(loginName);
	}

	public Page<User> findUser(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(userDaoR.findList(user));
		return page;
	}

	/**
	 * 无分页查询人员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		List<User> list = userDaoR.findList(user);
		return list;
	}

	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param officeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId) {
		List<User> list = (List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
		if (list == null){
			User user = new User();
			user.setOffice(new Office(officeId));
			list = userDaoR.findUserByOfficeId(user);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
		}
		return list;
	}

	@Transactional(readOnly = false)
	public void saveUser(User user) {
		if (StringUtils.isBlank(user.getId())){
			user.preInsert();
			userDao.insert(user);
		}else{
			// 清除原用户机构用户缓存
			User oldUser = userDaoR.get(user.getId());
			if(null==oldUser){
				return;
			}
			if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null){
				CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
			}
			// 更新用户数据
			user.preUpdate();
			userDao.update(user);
		}
		if (StringUtils.isNotBlank(user.getId())){
			// 更新用户与角色关联
			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0){
				try{
					userDao.insertUserRole(user);
				}catch (Exception e){
					throw new ServiceException(user.getLoginName() + "保存角色出错！",e);
				}

			}else{
				throw new ServiceException(user.getLoginName() + "没有设置角色！");
			}
			// 清除用户缓存
			UserUtils.clearCache(user);
//			// 清除权限缓存
//			systemRealm.clearAllCachedAuthorizationInfo();
		}
	}

	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		userDao.delete(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void updatePasswordById(String id, String loginName, String newPassword) {
		User user = new User(id);
		user.setPassword(PwdSha1Util.entryptPassword(newPassword));
		userDao.updatePasswordById(user);
		// 清除用户缓存
		user.setLoginName(loginName);
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {
		// 保存上次登录信息
		user.setOldLoginIp(user.getLoginIp());
		user.setOldLoginDate(user.getLoginDate());
		// 更新本次登录信息
		user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLoginDate(new Date());
		userDao.updateLoginInfo(user);
	}


	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}

	//-- Role Service --//

	public Role getRole(String id) {
		return roleDaoR.get(id);
	}

	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDaoR.getByName(r);
	}

	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDaoR.getByEnname(r);
	}

	public List<Role> findRole(Role role){
		return roleDaoR.findList(role);
	}

	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}

	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())){
			role.preInsert();
			roleDao.insert(role);
		}else{
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			roleDao.insertRoleMenu(role);
		}
		// 更新角色与部门关联
		roleDao.deleteRoleOffice(role);
		if (role.getOfficeList().size() > 0){
			roleDao.insertRoleOffice(role);
		}
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	/**
	 * @Title:
	 * @Description: 清除用户的某个权限
	 * @author chunyangli
	 * @date 2016/10/17 18:36
	 * @param     
	 * @return    
	 * @throws 
	 */
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		List<Role> roles = user.getRoleList();
		for (Role e : roles){
			if (e.getId().equals(role.getId())){
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title:
	 * @Description: 给用户授权
	 * @author chunyangli
	 * @date 2016/10/17 18:37
	 * @param
	 * @return
	 * @throws
	 */
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);
		return user;
	}

	//-- Menu Service --//
	/**
	 * @Title:
	 * @Description: 通过菜单id获取菜单
	 * @author chunyangli
	 * @date 2016/10/17 18:37
	 * @param
	 * @return
	 * @throws
	 */

	public Menu getMenu(String id) {
		return menuDaoR.get(id);
	}
	/**
	 * @Title: 
	 * @Description: TODO
	 * @author chunyangli
	 * @date 2016/10/17 18:38
	 * @param     
	 * @return    
	 * @throws 
	 */
	public List<Menu> findAllMenu(){
		return UserUtils.getMenuList();
	}

	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {

		// 获取父节点实体
		menu.setParent(this.getMenu(menu.getParent().getId()));

		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds();

		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");

		// 保存或更新实体
		if (StringUtils.isBlank(menu.getId())){
			menu.preInsert();
			menuDao.insert(menu);
		}else{
			menu.preUpdate();
			menuDao.update(menu);
		}

		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = menuDaoR.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menuDao.delete(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"  - Powered By http://xxx.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	/**
	 * @Title:
	 * @Description: 更新档案信息
	 * @author chunyangli
	 * @date 2016/10/17 14:08
	 * @param params
	 * @return
	 * @throws
	 */
	@Transactional(readOnly = false)
	public int updatePerInfo(Map<String,Object> params) throws  Exception {
		return userDao.updatePerInfo(params);
	}
	/**
	 * @Title:
	 * @Description: 通过身份证号查询用户信息
	 * @author chunyangli
	 * @date 2016/10/24 10:23
	 * @param
	 * @return
	 * @throws
	 */
	public User getUserByCertNum(Map<String,Object> params){
		return userDaoR.getUserByCertNum(params);
	}

	/**
	 * @param user
	 * @return
	 * @throws
	 * @Title:
	 * @Description: TODO 保存用户
	 * @author chunyangli
	 * @date 2016/10/26 16:47
	 */
	public int insertUser(User user) {
		int flag = 0;
		try {
			flag = 0;
			if(null!=user){
                if(StringUtils.isBlank(user.getId())){
                    user.preInsert();
                }
                flag = userDao.insertUser(user);
            }
		} catch (Exception e) {
			logger.error("insertUser is error",e);
			throw e;
		}
		return flag;
	}
}
