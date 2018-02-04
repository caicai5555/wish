package com.foundation.service.medicalHistory.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.DiseaseHistory;
import com.foundation.service.medicalHistory.biz.IDiseaseHistoryBiz;
import com.foundation.service.medicalHistory.service.IDiseaseHistoryService;

/**
 * 
* @ClassName: DiseaseHistoryBizImpl 
* @Description: 疾病史业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class DiseaseHistoryBizImpl implements IDiseaseHistoryBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IDiseaseHistoryService diseaseHistoryService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(DiseaseHistory history) throws Exception {
		try {
			return diseaseHistoryService.save(history);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(DiseaseHistory history) throws Exception {
		try {
			diseaseHistoryService.update(history);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DiseaseHistory queryById(String id) throws Exception {
		try {
			return diseaseHistoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<DiseaseHistory> queryPage(Map<String, Object> params,Page<DiseaseHistory> pageBounds) throws Exception
			 {
		try {
			return diseaseHistoryService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DiseaseHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return diseaseHistoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return diseaseHistoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			DiseaseHistory history = new DiseaseHistory();
			history.setId(id);
			history.setDelFlag(1);
			history.setDelDate(new Date());
			this.update(history);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			diseaseHistoryService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryBizImpl.realDel] error.", e);
		}
	}

}
