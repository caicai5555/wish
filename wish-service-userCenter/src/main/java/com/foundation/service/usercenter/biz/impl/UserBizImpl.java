package com.foundation.service.usercenter.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.foundation.service.usercenter.service.ISystemService;
import com.google.common.collect.Maps;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: UserBizImpl.java
 * @Package com.foundation.service.usercenter.biz.impl
 * @Description: 用户中心业务类
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/10/17 17:01 
 */
@Service
public class UserBizImpl implements IUserBiz{
    @Autowired(required = false)
    private ISystemService systemService;
    /**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过id查询用户信息（缓存）
     * @author chunyangli
     * @date 2016/10/17 17:14
     */
    @Override
    public User getUser(String id) {
        return systemService.getUser(id);
    }

    /**
     * @param params
     * @return
     * @throws
     * @Title:
     * @Description: 通过id查询用户信息（数据库）
     * @author chunyangli
     * @date 2016/10/17 17:14
     */
    @Override
    public User getUserInfoById(Map<String, Object> params) {
        return systemService.getUserInfoById(params);
    }

    /**
     * @param loginName
     * @return
     * @throws
     * @Title:
     * @Description: 通过登录名获取用户信息
     * @author chunyangli
     * @date 2016/10/17 17:15
     */
    @Override
    public User getUserByLoginName(String loginName) {
        return systemService.getUserByLoginName(loginName);
    }

    /**
     * @param page
     * @param user @return
     * @throws
     * @Title:
     * @Description: 分页查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     */
    @Override
    public Page<User> findUser(Page<User> page, User user) {
        return systemService.findUser(page,user);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 获取用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     */
    @Override
    public List<User> findUser(User user) {
        return systemService.findUser(user);
    }

    /**
     * @param officeId
     * @return
     * @throws
     * @Title:
     * @Description: 通过归属部门查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     */
    @Override
    public List<User> findUserByOfficeId(String officeId) {
        return systemService.findUserByOfficeId(officeId);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 保存用户
     * @author chunyangli
     * @date 2016/10/17 17:37
     */
    @Override
    public void saveUser(User user) {
        systemService.saveUser(user);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 更新用户信息
     * @author chunyangli
     * @date 2016/10/17 17:37
     */
    @Override
    public void updateUserInfo(User user) {
        systemService.updateUserInfo(user);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 更新用户信息
     * @author chunyangli
     * @date 2016/10/17 17:37
     */
    @Override
    public void updateUser(User user) {
        systemService.updateUser(user);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 删除用户
     * @author chunyangli
     * @date 2016/10/17 17:38
     */
    @Override
    public void deleteUser(User user) {
        systemService.deleteUser(user);
    }

    /**
     * @param id
     * @param loginName
     * @param newPassword @return
     * @throws
     * @Title:
     * @Description: 修改用户密码
     * @author chunyangli
     * @date 2016/10/17 17:38
     */
    @Override
    public void updatePasswordById(String id, String loginName, String newPassword) {
        systemService.updatePasswordById(id,loginName,newPassword);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 更新用户登录信息
     * @author chunyangli
     * @date 2016/10/17 17:38
     */
    @Override
    public void updateUserLoginInfo(User user) {
        systemService.updateUserInfo(user);
    }

    /**
     * @return
     * @throws
     * @Title:
     * @Description: 获得活动会话
     * @author chunyangli
     * @date 2016/10/17 17:38
     */
    @Override
    public Collection<Session> getActiveSessions() {
        return systemService.getActiveSessions();
    }

    /**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过角色id 获取角色
     * @author chunyangli
     * @date 2016/10/17 17:39
     */
    @Override
    public Role getRole(String id) {
        return systemService.getRole(id);
    }

    /**
     * @param name
     * @return
     * @throws
     * @Title:
     * @Description: 通过用户
     * @author chunyangli
     * @date 2016/10/17 17:39
     */
    @Override
    public Role getRoleByName(String name) {
        return systemService.getRoleByName(name);
    }

    /**
     * @param enname
     * @return
     * @throws
     * @Title:
     * @Description: 通过英文名字获取角色
     * @author chunyangli
     * @date 2016/10/17 17:45
     */
    @Override
    public Role getRoleByEnname(String enname) {
        return systemService.getRoleByEnname(enname);
    }

    /**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description:
     * @author chunyangli
     * @date 2016/10/17 17:47
     */
    @Override
    public List<Role> findRole(Role role) {
        return systemService.findRole(role);
    }

    /**
     * @return
     * @throws
     * @Title:
     * @Description: 获取全部角色列表
     * @author chunyangli
     * @date 2016/10/17 17:48
     */
    @Override
    public List<Role> findAllRole() {
        return systemService.findAllRole();
    }

    /**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description: 保存角色
     * @author chunyangli
     * @date 2016/10/17 17:48
     */
    @Override
    public void saveRole(Role role) {
        systemService.saveRole(role);
    }

    /**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description: 删除角色
     * @author chunyangli
     * @date 2016/10/17 18:31
     */
    @Override
    public void deleteRole(Role role) {
        systemService.deleteRole(role);
    }

    /**
     * @param role
     * @param user @return
     * @throws
     * @Title:
     * @Description: 清除用户的某个角色
     * @author chunyangli
     * @date 2016/10/17 18:35
     */
    @Override
    public Boolean outUserInRole(Role role, User user) {
        return systemService.outUserInRole(role,user);
    }

    /**
     * @param role
     * @param user @return
     * @throws
     * @Title:
     * @Description: 给用户授权
     * @author chunyangli
     * @date 2016/10/17 18:39
     */
    @Override
    public User assignUserToRole(Role role, User user) {
        return systemService.assignUserToRole(role,user);
    }

    /**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过菜单id获取菜单信息
     * @author chunyangli
     * @date 2016/10/17 18:39
     */
    @Override
    public Menu getMenu(String id) {
        return systemService.getMenu(id);
    }

    /**
     * @return
     * @throws
     * @Title:
     * @Description: 获取所有的菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     */
    @Override
    public List<Menu> findAllMenu() {
        return systemService.findAllMenu();
    }

    /**
     * @param menu
     * @return
     * @throws
     * @Title:
     * @Description: 保存菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     */
    @Override
    public void saveMenu(Menu menu) {
        systemService.saveMenu(menu);
    }

    /**
     * @param menu
     * @return
     * @throws
     * @Title:
     * @Description: 更新菜单种类
     * @author chunyangli
     * @date 2016/10/17 18:40
     */
    @Override
    public void updateMenuSort(Menu menu) {
        systemService.updateMenuSort(menu);
    }

    /**
     * @param menu
     * @return
     * @throws
     * @Title: 删除菜单
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/17 18:41
     */
    @Override
    public void deleteMenu(Menu menu) {
        systemService.deleteMenu(menu);
    }

    /**
     * @param params
     * @return
     * @throws
     * @Title:
     * @Description: 更新档案信息
     * @author chunyangli
     * @date 2016/10/17 14:08
     */
    @Override
    public int updatePerInfo(Map<String, Object> params) throws Exception {
        return systemService.updatePerInfo(params);
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
        return  systemService.getUserByCertNum(params);
    }

    /**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 新的保存用户
     * @author chunyangli
     * @date 2016/10/26 16:53
     */
    @Override
    public int insertUser(User user) throws Exception {
        return systemService.insertUser(user);
    }

    @Override
    public User getByIdOrLoginName(String loginName, String systemCode) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("loginName", loginName);
        if (StringUtils.isNotEmpty(systemCode)) {
            params.put("code", systemCode);
        }
        return systemService.getByIdOrLoginName(params);
    }

    @Override
    public User getByLoginName(String loginName, String systemCode) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("loginName", loginName);
        if (StringUtils.isNotEmpty(systemCode)) {
            params.put("code", systemCode);
        }
        return systemService.getByIdOrLoginName(params);
    }

    @Override
    public User getById(String id, String systemCode) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        if (StringUtils.isNotEmpty(systemCode)) {
            params.put("code", systemCode);
        }
        return systemService.getByIdOrLoginName(params);
    }

    @Override
    public Page<User> findUser(Map<String, Object> params, Page<User> page) {
        return systemService.findUser(params, page);
    }

    @Override
    public void saveUser(User user, String systemCode) {
        systemService.saveUser(user, systemCode);
    }
}
