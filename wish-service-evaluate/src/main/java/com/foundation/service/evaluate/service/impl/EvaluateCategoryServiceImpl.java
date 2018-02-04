package com.foundation.service.evaluate.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.dao.modules.read.evaluate.EvaluateCategoryDaoR;
import com.foundation.dao.modules.write.evaluate.EvaluateCategoryDao;
import com.foundation.service.evaluate.service.IEvaluateCategoryService;

/**
 * 
 * @ClassName: EvaluateCategoryServiceImpl
 * @Description: 评估分类服务层
 * @author chengchen
 * @date 2016年10月12日 上午10:58:06
 *
 */
@Service
public class EvaluateCategoryServiceImpl implements IEvaluateCategoryService {
	private Log logger = LogFactory.getLog(EvaluateCategoryServiceImpl.class);

	@Autowired(required=false)
	EvaluateCategoryDao evaluateCategoryDao;
	@Autowired(required=false)
	EvaluateCategoryDaoR evaluateCategoryDaoR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateCategory category) throws Exception {
		try {
			if(StringUtils.isBlank(category.getId())){
				category.setId(IdGen.uuid());
			}
			long affectedRows = evaluateCategoryDao.save(category);
			if (1 == affectedRows) {
				return true;
			}
		} catch (Exception e) {
			logger.error("[EvaluateCategoryServiceImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateCategory category) throws Exception {
		try {
			evaluateCategoryDao.update(category);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryServiceImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateCategory queryById(String id) throws Exception {
		try {
			return evaluateCategoryDaoR.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryServiceImpl.queryById] error.", e);
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateCategoryDaoR.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryServiceImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateCategory> queryList(Map<String, Object> params) throws Exception {
		try{
			return evaluateCategoryDaoR.queryList(params);
		}catch (Exception e) {
			logger.error("[EvaluateCategoryServiceImpl.queryList] error.", e);
		}
		return null;
	}
}
