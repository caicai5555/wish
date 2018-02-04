/**
 * 
 */
package com.foundation.service.clinic.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.service.clinic.biz.IInspectItemBiz;
import com.foundation.service.clinic.service.IInspectItemService;
import com.google.common.collect.Maps;

/**
 * @ClassName: InspectItemBizImpl
 * @Description: 检验项的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
@Service
public class InspectItemBizImpl implements IInspectItemBiz {
	private Log logger = LogFactory.getLog(InspectItemBizImpl.class);
	@Autowired
	private IInspectItemService inspectItemService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectItem inspectItem) throws Exception {
		return inspectItemService.save(inspectItem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectItem inspectItem) throws Exception {
		return inspectItemService.update(inspectItem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectItem queryById(String id) throws Exception {
		return inspectItemService.queryById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectItem> queryPage(Map<String, Object> params, Page<InspectItem> page) throws Exception {
		if (params == null) params = Maps.newHashMap();
		return inspectItemService.queryPage(params, page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectItemService.getCount(params);
	}

	@Override
	public boolean deleteReal(String id) throws Exception {
		return inspectItemService.deleteReal(id);
	}

	@Override
	public Page<InspectItem> queryPage(Map<String, Object> params, int pageNo, int pageSize) throws Exception {
		return queryPage(params, new Page<InspectItem>(pageNo, pageSize));
	}

	@Override
	public Page<InspectItem> queryPage(Map<String, Object> params) throws Exception {
		return queryPage(params, new Page<InspectItem>());
	}

	@Override
	public List<InspectItem> queryAll(Map<String, Object> params) throws Exception {
		return queryPage(params, 1, 100).getList();
	}

}
