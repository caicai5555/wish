package com.foundation.service.usercenter.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import org.apache.shiro.session.Session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author fqh
 * @version 2013-12-05
 */
public interface ISystemService{

	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(String id);
	public User getUserInfoById(Map<String,Object> params);

	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);
	public Page<User> findUser(Page<User> page, User user);

	/**
	 * 无分页查询人员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(User user);

	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param officeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId);

	public void saveUser(User user);

	public void updateUserInfo(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public void updatePasswordById(String id, String loginName, String newPassword);

	public void updateUserLoginInfo(User user);



	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions();

	//-- Role Service --//

	public Role getRole(String id);

	public Role getRoleByName(String name);

	public Role getRoleByEnname(String enname);

	public List<Role> findRole(Role role);

	public List<Role> findAllRole();

	public void saveRole(Role role);

	public void deleteRole(Role role);
	/**
	 * @Title:
	 * @Description: 清除用户的某个权限
	 * @author chunyangli
	 * @date 2016/10/17 18:36
	 * @param     
	 * @return    
	 * @throws 
	 */
	public Boolean outUserInRole(Role role, User user);

	/**
	 * @Title:
	 * @Description: 给用户授权
	 * @author chunyangli
	 * @date 2016/10/17 18:37
	 * @param
	 * @return
	 * @throws
	 */
	public User assignUserToRole(Role role, User user);

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

	public Menu getMenu(String id);
	/**
	 * @Title: 
	 * @Description: TODO
	 * @author chunyangli
	 * @date 2016/10/17 18:38
	 * @param     
	 * @return    
	 * @throws 
	 */
	public List<Menu> findAllMenu();

	public void saveMenu(Menu menu);

	public void updateMenuSort(Menu menu);
	public void deleteMenu(Menu menu);


	/**
	 * @Title:
	 * @Description: 更新档案信息
	 * @author chunyangli
	 * @date 2016/10/17 14:08
	 * @param params
	 * @return
	 * @throws
	 */
	public int updatePerInfo(Map<String,Object> params) throws  Exception;

	/**
	 * @Title:
	 * @Description: 通过身份证号查询用户信息
	 * @author chunyangli
	 * @date 2016/10/24 10:23
	 * @param
	 * @return
	 * @throws
	 */
	public User getUserByCertNum(Map<String,Object> params);
	/**
	 * @Title:
	 * @Description: TODO 保存用户
	 * @author chunyangli
	 * @date 2016/10/26 16:47
	 * @param     
	 * @return    
	 * @throws 
	 */
	public int insertUser(User user);


	/*		外接系统用户	*/

	/**
	 * @Title:getByIdOrLoginName
	 * @Description: 通过条件参数查询用户信息
	 * @author cuiyaohua
	 * @date 2016/12/13
	 * @param
	 * @return
	 * @throws
	 */
	public User getByIdOrLoginName(Map<String,Object> params);

	/**
	 * @Title:findUser
	 * @Description: 通过条件参数分页查询用户信息
	 * @author cuiyaohua
	 * @date 2016/12/13
	 * @param
	 * @return
	 * @throws
	 */
	public Page<User> findUser(Map<String, Object> params, Page<User> page);

	/**
	 * @Title:
	 * @Description: 保存用户
	 * @author cuiyaohua
	 * @date 2016/10/17 17:37
	 * @param
	 * @return
	 * @throws
	 */
	public void saveUser(User user, String systemCode);
}
