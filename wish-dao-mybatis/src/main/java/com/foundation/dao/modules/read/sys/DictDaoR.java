package com.foundation.dao.modules.read.sys;


import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Dict;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * 字典DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface DictDaoR extends MybatisBaseDao<String, Dict> {

	public List<String> findTypeList(Dict dict);


	/**
	 * 获取单条数据
	 * @param params
	 * @return
	 */
	public Dict get(Map<String, Object> params);

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public Dict get(Dict entity);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<Dict> findList(Dict entity);

	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<Dict> findAllList(Dict entity);

	/**
	 * 查询所有数据列表
	 * @see public List<Dict> findAllList(Dict entity)
	 * @return
	 */
	@Deprecated
	public List<Dict> findAllList();


	
}
