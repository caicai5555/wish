package com.foundation.service.medicalHistory.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.service.medicalHistory.biz.IDrugHistoryBiz;
import com.foundation.service.medicalHistory.service.IDrugHistoryService;

/**
 * 
* @ClassName: DrugHistoryBizImpl 
* @Description: 用药史业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class DrugHistoryBizImpl implements IDrugHistoryBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IDrugHistoryService drugHistoryService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(DrugHistory history) throws Exception {
		try {
			return drugHistoryService.save(history);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(DrugHistory history) throws Exception {
		try {
			drugHistoryService.update(history);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrugHistory queryById(String id) throws Exception {
		try {
			return drugHistoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<DrugHistory> queryPage(Map<String, Object> params,Page<DrugHistory> pageBounds) throws Exception
			 {
		try {
			return drugHistoryService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DrugHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			DrugHistory history = new DrugHistory();
			history.setId(id);
			history.setDelFlag(1);
			history.setDelDate(new Date());
			this.update(history);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			drugHistoryService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryBizImpl.realDel] error.", e);
		}
	}

}
