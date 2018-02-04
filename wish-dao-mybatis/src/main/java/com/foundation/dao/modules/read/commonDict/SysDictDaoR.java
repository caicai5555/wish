package com.foundation.dao.modules.read.commonDict;


import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.commonDict.SysDict;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 字典DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface SysDictDaoR extends MybatisBaseDao<String, SysDict> {

	/**
	 * 根据字典查找所有类型
	 * @param dict
	 * @return
	 */
	public List<String> findTypeList(SysDict dict);


	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SysDict> findList(SysDict entity);

	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	//public List<SysDict> findAllList(SysDict entity);

	/**
	 * 查询所有数据列表
	 * @see public List<SysDict> findAllList(SysDict entity)
	 * @return
	 */
	@Deprecated
	public List<SysDict> findAllList();



}
