package com.foundation.service.medicalHistory.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryBiz;
import com.foundation.service.medicalHistory.service.ISurgeryHistoryService;

/**
 * 
* @ClassName: SurgeryHistoryBizImpl 
* @Description: 手术史业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class SurgeryHistoryBizImpl implements ISurgeryHistoryBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISurgeryHistoryService surgeryHistoryService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(SurgeryHistory history) throws Exception {
		try {
			return surgeryHistoryService.save(history);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(SurgeryHistory history) throws Exception {
		try {
			surgeryHistoryService.update(history);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SurgeryHistory queryById(String id) throws Exception {
		try {
			return surgeryHistoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<SurgeryHistory> queryPage(Map<String, Object> params,Page<SurgeryHistory> pageBounds) throws Exception
			 {
		try {
			return surgeryHistoryService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SurgeryHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			SurgeryHistory history = new SurgeryHistory();
			history.setId(id);
			history.setDelFlag(1);
			history.setDelDate(new Date());
			this.update(history);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			surgeryHistoryService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryBizImpl.realDel] error.", e);
		}
	}

}
