/**
 * 
 */
package com.foundation.service.clinic.biz.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListOrg;
import com.foundation.service.clinic.biz.IInspectListOrgBiz;
import com.foundation.service.clinic.service.IInspectListOrgService;
import com.google.common.collect.Maps;

/**
 * @ClassName: InspectListOrgBizImpl
 * @Description: 检验项与授权机构关系的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
@Service
public class InspectListOrgBizImpl implements IInspectListOrgBiz {
	private Log logger = LogFactory.getLog(InspectListOrgBizImpl.class);
	@Autowired
	private IInspectListOrgService inspectListOrgService;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectListOrg inspectListOrg) throws Exception {
		return inspectListOrgService.save(inspectListOrg);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectListOrg inspectListOrg) throws Exception {
		return inspectListOrgService.update(inspectListOrg);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectListOrg queryById(String id) throws Exception {
		return inspectListOrgService.queryById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectListOrg> queryPage(Map<String, Object> params, Page<InspectListOrg> page) throws Exception {
		if (params == null) params = Maps.newHashMap();
		return inspectListOrgService.queryPage(params, page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListOrgService.getCount(params);
	}

	@Override
	public boolean deleteReal(String id) throws Exception {
		return inspectListOrgService.deleteReal(id);
	}

}
