package com.foundation.dao.modules.write.sys;

import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.User;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface UserDao extends MybatisBaseDao<String, User> {



	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);

	public int insertUser(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(User entity);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(User entity);

	/**
	 * @Title:
	 * @Description: 更新档案信息
	 * @author chunyangli
	 * @date 2016/10/17 14:08
	 * @param params
	 * @return
	 * @throws 
	 */
	public int updatePerInfo(Map<String,Object> params);


}
