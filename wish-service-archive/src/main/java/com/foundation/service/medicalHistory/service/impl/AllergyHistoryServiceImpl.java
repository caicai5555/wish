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
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.dao.modules.read.medicalHistory.AllergyHistoryDaoR;
import com.foundation.dao.modules.write.medicalHistory.AllergyHistoryDao;
import com.foundation.service.medicalHistory.service.IAllergyHistoryService;
/**
 * 
* @ClassName: IAllergyHistoryService 
* @Description: 过敏史服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
@Service
public class AllergyHistoryServiceImpl implements IAllergyHistoryService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	AllergyHistoryDao allergyHistoryDao;
	@Autowired(required = false)
	AllergyHistoryDaoR allergyHistoryDaoR;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(AllergyHistory history) throws Exception {
		try {
			if (StringUtils.isBlank(history.getId())) {
				history.setId(IdGen.uuid());
			}
			long affectedRows = allergyHistoryDao.save(history);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(AllergyHistory history) throws Exception {
		try {
			allergyHistoryDao.update(history);
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AllergyHistory queryById(String id) throws Exception {
		try {
			return allergyHistoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<AllergyHistory> queryPage(Map<String, Object> params, Page<AllergyHistory> pageBounds)
			throws Exception {
		try {
			Page<AllergyHistory> pagination = pageBounds;
			List<AllergyHistory> list = allergyHistoryDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AllergyHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			allergyHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryServiceImpl.realDel] error.", e);
		}
	}

}
