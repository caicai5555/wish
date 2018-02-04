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
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.dao.modules.read.medicalHistory.DrugHistoryDetailDaoR;
import com.foundation.dao.modules.write.medicalHistory.DrugHistoryDetailDao;
import com.foundation.service.medicalHistory.service.IDrugHistoryDetailService;

/** 
* @ClassName: DrugHistoryDetailServiceimpl 
* @Description: 用药史明细服务层
* @author chengchen 
* @date 2016年11月22日 下午5:40:58 
*  
*/
@Service
public class DrugHistoryDetailServiceimpl implements IDrugHistoryDetailService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	DrugHistoryDetailDao drugHistoryDetailDao;
	@Autowired(required = false)
	DrugHistoryDetailDaoR drugHistoryDetailDaoR;
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(DrugHistoryDetail historyDetail) throws Exception {
		try {
			if (StringUtils.isBlank(historyDetail.getId())) {
				historyDetail.setId(IdGen.uuid());
			}
			long affectedRows = drugHistoryDetailDao.save(historyDetail);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(DrugHistoryDetail historyDetail) throws Exception {
		try {
			drugHistoryDetailDao.update(historyDetail);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public DrugHistoryDetail queryById(String id) throws Exception {
		try {
			return drugHistoryDetailDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.queryById] error.", e);
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
			Page<DrugHistoryDetail> pagination = pageBounds;
			List<DrugHistoryDetail> list = drugHistoryDetailDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<DrugHistoryDetail> queryList(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryDetailDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return drugHistoryDetailDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			drugHistoryDetailDao.delete(id);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteByMap(Map<String, Object> params) throws Exception {
		try {
			drugHistoryDetailDao.deleteByMap(params);
		} catch (Exception e) {
			logger.error("[DrugHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

}
