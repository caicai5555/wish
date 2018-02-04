package com.foundation.service.usercenter.biz;

import com.foundation.common.cache.CacheUtils;
import com.foundation.common.config.Global;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.usercenter.LogUtils;
import com.foundation.service.usercenter.UserUtils;
import org.apache.shiro.session.Session;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by fanqinghui on 2016/8/31.
 */
public interface IUserBiz {
    /**
     * @Title:
     * @Description: 通过id查询用户信息（缓存）
     * @author chunyangli
     * @date 2016/10/17 17:14
     * @param     id
     * @return    
     * @throws 
     */
    public User getUser(String id);
    /**
     * @Title:
     * @Description: 通过id查询用户信息（数据库）
     * @author chunyangli
     * @date 2016/10/17 17:14
     * @param params
     * @return
     * @throws
     */
    public User getUserInfoById(Map<String,Object> params);
    /**
     * @Title:
     * @Description: 通过登录名获取用户信息
     * @author chunyangli
     * @date 2016/10/17 17:15
     * @param     
     * @return    
     * @throws 
     */
    public User getUserByLoginName(String loginName);
    /**
     * @Title:
     * @Description: 分页查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     * @param
     * @return
     * @throws
     */
    public Page<User> findUser(Page<User> page, User user);
    /**
     * @Title:
     * @Description: 获取用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     * @param
     * @return
     * @throws
     */
    public List<User> findUser(User user);
    /**
     * @Title:
     * @Description: 通过归属部门查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     * @param     
     * @return    
     * @throws 
     */
    public List<User> findUserByOfficeId(String officeId);
    /**
     * @Title:
     * @Description: 保存用户
     * @author chunyangli
     * @date 2016/10/17 17:37
     * @param
     * @return
     * @throws
     */
    public void saveUser(User user);
    /**
     * @Title:
     * @Description: 更新用户信息--登录情况下
     * @author chunyangli
     * @date 2016/10/17 17:37
     * @param
     * @return
     * @throws
     */
    public void updateUserInfo(User user);

    /**
     * @Title:
     * @Description: 更新用户信息--非登陆
     * @author chunyangli
     * @date 2016/10/17 17:37
     * @param
     * @return
     * @throws
     */
    public void updateUser(User user);
    /**
     * @Title:
     * @Description: 删除用户
     * @author chunyangli
     * @date 2016/10/17 17:38
     * @param
     * @return
     * @throws
     */
    public void deleteUser(User user);
    /**
     * @Title:
     * @Description: 修改用户密码
     * @author chunyangli
     * @date 2016/10/17 17:38
     * @param
     * @return
     * @throws
     */
    public void updatePasswordById(String id, String loginName, String newPassword);
    /**
     * @Title:
     * @Description: 更新用户登录信息
     * @author chunyangli
     * @date 2016/10/17 17:38
     * @param
     * @return
     * @throws
     */
    public void updateUserLoginInfo(User user);
    /**
     * @Title:
     * @Description: 获得活动会话
     * @author chunyangli
     * @date 2016/10/17 17:38
     * @param     
     * @return    
     * @throws 
     */
    public Collection<Session> getActiveSessions();
    /**
     * @Title:
     * @Description: 通过角色id 获取角色
     * @author chunyangli
     * @date 2016/10/17 17:39
     * @param
     * @return
     * @throws
     */
    public Role getRole(String id);
    /**
     * @Title:
     * @Description: 通过用户
     * @author chunyangli
     * @date 2016/10/17 17:39
     * @param
     * @return
     * @throws
     */
    public Role getRoleByName(String name);
    /**
     * @Title:
     * @Description: 通过英文名字获取角色
     * @author chunyangli
     * @date 2016/10/17 17:45
     * @param
     * @return
     * @throws
     */
    public Role getRoleByEnname(String enname);


    /**
     * @Title:
     * @Description: 
     * @author chunyangli
     * @date 2016/10/17 17:47
     * @param
     * @return
     * @throws
     */
    public List<Role> findRole(Role role);
    /**
     * @Title:
     * @Description: 获取全部角色列表
     * @author chunyangli
     * @date 2016/10/17 17:48
     * @param     
     * @return    
     * @throws 
     */

    public List<Role> findAllRole();
    /**
     * @Title:
     * @Description: 保存角色
     * @author chunyangli
     * @date 2016/10/17 17:48
     * @param
     * @return
     * @throws
     */

    public void saveRole(Role role);
    /**
     * @Title:
     * @Description: 删除角色
     * @author chunyangli
     * @date 2016/10/17 18:31
     * @param
     * @return
     * @throws
     */

    public void deleteRole(Role role);
    /**
     * @Title:
     * @Description: 清除用户的某个角色
     * @author chunyangli
     * @date 2016/10/17 18:35
     * @param     
     * @return    
     * @throws 
     */

    public Boolean outUserInRole(Role role, User user);
    /**
     * @Title:
     * @Description: 给用户授权
     * @author chunyangli
     * @date 2016/10/17 18:39
     * @param
     * @return
     * @throws
     */

    public User assignUserToRole(Role role, User user);

    //-- Menu Service --//
    /**
     * @Title:
     * @Description: 通过菜单id获取菜单信息
     * @author chunyangli
     * @date 2016/10/17 18:39
     * @param
     * @return
     * @throws
     */

    public Menu getMenu(String id);
    /**
     * @Title:
     * @Description: 获取所有的菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     * @param
     * @return
     * @throws
     */

    public List<Menu> findAllMenu();
    /**
     * @Title:
     * @Description: 保存菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     * @param
     * @return
     * @throws
     */

    public void saveMenu(Menu menu);
    /**
     * @Title:
     * @Description: 更新菜单种类
     * @author chunyangli
     * @date 2016/10/17 18:40
     * @param
     * @return
     * @throws
     */

    public void updateMenuSort(Menu menu) ;
    /**
     * @Title: 删除菜单
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/17 18:41
     * @param     
     * @return    
     * @throws 
     */

    public void deleteMenu(Menu menu) ;


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
     * @Description: 新的保存用户
     * @author chunyangli
     * @date 2016/10/26 16:53
     * @param     
     * @return    
     * @throws 
     */
    public int insertUser(User user) throws Exception;


	/*		外接系统用户	*/

    /**
     * @Title:getByIdOrLoginName
     * @Description: 通过条件参数查询用户信息
     * @author cuiyaohua
     * @date 2016/12/13
     * @param loginName
     * @param systemCode
     * @return
     * @throws
     */
    public User getByIdOrLoginName(String loginName, String systemCode);

    /**
     * @Title:getByLoginName
     * @Description: 通过条件参数查询用户信息
     * @author cuiyaohua
     * @date 2016/12/13
     * @param loginName
     * @param systemCode
     * @return
     * @throws
     */
    public User getByLoginName(String loginName, String systemCode);

    /**
     * @Title:getById
     * @Description: 通过条件参数查询用户信息
     * @author cuiyaohua
     * @date 2016/12/13
     * @param id
     * @param systemCode
     * @return
     * @throws
     */
    public User getById(String id, String systemCode);

    /**
     * @Title:findUser
     * @Description: 通过条件参数查询用户信息
     * @author cuiyaohua
     * @date 2016/12/13
     * @param params
     * @param page
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
