package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.User;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface UserDaoR extends MybatisBaseDao<String, User> {

    /**
     * 根据登录名称查询用户
     * @param user
     * @return
     */
    public User getByLoginName(User user);

    /**
     * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
     * @param user
     * @return
     */
    public List<User> findUserByOfficeId(User user);

    /**
     * 查询全部用户数目
     * @return
     */
    public long findAllCount(User user);
    /**
     * @Title: 
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/14 11:46
     * @param     
     * @return    
     * @throws 
     */
    public List<User> findList(User user);
    /**
     * @Title: 
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/14 11:46
     * @param     
     * @return    
     * @throws 
     */
    public List<User> findAllList(User user);
    /**
     * @Title:
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/14 14:24
     * @param     
     * @return    
     * @throws 
     */
    public List<User> findAllList();
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public User get(String id);
    /**
     * 获取单条数据
     * @param user
     * @return
     */
    public User get(User user);
    
    /**
     * @Title: 根据用户id得到用户基本信息
     * @Description: TODO
     * @author chunyangli
     * @date 2016/10/14 15:17
     * @param params
     * @return   User
     * @throws 
     */
    public User getUserInfoById(Map<String,Object> params);

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
     * @Title:getByIdOrLoginName
     * @Description: 通过条件参数查询用户信息
     * @author cuiyaohua
     * @date 2016/12/13
     * @param
     * @return
     * @throws
     */
    public User getByIdOrLoginName(Map<String,Object> params);

}
