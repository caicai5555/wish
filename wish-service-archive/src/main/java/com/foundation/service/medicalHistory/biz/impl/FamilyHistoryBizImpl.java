package com.foundation.service.medicalHistory.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.service.medicalHistory.biz.IFamilyHistoryBiz;
import com.foundation.service.medicalHistory.service.IFamilyHistoryService;

/**
 * 
* @ClassName: FamilyHistoryBizImpl 
* @Description: 家族病史业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class FamilyHistoryBizImpl implements IFamilyHistoryBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IFamilyHistoryService familyHistoryService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(FamilyHistory history) throws Exception {
		try {
			return familyHistoryService.save(history);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(FamilyHistory history) throws Exception {
		try {
			familyHistoryService.update(history);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FamilyHistory queryById(String id) throws Exception {
		try {
			return familyHistoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<FamilyHistory> queryPage(Map<String, Object> params,Page<FamilyHistory> pageBounds) throws Exception
			 {
		try {
			return familyHistoryService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FamilyHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return familyHistoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return familyHistoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			FamilyHistory history = new FamilyHistory();
			history.setId(id);
			history.setDelFlag(1);
			history.setDelDate(new Date());
			this.update(history);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			familyHistoryService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[FamilyHistoryBizImpl.realDel] error.", e);
		}
	}

}
