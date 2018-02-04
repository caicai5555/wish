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
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.dao.modules.read.medicalHistory.DrugHistoryDaoR;
import com.foundation.dao.modules.write.medicalHistory.DrugHistoryDao;
import com.foundation.service.medicalHistory.service.IDrugHistoryService;
/**
 * 
* @ClassName: IDrugHistoryService 
* @Description: 用药史服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
@Service
public class DrugHistoryServiceImpl implements IDrugHistoryService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	DrugHistoryDao drugHistoryDao;
	@Autowired(required = false)
	DrugHistoryDaoR drugHistoryDaoR;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(DrugHistory history) throws Exception {
		try {
			if (StringUtils.isBlank(history.getId())) {
				history.setId(IdGen.uuid());
			}
			long affectedRows = drugHistoryDao.save(history);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(DrugHistory history) throws Exception {
		try {
			drugHistoryDao.update(history);
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DrugHistory queryById(String id) throws Exception {
		try {
			return drugHistoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<DrugHistory> queryPage(Map<String, Object> params, Page<DrugHistory> pageBounds)
			throws Exception {
		try {
			Page<DrugHistory> pagination = pageBounds;
			List<DrugHistory> list = drugHistoryDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DrugHistory> queryList(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			drugHistoryDao.delete(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryServiceImpl.realDel] error.", e);
		}
	}

}
