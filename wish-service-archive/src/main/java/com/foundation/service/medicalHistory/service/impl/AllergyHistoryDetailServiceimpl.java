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
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.dao.modules.read.medicalHistory.AllergyHistoryDetailDaoR;
import com.foundation.dao.modules.write.medicalHistory.AllergyHistoryDetailDao;
import com.foundation.service.medicalHistory.service.IAllergyHistoryDetailService;

/** 
* @ClassName: AllergyHistoryDetailServiceimpl 
* @Description: 过敏史明细服务层
* @author chengchen 
* @date 2016年11月22日 下午5:40:58 
*  
*/
@Service
public class AllergyHistoryDetailServiceimpl implements IAllergyHistoryDetailService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	AllergyHistoryDetailDao allergyHistoryDetailDao;
	@Autowired(required = false)
	AllergyHistoryDetailDaoR allergyHistoryDetailDaoR;
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(AllergyHistoryDetail historyDetail) throws Exception {
		try {
			if (StringUtils.isBlank(historyDetail.getId())) {
				historyDetail.setId(IdGen.uuid());
			}
			long affectedRows = allergyHistoryDetailDao.save(historyDetail);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(AllergyHistoryDetail historyDetail) throws Exception {
		try {
			allergyHistoryDetailDao.update(historyDetail);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public AllergyHistoryDetail queryById(String id) throws Exception {
		try {
			return allergyHistoryDetailDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.queryById] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Page<AllergyHistoryDetail> queryPage(Map<String, Object> params, Page<AllergyHistoryDetail> pageBounds)
			throws Exception {
		try {
			Page<AllergyHistoryDetail> pagination = pageBounds;
			List<AllergyHistoryDetail> list = allergyHistoryDetailDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<AllergyHistoryDetail> queryList(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryDetailDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryDetailDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			allergyHistoryDetailDao.delete(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteByMap(Map<String, Object> params) throws Exception {
		try {
			allergyHistoryDetailDao.deleteByMap(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

}
