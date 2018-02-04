package com.foundation.service.medicalHistory.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.service.medicalHistory.biz.IDrugHistoryDetailBiz;
import com.foundation.service.medicalHistory.service.IDrugHistoryDetailService;
import com.google.common.collect.Maps;

/** 
* @ClassName: DrugHistoryDetailBizImpl 
* @Description: 用药史明细业务层
* @author chengchen 
* @date 2016年11月23日 上午11:13:04 
*  
*/
@Service
public class DrugHistoryDetailBizImpl implements IDrugHistoryDetailBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IDrugHistoryDetailService drugHistoryDetailService;

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(DrugHistoryDetail historyDetail) throws Exception {
		try {
			return drugHistoryDetailService.save(historyDetail);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(DrugHistoryDetail historyDetail) throws Exception {
		try {
			drugHistoryDetailService.update(historyDetail);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public DrugHistoryDetail queryById(String id) throws Exception {
		try {
			return drugHistoryDetailService.queryById(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Page<DrugHistoryDetail> queryPage(Map<String, Object> params, Page<DrugHistoryDetail> pageBounds)
			throws Exception {
		try {
			return drugHistoryDetailService.queryPage(params,pageBounds);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<DrugHistoryDetail> queryList(String historyId) throws Exception {
		try {
			if(StringUtils.isNotBlank(historyId)){
				Map<String,Object> params = Maps.newHashMap();
				params.put("drugHistoryId", historyId);
				return drugHistoryDetailService.queryList(params);
			}
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryDetailService.getCount(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			drugHistoryDetailService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.deleteReal] error.", e);
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
				params.put("drugHistoryId", historyId);
				drugHistoryDetailService.deleteByMap(params);
			}
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailBizImpl.deleteBySurgeryHistoryId] error.", e);
		}
	}

}
