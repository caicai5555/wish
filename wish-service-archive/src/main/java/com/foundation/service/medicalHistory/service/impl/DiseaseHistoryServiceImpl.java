package com.foundation.service.medicalHistory.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.medicalHistory.DiseaseHistory;
import com.foundation.dao.modules.read.medicalHistory.DiseaseHistoryDaoR;
import com.foundation.dao.modules.write.medicalHistory.DiseaseHistoryDao;
import com.foundation.service.medicalHistory.service.IDiseaseHistoryService;
/**
 * 
* @ClassName: IDiseaseHistoryService 
* @Description: 疾病史服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
@Service
public class DiseaseHistoryServiceImpl implements IDiseaseHistoryService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	DiseaseHistoryDao diseaseHistoryDao;
	@Autowired(required = false)
	DiseaseHistoryDaoR diseaseHistoryDaoR;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(DiseaseHistory history) throws Exception {
		try {
			if (StringUtils.isBlank(history.getId())) {
				history.setId(IdGen.uuid());
			}
			long affectedRows = diseaseHistoryDao.save(history);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(DiseaseHistory history) throws Exception {
		try {
			diseaseHistoryDao.update(history);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DiseaseHistory queryById(String id) throws Exception {
		try {
			return diseaseHistoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<DiseaseHistory> queryPage(Map<String, Object> params, Page<DiseaseHistory> pageBounds)
			throws Exception {
		try {
			Page<DiseaseHistory> pagination = pageBounds;
			List<DiseaseHistory> list = diseaseHistoryDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DiseaseHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return diseaseHistoryDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return diseaseHistoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			diseaseHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("[DiseaseHistoryServiceImpl.realDel] error.", e);
		}
	}

}
