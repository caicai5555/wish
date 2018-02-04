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
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.modules.read.evaluate.EvaluateParamDaoR;
import com.foundation.dao.modules.write.evaluate.EvaluateParamDao;
import com.foundation.service.evaluate.service.IEvaluateParamService;

/**
 * 
 * @ClassName: EvaluateParamServiceImpl
 * @Description: 评估参数服务层
 * @author chengchen
 * @date 2016年10月12日 上午10:58:06
 *
 */
@Service
public class EvaluateParamServiceImpl implements IEvaluateParamService {
	private Log logger = LogFactory.getLog(EvaluateParamServiceImpl.class);

	@Autowired(required=false)
	EvaluateParamDao evaluateParamDao;
	@Autowired(required=false)
	EvaluateParamDaoR evaluateParamDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateParam param) throws Exception {
		try {
			if(StringUtils.isBlank(param.getId())){
				param.setId(IdGen.uuid());
			}
			long affectedRows = evaluateParamDao.save(param);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateParam param) throws Exception {
		try {
			evaluateParamDao.update(param);
		} catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateParam queryById(String id) throws Exception {
		try {
			return evaluateParamDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateParam> queryPage(Map<String, Object> params, Page<EvaluateParam> pageBounds) throws Exception {
		try {
			Page<EvaluateParam> pagination = pageBounds;
			List<EvaluateParam> list = evaluateParamDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateParamDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateParam> queryList(Map<String, Object> params) throws Exception {
		try{
			return evaluateParamDaoR.queryList(params);
		}catch (Exception e) {
			logger.error("[EvaluateParamServiceImpl.queryList] error.", e);
		}
		return null;
	}
}
