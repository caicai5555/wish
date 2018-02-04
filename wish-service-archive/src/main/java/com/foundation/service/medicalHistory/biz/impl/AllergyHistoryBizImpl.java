package com.foundation.service.medicalHistory.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryBiz;
import com.foundation.service.medicalHistory.service.IAllergyHistoryService;

/**
 * 
* @ClassName: AllergyHistoryBizImpl 
* @Description: 过敏史业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class AllergyHistoryBizImpl implements IAllergyHistoryBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IAllergyHistoryService allergyHistoryService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(AllergyHistory history) throws Exception {
		try {
			return allergyHistoryService.save(history);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(AllergyHistory history) throws Exception {
		try {
			allergyHistoryService.update(history);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AllergyHistory queryById(String id) throws Exception {
		try {
			return allergyHistoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<AllergyHistory> queryPage(Map<String, Object> params,Page<AllergyHistory> pageBounds) throws Exception
			 {
		try {
			return allergyHistoryService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AllergyHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			AllergyHistory history = new AllergyHistory();
			history.setId(id);
			history.setDelFlag(1);
			history.setDelDate(new Date());
			this.update(history);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			allergyHistoryService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryBizImpl.realDel] error.", e);
		}
	}

}
