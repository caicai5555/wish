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
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.dao.modules.read.medicalHistory.SurgeryHistoryDaoR;
import com.foundation.dao.modules.write.medicalHistory.SurgeryHistoryDao;
import com.foundation.service.medicalHistory.service.ISurgeryHistoryService;
/**
 * 
* @ClassName: ISurgeryHistoryService 
* @Description: 手术史服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
@Service
public class SurgeryHistoryServiceImpl implements ISurgeryHistoryService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	SurgeryHistoryDao surgeryHistoryDao;
	@Autowired(required = false)
	SurgeryHistoryDaoR surgeryHistoryDaoR;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(SurgeryHistory history) throws Exception {
		try {
			if (StringUtils.isBlank(history.getId())) {
				history.setId(IdGen.uuid());
			}
			long affectedRows = surgeryHistoryDao.save(history);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(SurgeryHistory history) throws Exception {
		try {
			surgeryHistoryDao.update(history);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SurgeryHistory queryById(String id) throws Exception {
		try {
			return surgeryHistoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<SurgeryHistory> queryPage(Map<String, Object> params, Page<SurgeryHistory> pageBounds)
			throws Exception {
		try {
			Page<SurgeryHistory> pagination = pageBounds;
			List<SurgeryHistory> list = surgeryHistoryDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SurgeryHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			surgeryHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryServiceImpl.realDel] error.", e);
		}
	}

}
