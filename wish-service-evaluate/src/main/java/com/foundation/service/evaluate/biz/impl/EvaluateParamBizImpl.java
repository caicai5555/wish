package com.foundation.service.evaluate.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.service.evaluate.biz.IEvaluateParamBiz;
import com.foundation.service.evaluate.service.IEvaluateParamService;

/**
 * 
* @ClassName: EvaluateParamBizImpl 
* @Description: 评估参数业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class EvaluateParamBizImpl implements IEvaluateParamBiz {

	private Log logger = LogFactory.getLog(EvaluateParamBizImpl.class);
	@Autowired
	private IEvaluateParamService evaluateParamService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateParam param) throws Exception {
		try {
			return evaluateParamService.save(param);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateParam param) throws Exception {
		try {
			evaluateParamService.update(param);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateParam queryById(String id) throws Exception {
		try {
			return evaluateParamService.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateParam> queryPage(Map<String, Object> params,Page<EvaluateParam> pageBounds) throws Exception {
		try {
			return evaluateParamService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateParamService.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateParam> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateParamService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateParamBizImpl.queryEntityList] error.", e);
		}
		return null;
	}
}
