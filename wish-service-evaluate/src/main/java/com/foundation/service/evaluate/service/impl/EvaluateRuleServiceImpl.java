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
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.dao.modules.read.evaluate.EvaluateRuleDaoR;
import com.foundation.dao.modules.write.evaluate.EvaluateRuleDao;
import com.foundation.service.evaluate.service.IEvaluateRuleService;

/**
 * 
 * @ClassName: EvaluateRuleServiceImpl
 * @Description: 评估规则服务层
 * @author chengchen
 * @date 2016年10月12日 上午10:58:06
 *
 */
@Service
public class EvaluateRuleServiceImpl implements IEvaluateRuleService {
	private Log logger = LogFactory.getLog(EvaluateRuleServiceImpl.class);

	@Autowired(required=false)
	EvaluateRuleDao evaluateRuleDao;
	@Autowired(required=false)
	EvaluateRuleDaoR evaluateRuleDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateRule rule) throws Exception {
		try {
			if(StringUtils.isBlank(rule.getId())){
				rule.setId(IdGen.uuid());
			}
			long affectedRows = evaluateRuleDao.save(rule);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateRule rule) throws Exception {
		try {
			evaluateRuleDao.update(rule);
		} catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateRule queryById(String id) throws Exception {
		try {
			return evaluateRuleDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateRule> queryPage(Map<String, Object> params, Page<EvaluateRule> pageBounds) throws Exception {
		try {
			Page<EvaluateRule> pagination = pageBounds;
			List<EvaluateRule> list = evaluateRuleDaoR.queryPage(params, pageBounds);
			pagination.setList(list);
			return pagination;
		} catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateRuleDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateRule> queryList(Map<String, Object> params) throws Exception {
		try{
			return evaluateRuleDaoR.queryList(params);
		}catch (Exception e) {
			logger.error("[EvaluateRuleServiceImpl.queryList] error.", e);
		}
		return null;
	}
}
