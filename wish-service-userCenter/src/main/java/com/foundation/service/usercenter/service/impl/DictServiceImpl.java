package com.foundation.service.usercenter.service.impl;

import java.util.List;
import java.util.Map;

import com.foundation.common.cache.CacheUtils;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.sys.Dict;
import com.foundation.dao.modules.read.sys.DictDaoR;
import com.foundation.dao.modules.write.sys.DictDao;
import com.foundation.service.usercenter.DictUtils;
import com.foundation.service.usercenter.service.ICrudService;
import com.foundation.service.usercenter.service.IDictService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 字典Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl  implements IDictService{
	@Autowired(required = false)
	private DictDao dictDao;
	@Autowired(required = false)
	private DictDaoR dictDaoR;


	public Dict get(Map<String,Object> params){
		return dictDaoR.get(params);
	}
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dictDaoR.findTypeList(new Dict());
	}
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<Dict> findPage(Page<Dict> page, Dict entity) {
		entity.setPage(page);
		page.setList(dictDaoR.findList(entity));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		saveOrUpdate(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		if(null!=dict && StringUtils.isNotBlank(dict.getId())){
			Map<String,Object> params = Maps.newHashMap();
			params.put("id",dict.getId());
			dictDao.delete(params);
			CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
		}

	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(Dict entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dictDao.insert(entity);
		}else{
			entity.preUpdate();
			dictDao.update(entity);
		}
	}

}
