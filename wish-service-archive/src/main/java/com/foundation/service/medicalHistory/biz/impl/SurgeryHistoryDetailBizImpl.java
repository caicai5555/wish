package com.foundation.service.medicalHistory.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;
import com.foundation.service.medicalHistory.biz.ISurgeryHistoryDetailBiz;
import com.foundation.service.medicalHistory.service.ISurgeryHistoryDetailService;
import com.google.common.collect.Maps;

/** 
* @ClassName: SurgeryHistoryDetailBizImpl 
* @Description: 手术史明细业务层
* @author chengchen 
* @date 2016年11月23日 上午11:13:04 
*  
*/
@Service
public class SurgeryHistoryDetailBizImpl implements ISurgeryHistoryDetailBiz {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private ISurgeryHistoryDetailService surgeryHistoryDetailService;

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(SurgeryHistoryDetail historyDetail) throws Exception {
		try {
			return surgeryHistoryDetailService.save(historyDetail);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(SurgeryHistoryDetail historyDetail) throws Exception {
		try {
			surgeryHistoryDetailService.update(historyDetail);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public SurgeryHistoryDetail queryById(String id) throws Exception {
		try {
			return surgeryHistoryDetailService.queryById(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Page<SurgeryHistoryDetail> queryPage(Map<String, Object> params, Page<SurgeryHistoryDetail> pageBounds)
			throws Exception {
		try {
			return surgeryHistoryDetailService.queryPage(params,pageBounds);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<SurgeryHistoryDetail> queryList(String historyId) throws Exception {
		try {
			if(StringUtils.isNotBlank(historyId)){
				Map<String,Object> params = Maps.newHashMap();
				params.put("surgeryHistoryId", historyId);
				return surgeryHistoryDetailService.queryList(params);
			}
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryDetailService.getCount(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			surgeryHistoryDetailService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.deleteReal] error.", e);
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
				params.put("surgeryHistoryId", historyId);
				surgeryHistoryDetailService.deleteByMap(params);
			}
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailBizImpl.deleteBySurgeryHistoryId] error.", e);
		}
	}

}
