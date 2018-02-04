/**
 * 
 */
package com.foundation.service.clinic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.modules.read.clinic.InspectItemDaoR;
import com.foundation.dao.modules.write.clinic.InspectItemDao;
import com.foundation.service.clinic.service.IInspectItemService;

/**
 * @ClassName: InspectItemServiceImpl
 * @Description: 检验项的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
@Service
public class InspectItemServiceImpl implements IInspectItemService {
//	private Log logger = LogFactory.getLog(InspectItemServiceImpl.class);

	@Autowired(required=false)
	private InspectItemDao inspectItemDao;
	@Autowired(required=false)
	private InspectItemDaoR inspectItemDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectItem inspectItem) throws Exception {
		long affectedRows = inspectItemDao.save(inspectItem);
		return 1 == affectedRows ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectItem inspectItem) throws Exception {
		inspectItemDao.update(inspectItem);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectItem queryById(String id) throws Exception {
		return inspectItemDaoR.queryById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectItem> queryPage(Map<String, Object> params, Page<InspectItem> page) throws Exception {
		List<InspectItem> list = inspectItemDaoR.queryPageList(params, page);
		page.setList(list);
		return page;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectItemDaoR.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		inspectItemDao.delete(id);
		return true;
	}

}
