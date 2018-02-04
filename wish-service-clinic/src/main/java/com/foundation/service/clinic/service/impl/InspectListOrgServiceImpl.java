/**
 * 
 */
package com.foundation.service.clinic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListOrg;
import com.foundation.dao.modules.read.clinic.InspectListOrgDaoR;
import com.foundation.dao.modules.write.clinic.InspectListOrgDao;
import com.foundation.service.clinic.service.IInspectListOrgService;

/**
 * @ClassName: InspectListOrgServiceImpl
 * @Description: 检验单与授权机构关系的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
@Service
public class InspectListOrgServiceImpl implements IInspectListOrgService {
//	private Log logger = LogFactory.getLog(InspectListOrgServiceImpl.class);

	@Autowired(required=false)
	private InspectListOrgDao inspectListOrgDao;
	@Autowired(required=false)
	private InspectListOrgDaoR inspectListOrgDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectListOrg inspectListOrg) throws Exception {
		long affectedRows = inspectListOrgDao.save(inspectListOrg);
		return 1 == affectedRows ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectListOrg inspectListOrg) throws Exception {
		inspectListOrgDao.update(inspectListOrg);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectListOrg queryById(String id) throws Exception {
		return inspectListOrgDaoR.queryById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectListOrg> queryPage(Map<String, Object> params, Page<InspectListOrg> page) throws Exception {
		List<InspectListOrg> list = inspectListOrgDaoR.queryPageList(params, page);
		page.setList(list);
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListOrgDaoR.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		inspectListOrgDao.delete(id);
		return true;
	}
}
