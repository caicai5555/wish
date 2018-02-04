package com.foundation.service.usercenter;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.sys.User;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chunyangli(chunyangli@bioeh.com)
 * @Title: SystemTest.java
 * @Package com.foundation.service.usercenter
 * @Description: TODO
 * @date 2016/10/17 16:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:spring-db.xml" })
public class SystemUserTest {

    @Autowired
    private IUserBiz userBiz;

    /*  外接系统用户 */
    @Test
    public void subsystemGet(){
        Map<String, Object> params = Maps.newHashMap();
        params.put("code", "pregnancy");
        Page<User> page = userBiz.findUser(params, new Page<User>(1, 20));
        System.out.println(page.getList().size());
        User entity = userBiz.getByLoginName("jn_jsb", "tjxt");
        User entity1 = userBiz.getByLoginName("jn_jsb", null);
        System.out.println(entity);
        System.out.println(entity1);
        User user = UserUtils.getByLoginName("tree4", "pregnancy");
        System.out.println(user);
    }

   /* *//**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过id查询用户信息（缓存）
     * @author chunyangli
     * @date 2016/10/17 17:14
     *//*
//   @Test
    public void getUser() {
        String id="c883d4f3f8604bb78a802d4dbe880dce";
        User user = userBiz.getUser(id);
        System.out.println("========end=======");
    }

    *//**
     * @param params
     * @return
     * @throws
     * @Title:
     * @Description: 通过id查询用户信息（数据库）
     * @author chunyangli
     * @date 2016/10/17 17:14
     *//*
//   @Test
    public void getUserInfoById() {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id","c883d4f3f8604bb78a802d4dbe880dce");
        User user = userBiz.getUserInfoById(params);
       System.out.print("============end=============");
    }

    *//**
     * @param loginName
     * @return
     * @throws
     * @Title:
     * @Description: 通过登录名获取用户信息
     * @author chunyangli
     * @date 2016/10/17 17:15
     *//*
//   @Test
    public void getUserByLoginName() {
        String loginName = "user";
        User user = userBiz.getUserByLoginName(loginName);
        System.out.print("============end=============");
    }

    *//**
     * @param page
     * @param user @return
     * @throws
     * @Title:
     * @Description: 分页查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     *//*
//   @Test
    public void findUser() {
        Page<User> page = new Page<>();
        String loginName = "user";
        User user = userBiz.getUserByLoginName(loginName);
       Page<User> page2 = userBiz.findUser(page, user);
       System.out.println("============end===============");
    }

    *//**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 获取用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     *//*
//   @Test
    public void findUserByUser() {
       String loginName = "admin";
       User user = userBiz.getUserByLoginName(loginName);
       List<User> user1 = userBiz.findUser(user);
       System.out.println("============end===============");
    }

    *//**
     * @param officeId
     * @return
     * @throws
     * @Title:
     * @Description: 通过归属部门查询用户列表
     * @author chunyangli
     * @date 2016/10/17 17:16
     *//*
//   @Test
    public void findUserByOfficeId() {
        String officeId = "2";
        List<User> list = userBiz.findUserByOfficeId(officeId);
        System.out.println("============end===============");
    }

    *//**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 保存用户
     * @author chunyangli
     * @date 2016/10/17 17:37
     *//*
//   @Test
    public void saveUser() {
        User user = new User();
       user.setName("王森");
       user.setCardNo("1201129861236478");
       user.setLoginName("wangsen");
       user.setPassword("000000");
       Role role = new Role();
       role.setName("普通用户");
       role.setId("6");
       role.setEnname("d");
       role.setOfficeIds("2");
       List<Role> roleList = new ArrayList<Role>();
       roleList.add(role);
        user.setRoleList(roleList);
        userBiz.saveUser(user);
       System.out.println("============end===============");
    }

    *//**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 更新用户信息
     * @author chunyangli
     * @date 2016/10/17 17:37
     *//*
//   @Test
    public void updateUserInfo() {
       String loginName = "112233";
       User user = userBiz.getUserByLoginName(loginName);
       user.setCardNo("11223344");
        userBiz.updateUserInfo(user);
       System.out.println("============end===============");
    }

    *//**
     * @param
     * @return
     * @throws
     * @Title:
     * @Description: 删除用户
     * @author chunyangli
     * @date 2016/10/17 17:38
     *//*
//   @Test
    public void deleteUser() {
        String loginName = "112233";
        User user = userBiz.getUserByLoginName(loginName);
        userBiz.deleteUser(user);
        System.out.println("============end===============");
    }

    *//**
     * @throws
     * @Title:
     * @Description: 修改用户密码
     * @author chunyangli
     * @date 2016/10/17 17:38
     *//*
//   @Test
    public void updatePasswordById() {
        String id = "ea698032-3bbc-45a0-bc93-320383e0b65f";
        String loginName = "112233";
        String newPassword = "11223344";
        userBiz.updatePasswordById(id, loginName, newPassword);
        System.out.println("============end===============");
    }

    *//**
     * @param user
     * @return
     * @throws
     * @Title:
     * @Description: 更新用户登录信息
     * @author chunyangli
     * @date 2016/10/17 17:38
     *//*
   // @Test
    public void updateUserLoginInfo() {
        String loginName = "112233";
        User user = userBiz.getUserByLoginName(loginName);
        user.setCardNo("11223344");
        userBiz.updateUserInfo(user);
        System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title:
     * @Description: 获得活动会话
     * @author chunyangli
     * @date 2016/10/17 17:38
     *//*
//   @Test
    public void getActiveSessions() {
       Collection<Session> sessionCollection =  userBiz.getActiveSessions();
        System.out.println("============end===============");
    }

    *//**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过角色id 获取角色
     * @author chunyangli
     * @date 2016/10/17 17:39
     *//*
//   @Test
    public void getRole() {
        String id = "6";
        Role role = userBiz.getRole(id);
        System.out.println("============end===============");
    }

    *//**
     * @param name
     * @return
     * @throws
     * @Title:
     * @Description: 通过用户
     * @author chunyangli
     * @date 2016/10/17 17:39
     *//*
   // @Test
    public void getRoleByName() {
        String name = "普通用户";
        Role role =  userBiz.getRoleByName(name);
        System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title:
     * @Description: 通过英文名字获取角色
     * @author chunyangli
     * @date 2016/10/17 17:45
     *//*
   // @Test
    public void getRoleByEnname() {
        String enname = "d";
        Role role =  userBiz.getRoleByName(enname);
        System.out.println("============end===============");
    }

    *//**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description:
     * @author chunyangli
     * @date 2016/10/17 17:47
     *//*
   // @Test
    public void findRole() {
        String enname = "d";
        Role role =  userBiz.getRoleByName(enname);
        List<Role>  roles =  userBiz.findRole(role);
        System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title:
     * @Description: 获取全部角色列表
     * @author chunyangli
     * @date 2016/10/17 17:48
     *//*
   // @Test
    public void findAllRole() {
        List<Role>  roleList = userBiz.findAllRole();
        System.out.println("============end===============");
    }

    *//**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description: 保存角色
     * @author chunyangli
     * @date 2016/10/17 17:48
     *//*
//   @Test
    public void saveRole() {
        Role role = new Role();
        role.setName("lcytest");
        role.setOfficeIds("2");
        role.setEnname("lcy");
        userBiz.saveRole(role);
        System.out.println("============end===============");
    }

    *//**
     * @param role
     * @return
     * @throws
     * @Title:
     * @Description: 删除角色
     * @author chunyangli
     * @date 2016/10/17 18:31
     *//*
   // @Test
    public void deleteRole() {
        String name = "lcytest";
        Role role =  userBiz.getRoleByName(name);
        userBiz.deleteRole(role);
        System.out.println("============end===============");
    }

    *//**
     * @param role
     * @param user @return
     * @throws
     * @Title:
     * @Description: 清除用户的某个角色
     * @author chunyangli
     * @date 2016/10/17 18:35
     *//*
   // @Test
    public void outUserInRole() {
        String name = "lcytest";
        Role role =  userBiz.getRoleByName(name);
        String loginName = "112233";
        User user = userBiz.getUserByLoginName(loginName);
        boolean boo =  userBiz.outUserInRole(role, user);
        System.out.println("============end===============");
    }

    *//**
     * @param role
     * @param user @return
     * @throws
     * @Title:
     * @Description: 给用户授权
     * @author chunyangli
     * @date 2016/10/17 18:39
     *//*
   // @Test
    public void assignUserToRole() {

        String name = "lcytest";
        Role role =  userBiz.getRoleByName(name);
        String loginName = "112233";
        User user = userBiz.getUserByLoginName(loginName);
        userBiz.assignUserToRole(role, user);
        System.out.println("============end===============");
    }

    *//**
     * @param id
     * @return
     * @throws
     * @Title:
     * @Description: 通过菜单id获取菜单信息
     * @author chunyangli
     * @date 2016/10/17 18:39
     *//*
   // @Test
    public void getMenu() {
        String id = "1";
        Menu menu =  userBiz.getMenu(id);
        System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title:
     * @Description: 获取所有的菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     *//*
   // @Test
    public void findAllMenu() {
        List<Menu> menuList =  userBiz.findAllMenu();
        System.out.println("============end===============");
    }

    *//**
     * @param menu
     * @return
     * @throws
     * @Title:
     * @Description: 保存菜单
     * @author chunyangli
     * @date 2016/10/17 18:40
     *//*
//   @Test
    public void saveMenu() {
        Menu menu = new Menu();
        menu.setName("lcytest");
        menu.setHref("/lcy/lcy/lcy");
       Menu parent = new Menu();
       parent.setId("2");
       menu.setParent(parent);
        userBiz.saveMenu(menu);
       System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title:
     * @Description: 更新菜单种类
     * @author chunyangli
     * @date 2016/10/17 18:40
     *//*
//   @Test
    public void updateMenuSort() {
       String id = "1";
       Menu menu =  userBiz.getMenu(id);
       menu.setName("档案列表");
        userBiz.updateMenuSort(menu);
       System.out.println("============end===============");
    }

    *//**
     * @return
     * @throws
     * @Title: 删除菜单
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/17 18:41
     *//*
   // @Test
    public void deleteMenu() {
        String id = "1";
        Menu menu =  userBiz.getMenu(id);
        userBiz.deleteMenu(menu);
        System.out.println("============end===============");
    }

    *//**
     * @param params
     * @return
     * @throws
     * @Title:
     * @Description: 更新档案信息
     * @author chunyangli
     * @date 2016/10/17 14:08
     *//*
//   @Test
    public void updatePerInfo() throws Exception {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("name","lcy");
        params.put("cardNo","111111");
        params.put("email","lcy@qq.com");
        params.put("phone","11111111");
        params.put("mobile","2222222");
        params.put("addressProvince","北京市");
        params.put("addressCity","朝阳区");
        params.put("addressCounty","八里庄");
        params.put("addressTown","十里堡");
        params.put("addressVillage","13号楼");
        params.put("hukouProvince","");
        params.put("hukouCity","");
        params.put("hukouCounty","");
        params.put("hukouTown","");
        params.put("hukouVillage","");
        params.put("updateBy","c883d4f3f8604bb78a802d4dbe880dce");
        params.put("id","ea698032-3bbc-45a0-bc93-320383e0b65f");
        params.put("DBKEY","yungu#jiamiKey@20160830!");
         userBiz.updatePerInfo(params);
        System.out.println("============end===============");
    }
//    @Test
    public void getUserByCertNum(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("certNum","37068619871009412X");
        params.put("DBKEY" , "yungu#jiamiKey@20160830!");
        User u = userBiz.getUserByCertNum(params);
        System.out.println("============end===============");
    }
    @Test
    public void insertUser(){

        User user = new User();
        user.setUserType(Constants.UserRole.Common);
        user.setName("吴振杰");
        user.setLoginName("wuzhenjie");
        user.setPassword(PwdSha1Util.entryptPassword("123456"));
        user.setLoginFlag(Global.YES);
        user.setDelFlag(Constants.DEL_FLAG_NORMAL);
        user.setAge(25);
        user.setAddressCity("天津市");
        user.setAddressCounty("蓟州区");
        user.setCardNo("11011201993513564" );
        user.setCertificateNumber("11011201993513564");
        //user.setNation(info.getNation());TODO://民族转换
        user.setAddressProvince("天津市");
        user.setAddressTown("下仓镇" );
        user.setAddressVillage("王家浅");
        //user.setCertificateType(info.getCertificateType());//TODO:身份证转换
        //user.setEducation(info.getEducation());//TODO:教育
        //user.setOccupation(info.getOccupation());//TODO:职业
        user.setHukouCity("天津市" );
        user.setHukouCounty("蓟州区");
        user.setHukouProvince("天津市" );
        user.setHukouTown("下仓镇");
        user.setHukouVillage("王家浅" );
        //user.setHukouType(info.getHukouType());//TODO://户口类型
        user.setPhone("1331002362013" );
        user.setPostCode("1111");
        user.setBirthday(new Date());
        user.setEmail("wuzhenjie@qq.com");
        user.setAddress("天津市");
        try {
            userBiz.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


}
