package com.foundation.service.evaluate.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.service.evaluate.biz.IEvaluateConclusionContentBiz;
import com.foundation.service.evaluate.service.IEvaluateConclusionContentService;

/**
 * 
* @ClassName: EvaluateConclusionContentBizImpl 
* @Description: 评估结论内容业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class EvaluateConclusionContentBizImpl implements IEvaluateConclusionContentBiz {

	private Log logger = LogFactory.getLog(EvaluateConclusionContentBizImpl.class);
	@Autowired
	private IEvaluateConclusionContentService evaluateConclusionContentService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateConclusionContent conclusion) throws Exception {
		try {
			return evaluateConclusionContentService.save(conclusion);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateConclusionContent conclusion) throws Exception {
		try {
			evaluateConclusionContentService.update(conclusion);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentBizImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateConclusionContent queryById(String id) throws Exception {
		try {
			return evaluateConclusionContentService.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentBizImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateConclusionContentService.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateConclusionContent> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateConclusionContentService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentBizImpl.queryEntityList] error.", e);
		}
		return null;
	}
}
