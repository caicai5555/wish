package com.foundation.service.evaluate.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.modules.read.evaluate.EvaluateConclusionContentDaoR;
import com.foundation.dao.modules.write.evaluate.EvaluateConclusionContentDao;
import com.foundation.service.evaluate.service.IEvaluateConclusionContentService;

/**
 * 
 * @ClassName: EvaluateConclusionContentServiceImpl
 * @Description: 评估结论内容服务层
 * @author chengchen
 * @date 2016年10月12日 上午10:58:06
 *
 */
@Service
public class EvaluateConclusionContentServiceImpl implements IEvaluateConclusionContentService {
	private Log logger = LogFactory.getLog(EvaluateConclusionContentServiceImpl.class);

	@Autowired(required=false)
	EvaluateConclusionContentDao evaluateConclusionContentDao;
	@Autowired(required=false)
	EvaluateConclusionContentDaoR evaluateConclusionContentDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateConclusionContent conclusion) throws Exception {
		try {
			if(StringUtils.isBlank(conclusion.getId())){
				conclusion.setId(IdGen.uuid());
			}
			long affectedRows = evaluateConclusionContentDao.save(conclusion);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateConclusionContent conclusion) throws Exception {
		try {
			evaluateConclusionContentDao.update(conclusion);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateConclusionContent queryById(String id) throws Exception {
		try {
			return evaluateConclusionContentDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentServiceImpl.queryById] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateConclusionContentDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateConclusionContentServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateConclusionContent> queryList(Map<String, Object> params) throws Exception {
		try{
			return evaluateConclusionContentDaoR.queryList(params);
		}catch (Exception e) {
			logger.error("[EvaluateConclusionContentServiceImpl.queryList] error.", e);
		}
		return null;
	}
}
