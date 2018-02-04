/**
 * 
 */
package com.foundation.service.clinic.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListItem;
import com.foundation.service.clinic.biz.IInspectListItemBiz;
import com.foundation.service.clinic.service.IInspectListItemService;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: InspectListItemBizImpl
 * @Description: 检验单与检验项关系的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
@Service
public class InspectListItemBizImpl implements IInspectListItemBiz {
	private Log logger = LogFactory.getLog(InspectListItemBizImpl.class);
	@Autowired
	private IInspectListItemService inspectListItemService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectListItem inspectListItem) throws Exception {
		return inspectListItemService.save(inspectListItem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectListItem inspectListItem) throws Exception {
		return inspectListItemService.update(inspectListItem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectListItem queryById(String id) throws Exception {
		return inspectListItemService.queryById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectListItem> queryPage(Map<String, Object> params, Page<InspectListItem> page) throws Exception {
		if (params == null) params = Maps.newHashMap();
		return inspectListItemService.queryPage(params, page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListItemService.getCount(params);
	}

	@Override
	public boolean deleteReal(String id) throws Exception {
		return inspectListItemService.deleteReal(id);
	}

	@Override
	public List<InspectListItem> queryAll(Map<String, Object> params) throws Exception {
		return queryPage(params, new Page<InspectListItem>(1, 100)).getList();
	}

	@Override
	public int updateByMap(Map<String, Object> params) throws Exception {
		return inspectListItemService.updateByMap(params);
	}

}
