package com.foundation.service.evaluate.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.modules.read.evaluate.EvaluateInfoDaoR;
import com.foundation.dao.modules.write.evaluate.EvaluateInfoDao;
import com.foundation.service.evaluate.service.IEvaluateInfoService;

/**
 * 
 * @ClassName: EvaluateInfoServiceImpl
 * @Description: 评估信息服务层
 * @author chengchen
 * @date 2016年10月12日 上午10:58:06
 *
 */
@Service
public class EvaluateInfoServiceImpl implements IEvaluateInfoService {
	private Log logger = LogFactory.getLog(EvaluateInfoServiceImpl.class);

	@Autowired(required = false)
	EvaluateInfoDao evaluateInfoDao;
	@Autowired(required = false)
	EvaluateInfoDaoR evaluateInfoDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateInfo info) throws Exception {
		try {
			if (StringUtils.isBlank(info.getId())) {
				info.setId(IdGen.uuid());
			}
			long affectedRows = evaluateInfoDao.save(info);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateInfo info) throws Exception {
		try {
			evaluateInfoDao.update(info);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateInfo queryById(String id) throws Exception {
		try {
			return evaluateInfoDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateInfo> queryPage(Map<String, Object> params, Page<EvaluateInfo> pageBounds) throws Exception {
		try {
			Page<EvaluateInfo> pagination = pageBounds;
			List<EvaluateInfo> list = evaluateInfoDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateInfo> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateInfoDaoR.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateInfoDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			evaluateInfoDao.delete(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.realDel] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTimes(String id) throws Exception {
		try {
			evaluateInfoDao.updateTimes(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoServiceImpl.updateTimes] error.", e);
		}
	}
}
