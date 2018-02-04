/**
 * 
 */
package com.foundation.service.clinic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListItem;
import com.foundation.dao.modules.read.clinic.InspectListItemDaoR;
import com.foundation.dao.modules.write.clinic.InspectListItemDao;
import com.foundation.service.clinic.service.IInspectListItemService;

/**
 * @ClassName: InspectListItemServiceImpl
 * @Description: 检验单与检验项关系的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
@Service
public class InspectListItemServiceImpl implements IInspectListItemService {
//	private Log logger = LogFactory.getLog(InspectListItemServiceImpl.class);

	@Autowired(required=false)
	private InspectListItemDao inspectListItemDao;
	@Autowired(required=false)
	private InspectListItemDaoR inspectListItemDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectListItem inspectListItem) throws Exception {
		long affectedRows = inspectListItemDao.save(inspectListItem);
		return 1 == affectedRows ? true : false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectListItem inspectListItem) throws Exception {
		inspectListItemDao.update(inspectListItem);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectListItem queryById(String id) throws Exception {
		return inspectListItemDaoR.queryById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectListItem> queryPage(Map<String, Object> params, Page<InspectListItem> page) throws Exception {
		List<InspectListItem> list = inspectListItemDaoR.queryPageList(params, page);
		page.setList(list);
		return page;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListItemDaoR.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		inspectListItemDao.delete(id);
		return true;
	}

	@Override
	public int updateByMap(Map<String, Object> params) throws Exception {
		return inspectListItemDao.updateByMap(params);
	}
}
