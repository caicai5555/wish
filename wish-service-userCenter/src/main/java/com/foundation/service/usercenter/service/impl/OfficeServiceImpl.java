package com.foundation.service.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foundation.common.utils.Reflections;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.modules.read.sys.OfficeDaoR;
import com.foundation.dao.modules.write.sys.OfficeDao;
import com.foundation.service.usercenter.UserUtils;
import com.foundation.service.usercenter.service.IOfficeService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.dao.entity.sys.Office;

/**
 * 机构Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeServiceImpl  implements IOfficeService {
	@Autowired(required = false)
	private OfficeDaoR officeDaoR;

	@Autowired(required = false)
	private OfficeDao officeDao;

	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			office.setParentIds(office.getParentIds()+"%");
			return officeDaoR.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		saveData(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	@Transactional(readOnly = false)
	public void saveData(Office entity) {


		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null || StringUtils.isBlank(entity.getParentId())
				|| "0".equals(entity.getParentId())){
			entity.setParent(null);
		}else{
			Map<String,Object> params = Maps.newHashMap();
			params.put("id",entity.getParentId());
			entity.setParent(officeDaoR.get(params));
		}
		if (entity.getParent() == null){
			Office parentEntity = null;
			try {
				parentEntity = new Office();
				parentEntity.setId("0");
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			entity.setParent(parentEntity);
			entity.getParent().setParentIds(StringUtils.EMPTY);
		}

		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = entity.getParentIds();

		// 设置新的父节点串
		entity.setParentIds(entity.getParent().getParentIds()+entity.getParent().getId()+",");

		// 保存或更新实体
		saveOrUpdate(entity);

		// 更新子节点 parentIds
		Office o = null;
		try {
			o = new Office();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		o.setParentIds("%,"+entity.getId()+",%");
		List<Office> list = officeDaoR.findByParentIdsLike(o);
		for (Office e : list){
			if (e.getParentIds() != null && oldParentIds != null){
				e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
				preUpdateChild(entity, e);
				officeDao.updateParentIds(e);
			}
		}

	}

	/**
	 * 预留接口，用户更新子节前调用
	 * @param childEntity
	 */
	protected void preUpdateChild(Office entity, Office childEntity) {

	}


	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void saveOrUpdate(Office entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			officeDao.insert(entity);
		}else{
			entity.preUpdate();
			officeDao.update(entity);
		}
	}


	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		officeDao.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}

	public Office get(Map<String,Object> params){
		return officeDaoR.get(params);
	}
	
}
