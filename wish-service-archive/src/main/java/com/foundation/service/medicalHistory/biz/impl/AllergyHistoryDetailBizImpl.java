package com.foundation.service.medicalHistory.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.service.medicalHistory.biz.IAllergyHistoryDetailBiz;
import com.foundation.service.medicalHistory.service.IAllergyHistoryDetailService;
import com.google.common.collect.Maps;

/** 
* @ClassName: AllergyHistoryDetailBizImpl 
* @Description: 过敏史明细业务层
* @author chengchen 
* @date 2016年11月23日 上午11:13:04 
*  
*/
@Service
public class AllergyHistoryDetailBizImpl implements IAllergyHistoryDetailBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IAllergyHistoryDetailService allergyHistoryDetailService;

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(AllergyHistoryDetail historyDetail) throws Exception {
		try {
			return allergyHistoryDetailService.save(historyDetail);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(AllergyHistoryDetail historyDetail) throws Exception {
		try {
			allergyHistoryDetailService.update(historyDetail);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public AllergyHistoryDetail queryById(String id) throws Exception {
		try {
			return allergyHistoryDetailService.queryById(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.queryById] error.", e);
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
			return allergyHistoryDetailService.queryPage(params,pageBounds);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<AllergyHistoryDetail> queryList(String historyId) throws Exception {
		try {
			if(StringUtils.isNotBlank(historyId)){
				Map<String,Object> params = Maps.newHashMap();
				params.put("allergyHistoryId", historyId);
				return allergyHistoryDetailService.queryList(params);
			}
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return allergyHistoryDetailService.getCount(params);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			allergyHistoryDetailService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.deleteReal] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteByHistoryId(String historyId) throws Exception {
		try {
			if(StringUtils.isNotBlank(historyId)){
				Map<String,Object> params = Maps.newHashMap();
				params.put("allergyHistoryId", historyId);
				allergyHistoryDetailService.deleteByMap(params);
			}
		} catch (Exception e) {
			logger.error("[AllergyHistoryDetailBizImpl.deleteBySurgeryHistoryId] error.", e);
		}
	}

}
