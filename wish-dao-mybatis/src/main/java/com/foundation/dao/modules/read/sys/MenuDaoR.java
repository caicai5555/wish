package com.foundation.dao.modules.read.sys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Menu;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.CrudDao;

import java.util.List;

/**
 * @Title: MenuDaoR.java
 * @Package com.foundation.dao.modules.read.sys
 * @Description: 菜单读dao
 * @author chunyangli(chunyangli@bioeh.com)
 * @date 2016/11/21 10:06
 */
@MyBatisRepository
public interface MenuDaoR extends MybatisBaseDao<String, Menu> {


	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);


	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public Menu get(String id);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public Menu get(Menu entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<Menu>());
	 * @param entity
	 * @return
	 */
	public List<Menu> findList(Menu entity);

	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<Menu> findAllList(Menu entity);

	/**
	 * 查询所有数据列表
	 * @see public List<Menu> findAllList(Menu entity)
	 * @return
	 */
	@Deprecated
	public List<Menu> findAllList();
	
}
