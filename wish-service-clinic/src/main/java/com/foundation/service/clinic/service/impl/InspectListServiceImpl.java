/**
 * 
 */
package com.foundation.service.clinic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.dao.modules.read.clinic.InspectListDaoR;
import com.foundation.dao.modules.write.clinic.InspectListDao;
import com.foundation.service.clinic.service.IInspectListService;

/**
 * @ClassName: InspectListServiceImpl
 * @Description: 检验单的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
@Service
public class InspectListServiceImpl implements IInspectListService {
//	private Log logger = LogFactory.getLog(InspectListServiceImpl.class);

	@Autowired(required=false)
	private InspectListDao inspectListDao;
	@Autowired(required=false)
	private InspectListDaoR inspectListDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectList inspectList) throws Exception {
		long affectedRows = inspectListDao.save(inspectList);
		return 1 == affectedRows ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectList inspectList) throws Exception {
		inspectListDao.update(inspectList);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectList queryById(String id) throws Exception {
		return inspectListDaoR.queryById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectList> queryPage(Map<String, Object> params, Page<InspectList> page) throws Exception {
		List<InspectList> list = inspectListDaoR.queryPageList(params, page);
		page.setList(list);
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListDaoR.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		inspectListDao.delete(id);
		return true;
	}
}
