package com.foundation.dao.modules.write.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Log;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.CrudDao;
import com.foundation.dao.entity.sys.Menu;

import java.util.List;

/**
 * 菜单DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface MenuDao extends MybatisBaseDao<String, Menu>{

	public int updateParentIds(Menu menu);

	public int updateSort(Menu menu);

	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Menu entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public void update(Menu entity);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(Menu entity)
	 * @return
	 */
	@Deprecated
	public void delete(String id);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(Menu entity);
	
}
