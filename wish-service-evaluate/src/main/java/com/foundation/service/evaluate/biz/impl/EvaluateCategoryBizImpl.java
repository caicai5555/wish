package com.foundation.service.evaluate.biz.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.service.evaluate.biz.IEvaluateCategoryBiz;
import com.foundation.service.evaluate.service.IEvaluateCategoryService;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: EvaluateCategoryBizImpl 
* @Description: 评估分类业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class EvaluateCategoryBizImpl implements IEvaluateCategoryBiz {

	private Log logger = LogFactory.getLog(EvaluateCategoryBizImpl.class);
	@Autowired
	private IEvaluateCategoryService evaluateCategoryService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateCategory category) throws Exception {
		try {
			return evaluateCategoryService.save(category);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.save] error.", e);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateCategory category) throws Exception {
		try {
			evaluateCategoryService.update(category);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.update] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateCategory queryById(String id) throws Exception {
		try {
			return evaluateCategoryService.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.queryById] error.", e);
		}
		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateCategoryService.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateCategory> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateCategoryService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.queryEntityList] error.", e);
		}
		return null;
	}

	@Override
	public boolean delete(String id) throws Exception {
		try {
			EvaluateCategory category = new EvaluateCategory();
			category.setId(id);
			category.setDelFlag(1);
			category.setDelDate(new Date());
			this.update(category);
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.delete] error.", e);
		}
		return false;
	}

	@Override
	public EvaluateCategory queryByEnname(String enname) throws Exception {
		try {
			Assert.notNull(enname);
			Map<String,Object> params = Maps.newHashMap();
			params.put("enname", enname);
			List<EvaluateCategory> list = this.queryList(params);
			if(null!=list&&list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			logger.error("[EvaluateCategoryBizImpl.queryByEnname] error.", e);
		}
		return null;
	}
}
