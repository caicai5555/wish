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
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;
import com.foundation.dao.modules.read.medicalHistory.SurgeryHistoryDetailDaoR;
import com.foundation.dao.modules.write.medicalHistory.SurgeryHistoryDetailDao;
import com.foundation.service.medicalHistory.service.ISurgeryHistoryDetailService;

/** 
* @ClassName: SurgeryHistoryDetailServiceimpl 
* @Description: 手术史明细服务层
* @author chengchen 
* @date 2016年11月22日 下午5:40:58 
*  
*/
@Service
public class SurgeryHistoryDetailServiceimpl implements ISurgeryHistoryDetailService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired(required = false)
	SurgeryHistoryDetailDao surgeryHistoryDetailDao;
	@Autowired(required = false)
	SurgeryHistoryDetailDaoR surgeryHistoryDetailDaoR;
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean save(SurgeryHistoryDetail historyDetail) throws Exception {
		try {
			if (StringUtils.isBlank(historyDetail.getId())) {
				historyDetail.setId(IdGen.uuid());
			}
			long affectedRows = surgeryHistoryDetailDao.save(historyDetail);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.save] error.", e);
		}
		return false;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void update(SurgeryHistoryDetail historyDetail) throws Exception {
		try {
			surgeryHistoryDetailDao.update(historyDetail);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.update] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public SurgeryHistoryDetail queryById(String id) throws Exception {
		try {
			return surgeryHistoryDetailDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.queryById] error.", e);
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
			Page<SurgeryHistoryDetail> pagination = pageBounds;
			List<SurgeryHistoryDetail> list = surgeryHistoryDetailDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public List<SurgeryHistoryDetail> queryList(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryDetailDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.queryList] error.", e);
		}
		return null;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return surgeryHistoryDetailDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			surgeryHistoryDetailDao.delete(id);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void deleteByMap(Map<String, Object> params) throws Exception {
		try {
			surgeryHistoryDetailDao.deleteByMap(params);
		} catch (Exception e) {
			logger.error("[SurgeryHistoryDetailServiceimpl.realDel] error.", e);
		}
	}

}
