/**
 * 
 */
package com.foundation.service.clinic.biz.impl;

import com.foundation.common.bean.Constants;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.dao.entity.clinic.InspectListItem;
import com.foundation.service.clinic.biz.IInspectItemBiz;
import com.foundation.service.clinic.biz.IInspectListBiz;
import com.foundation.service.clinic.biz.IInspectListItemBiz;
import com.foundation.service.clinic.service.IInspectListService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: InspectListBizImpl
 * @Description: 检验项的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
@Service
public class InspectListBizImpl implements IInspectListBiz {
	private Log logger = LogFactory.getLog(InspectListBizImpl.class);
	@Autowired
	private IInspectListService inspectListService;
	@Autowired
	private IInspectListItemBiz inspectListItemBiz;
	@Autowired
	private IInspectItemBiz inspectItemBiz;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectList inspectList) throws Exception {
		return inspectListService.save(inspectList);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(InspectList inspectList, String itemIds) throws Exception {
		if (StringUtils.isNotEmpty(itemIds)) {
			for (String itemId : itemIds.split(",")) {
				InspectListItem listItem = new InspectListItem();
				listItem.setId(IdGen.uuid());
				listItem.setInspectName(inspectList.getInspectName());
				listItem.setItemId(itemId);
				listItem.setListId(inspectList.getId());
				inspectListItemBiz.save(listItem);
			}
		}
		return inspectListService.save(inspectList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean update(InspectList inspectList) throws Exception {
		return inspectListService.update(inspectList);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InspectList queryById(String id) throws Exception {
		return inspectListService.queryById(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<InspectList> queryPage(Map<String, Object> params, Page<InspectList> page) throws Exception {
		if (params == null) params = Maps.newHashMap();
		return inspectListService.queryPage(params, page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return inspectListService.getCount(params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteReal(String id) throws Exception {
		return inspectListService.deleteReal(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<InspectItem> queryItem(String inspectId) throws Exception {
		List<InspectItem> itemList = null;
		HashMap<String, Object> params = Maps.newHashMap();
		params.put("listId", inspectId);
		List<InspectListItem> listItems = inspectListItemBiz.queryAll(params);
		if (listItems !=null && listItems.size() > 0) {
			ArrayList<String> itemIds = Lists.newArrayList();
			for (InspectListItem listItem : listItems) {
				itemIds.add(listItem.getItemId());
			}
			params.clear();
			params.put("ids", itemIds);
			itemList = inspectItemBiz.queryAll(params);
		}
		return itemList;
	}

	@Override
	public boolean update(InspectList inspectList, String itemIdsNew, String itemIdsDel) throws Exception {
		if (StringUtils.isNotEmpty(itemIdsDel)) {
			HashMap<String, Object> map = Maps.newHashMap();
			map.put("itemIds", Arrays.asList(itemIdsDel.split(",")));
			map.put("listIds", Arrays.asList(inspectList.getId()));
			map.put("delFlag", Constants.DEL_FLAG_DELETE);
			inspectListItemBiz.updateByMap(map);
		}
		if (StringUtils.isNotEmpty(itemIdsNew)) {
			for (String id : itemIdsNew.split(",")) {
				InspectListItem entity = new InspectListItem();
				entity.setId(IdGen.uuid());
				entity.setListId(inspectList.getId());
				entity.setInspectName(inspectList.getInspectName());
				entity.setItemId(id);
				inspectListItemBiz.save(entity);
			}
		}
		return update(inspectList);
	}

}
