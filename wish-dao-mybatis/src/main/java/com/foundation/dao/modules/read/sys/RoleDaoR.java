package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Role;
import com.foundation.dao.entity.sys.User;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @Title: RoleDaoR.java
 * @Package com.foundation.dao.modules.read.sys
 * @Description: 角色读的dao类
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/11/21 10:05 
 */
@MyBatisRepository
public interface RoleDaoR extends MybatisBaseDao<String, Role> {

	public Role getByName(Role role);
	
	public Role getByEnname(Role role);



	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public Role get(String id);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public Role get(Role entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<Role> findList(Role entity);

	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<Role> findAllList(Role entity);

	/**
	 * 查询所有数据列表
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	public List<Role> findAllList();


}
