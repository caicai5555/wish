package com.foundation.dao.modules.write.sys;


import com.foundation.dao.entity.sys.Area;
import com.foundation.dao.modules.MybatisBaseDao;
import com.foundation.dao.util.CrudDao;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.sys.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典DAO接口
 * @author fqh
 * @version 2016-08-16
 */
@MyBatisRepository
public interface DictDao extends MybatisBaseDao<String, Dict>{


	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Dict entity);

	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public void update(Dict entity);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param params
	 * @see public int delete(Dict entity)
	 * @return
	 */
	public int delete(Map<String,Object> params);


}
