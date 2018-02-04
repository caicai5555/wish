package com.foundation.dao.modules.write.sys;

import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Role;

/**
 * 角色DAO接口
 * @author fqh
 * @version 2013-12-05
 */
@MyBatisRepository
public interface RoleDao extends MybatisBaseDao<String, Role> {

	/**
	 * 维护角色与菜单权限关系
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	/**
	 * 维护角色与公司部门关系
	 * @param role
	 * @return
	 */
	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Role entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public void update(Role entity);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	public void delete(String id);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(Role entity);

}
