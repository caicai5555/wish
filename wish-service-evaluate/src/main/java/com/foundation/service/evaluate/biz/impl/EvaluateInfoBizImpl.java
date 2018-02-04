package com.foundation.service.evaluate.biz.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.service.evaluate.biz.IEvaluateInfoBiz;
import com.foundation.service.evaluate.common.Constants;
import com.foundation.service.evaluate.common.EvaluateUtils;
import com.foundation.service.evaluate.common.PregnantResultKeyEnum;
import com.foundation.service.evaluate.common.RuleEnum;
import com.foundation.service.evaluate.common.StatusEnum;
import com.foundation.service.evaluate.service.IEvaluateConclusionContentService;
import com.foundation.service.evaluate.service.IEvaluateInfoService;
import com.foundation.service.evaluate.service.IEvaluateParamService;
import com.foundation.service.evaluate.service.IEvaluateRuleService;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.Param;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.RuleModel;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: EvaluateInfoBizImpl 
* @Description: 评估信息业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:17:40 
*
 */
@Service
public class EvaluateInfoBizImpl implements IEvaluateInfoBiz {
	private Log logger = LogFactory.getLog(EvaluateInfoBizImpl.class);
	@Autowired
	private IEvaluateInfoService evaluateInfoService;
	@Autowired
	private IEvaluateParamService evaluateParamService;
	@Autowired
	private IEvaluateRuleService evaluateRuleService;
	@Autowired
	private IEvaluateConclusionContentService evaluateConclusionContentService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(EvaluateInfo info) throws Exception {
		try {
			return evaluateInfoService.save(info);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.save] error.", e);
		}
		return false;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(EvaluateInfo info) throws Exception {
		try {
			evaluateInfoService.update(info);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.update] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluateInfo queryById(String id) throws Exception {
		try {
			return evaluateInfoService.queryById(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.queryById] error.", e);
		}
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<EvaluateInfo> queryPage(Map<String, Object> params,Page<EvaluateInfo> pageBounds) throws Exception
			 {
		try {
			return evaluateInfoService.queryPage(params, pageBounds);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.queryPage] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateInfo> queryList(Map<String, Object> params) throws Exception {
		try {
			return evaluateInfoService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.queryList] error.", e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<EvaluateInfo> queryList(String evaluateCategoryId) throws Exception {
		try {
			Assert.notNull(evaluateCategoryId);
			Map<String,Object> params = Maps.newHashMap();
			params.put("evaluateCategoryId", evaluateCategoryId);
			return evaluateInfoService.queryList(params);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.queryList] error.", e);
		}
		return null;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		try {
			return evaluateInfoService.getCount(params);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.getCount] error.", e);
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(String id) throws Exception {
		try {
			EvaluateInfo info = new EvaluateInfo();
			info.setId(id);
			info.setStatus(StatusEnum.RUNNING.getId());
			this.update(info);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.start] error.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void complete(String id) throws Exception {
		try {
			evaluateInfoService.updateTimes(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.complete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String id) throws Exception {
		try {
			EvaluateInfo info = new EvaluateInfo();
			info.setId(id);
			info.setDelFlag(1);
			info.setDelDate(new Date());
			this.update(info);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.delete] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteReal(String id) throws Exception {
		try {
			evaluateInfoService.deleteReal(id);
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.realDel] error.", e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public JSONObject parsingXML(EvaluateInfo info, InputStream inputStream) throws Exception {
		JSONObject result = new JSONObject();
		result.put(Constants.Common.SUCCESS, false);
		result.put(Constants.Common.MSG, "解析失败！");
		try {
			if (null != info &&StringUtils.isNotBlank(info.getId())) {
				RuleModel ruleModel = (RuleModel) EvaluateUtils.xml2Obj(null,inputStream);
				if (null != ruleModel) {
					Map<String, Object> params = new HashMap<>();
					params.put("enname", ruleModel.getEnName());
					params.put("idNot", info.getId());
					int count = this.getCount(params);
					if (count > 0) {
						result.put(Constants.Common.MSG, "存在重名（英文名称）的评估！");
						return result;
					} else {
						boolean flag = EvaluateUtils.verifyParamCount(ruleModel);
						if (!flag) {
							result.put(Constants.Common.MSG, "表达式中的参数比定义的参数多，请重新上传！");
							return result;
						}
						/** 补全评估信息 */
						info.setName(ruleModel.getName());
						info.setEnname(ruleModel.getEnName());
						String modeStr = "";
						if(null!=ruleModel.getRules().getMode()){
							modeStr = ruleModel.getRules().getMode().value();
						}
						RuleEnum mode = RuleEnum.getByName(modeStr);
						info.setMode(mode.getId());
						evaluateInfoService.update(info);
						/** 保存评估参数 */
						for (Param param : ruleModel.getParams().getParam()) {
							EvaluateParam tmp = new EvaluateParam();
							tmp.setEvaluateInfoId(info.getId());
							tmp.setName(param.getName());
							tmp.setEnname(param.getEnName());
							String typeStr = "";
							if(null!=param.getType()){
								typeStr = param.getType().value();
							}
							tmp.setType(typeStr);
							evaluateParamService.save(tmp);
						}
						/**只是结论型，RuleEnum.CONCLUSION==mode，解析评估结论内容，并用评估结论内容id（多个以+连接）替换ruleModel中的result*/
						Map<String,List<Map<String,Object>>> ruleResultList = null;
						if(RuleEnum.CONCLUSION==mode){
							Map<String,Object> ruleResult = EvaluateUtils.getParsingRuleResult(ruleModel,1);
							/**ruleModel在解析评论结果时被替换了其中的结论信息，用新的继续下面操作*/
							if(null==ruleResult&&ruleResult.size()<=0){
								result.put(Constants.Common.MSG, "解析评估结果失败，请重新上传！");
								return result;
							}
							ruleModel = (RuleModel)ruleResult.get("ruleModel");
							ruleResultList = (Map<String,List<Map<String,Object>>>)ruleResult.get("ruleResultMap");
						}
						/** 保存评估规则 */
						Map<String, String> parsingResult = EvaluateUtils.getParsingResult(ruleModel);
						if (null == parsingResult && parsingResult.size() <= 0) {
							result.put(Constants.Common.MSG, "解析表达式失败，请重新上传！");
							return result;
						}
						for (Entry<String, String> entry : parsingResult.entrySet()) {
							String key = entry.getKey();
							String ruleName ="";
							String resultTag = "";
							EvaluateRule rule = new EvaluateRule();
							if(RuleEnum.CONCLUSION==mode){
								int pos = key.lastIndexOf("_");
								ruleName = key.substring(0,pos);
								resultTag = key.substring(pos+1);
								rule.setResultTag(resultTag);
							}else{
								ruleName = key;
							}
							rule.setEvaluateInfoId(info.getId());
							rule.setName(ruleName);
							rule.setRule(entry.getValue());
							evaluateRuleService.save(rule);
							if(RuleEnum.CONCLUSION==mode){
								/**保存评估内容*/
								String[] resultTagArr = resultTag.split(",");
								for(String tmp:resultTagArr){
									if(ruleResultList.containsKey(tmp)){
										List<Map<String,Object>> _list = ruleResultList.get(tmp);
										for(Map<String,Object> _map:_list){
											/**保存评估结论内容*/
											String name = _map.get("name").toString();
											PregnantResultKeyEnum enname = PregnantResultKeyEnum.getByName(name);
											EvaluateConclusionContent conclusion = new EvaluateConclusionContent();
											conclusion.setId(_map.get("id").toString());
											conclusion.setEvaluateInfoId(info.getId());
											conclusion.setEvaluateRuleId(rule.getId());
											conclusion.setName(name);
											if(null!=enname){
												conclusion.setEnname(enname.getEnname());
											}
											conclusion.setConclusion(_map.get("conclusion").toString());
											conclusion.setResultTag(tmp);
											evaluateConclusionContentService.save(conclusion);
										}
										
									}
								}
							}
						}
						result.put(Constants.Common.SUCCESS, true);
						result.put(Constants.Common.MSG, "解析成功！");
					}

				}
			}
		} catch (Exception e) {
			logger.error("[EvaluateInfoBizImpl.parsingXML] error.", e);
		} finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}
		return result;
	}

}
