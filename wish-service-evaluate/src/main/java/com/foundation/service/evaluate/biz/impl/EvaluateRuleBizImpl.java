package com.foundation.service.evaluate.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.service.evaluate.biz.IEvaluateRuleBiz;
import com.foundation.service.evaluate.service.IEvaluateRuleService;

/**
 * 
* @ClassName: EvaluateRuleBizImpl 
* @Description: 评估规则业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class EvaluateRuleBizImpl implements IEvaluateRuleBiz {

	private Log logger = LogFactory.getLog(EvaluateRuleBizImpl.class);
	@Autowired
	private IEvaluateRuleService evaluateRuleService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateRule rule) throws Exception {
		try {
			return evaluateRuleService.save(rule);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateRule rule) throws Exception {
		try {
			evaluateRuleService.update(rule);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateRule queryById(String id) throws Exception {
		try {
			return evaluateRuleService.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateRule> queryPage(Map<String, Object> params,Page<EvaluateRule> pageBounds) throws Exception {
		try {
			return evaluateRuleService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateRuleService.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateRule> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateRuleService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateRuleBizImpl.queryEntityList] error.", e);
		}
		return null;
	}
}
