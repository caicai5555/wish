package com.foundation.dao.modules.write.commonDict;


import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.commonDict.SysDict;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.Map;

/**
 * 字典DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface SysDictDao extends MybatisBaseDao<String, SysDict>{


	/**
	 * 插入数据
	 * @param entity
	 * @return
	 *//*
	public int insert(SysDict entity);*/

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public void update(SysDict entity);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param params
	 * @see public int delete(SysDict entity)
	 * @return
	 */
	public int delete(Map<String, Object> params);


}
