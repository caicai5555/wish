package com.foundation.service.usercenter.service.impl;

import java.util.List;
import java.util.Map;

import com.foundation.common.utils.Reflections;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.modules.read.sys.AreaDaoR;
import com.foundation.dao.modules.write.sys.AreaDao;
import com.foundation.service.usercenter.UserUtils;
import com.foundation.service.usercenter.service.ISysAreaService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foundation.dao.entity.sys.Area;

/**
 * 区域Service
 * @author fqh
 * @version 2016-08-16
 */
@Service
public class SysAreaServiceImpl  implements ISysAreaService{
	@Autowired(required = false)
	private AreaDao areaDao;

	@Autowired(required = false)
	private AreaDaoR areaDaoR;
	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Area area) {
		saveData(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Area area) {
		if(null!=area && StringUtils.isNotBlank(area.getId())){
			areaDao.delete(area.getId());
			UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
		}
	}

	@Override
	public Area get(Map<String, Object> params) {
		return areaDaoR.get(params);
	}


	public void saveData(Area entity) {


		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null || StringUtils.isBlank(entity.getParentId())
				|| "0".equals(entity.getParentId())){
			entity.setParent(null);
		}else{
			Map params = Maps.newHashMap();
			params.put("id",entity.getParentId());
			entity.setParent(areaDaoR.get(params));
		}
		if (entity.getParent() == null){
			Area parentEntity = null;
			try {
				parentEntity = new Area();
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
		saveOrUpdateArea(entity);

		// 更新子节点 parentIds
		Area o = null;
		try {
			o = new Area();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		o.setParentIds("%,"+entity.getId()+",%");
		Map<String,Object> params = Maps.newHashMap();
		params.put("parentIds",o.getParentIds());
		List<Area> list = areaDaoR.findByParentIdsLike(params);
		for (Area e : list){
			if (e.getParentIds() != null && oldParentIds != null){
				e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
				preUpdateChild(entity, e);
				areaDao.updateParentIds(e);
			}
		}

	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	public void saveOrUpdateArea(Area entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			areaDao.insert(entity);
		}else{
			entity.preUpdate();
			areaDao.update(entity);
		}
	}

	/**
	 * 预留接口，用户更新子节前调用
	 * @param childEntity
	 */
	protected void preUpdateChild(Area entity, Area childEntity) {

	}

}
