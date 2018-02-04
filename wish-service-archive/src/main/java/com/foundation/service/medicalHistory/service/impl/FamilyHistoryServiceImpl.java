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
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.dao.modules.read.medicalHistory.FamilyHistoryDaoR;
import com.foundation.dao.modules.write.medicalHistory.FamilyHistoryDao;
import com.foundation.service.medicalHistory.service.IFamilyHistoryService;
/**
 * 
* @ClassName: IFamilyHistoryService 
* @Description: 家族病史服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
@Service
public class FamilyHistoryServiceImpl implements IFamilyHistoryService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	FamilyHistoryDao familyHistoryDao;
	@Autowired(required = false)
	FamilyHistoryDaoR familyHistoryDaoR;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(FamilyHistory history) throws Exception {
		try {
			if (StringUtils.isBlank(history.getId())) {
				history.setId(IdGen.uuid());
			}
			long affectedRows = familyHistoryDao.save(history);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(FamilyHistory history) throws Exception {
		try {
			familyHistoryDao.update(history);
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FamilyHistory queryById(String id) throws Exception {
		try {
			return familyHistoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<FamilyHistory> queryPage(Map<String, Object> params, Page<FamilyHistory> pageBounds)
			throws Exception {
		try {
			Page<FamilyHistory> pagination = pageBounds;
			List<FamilyHistory> list = familyHistoryDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FamilyHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return familyHistoryDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return familyHistoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			familyHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("[FamilyHistoryServiceImpl.realDel] error.", e);
		}
	}

}
