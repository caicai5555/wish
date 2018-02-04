package com.foundation.service.evaluate.serviceInterface.pregnant;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.config.Global;
import com.foundation.common.date.DateUtils;
import com.foundation.common.json.JsonUtils;
import com.foundation.dao.entity.archive.FamilyArchive;
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.dao.entity.sys.User;
import com.foundation.mongo.entity.archive.Archive;
import com.foundation.mongo.entity.record.ConcIndicator;
import com.foundation.mongo.entity.record.ConcIndicatorItem;
import com.foundation.mongo.entity.record.DescIndicator;
import com.foundation.mongo.entity.record.FamilyConcIndicator;
import com.foundation.mongo.entity.record.Indicator;
import com.foundation.service.archive.biz.IArchiveBiz;
import com.foundation.service.common.IndicatorType;
import com.foundation.service.dist.biz.IFamilyArchiveBiz;
import com.foundation.service.dist.service.IDistArchiveUtilService;
import com.foundation.service.evaluate.biz.IEvaluateCategoryBiz;
import com.foundation.service.evaluate.biz.IEvaluateConclusionContentBiz;
import com.foundation.service.evaluate.biz.IEvaluateInfoBiz;
import com.foundation.service.evaluate.biz.IEvaluateParamBiz;
import com.foundation.service.evaluate.biz.IEvaluateRuleBiz;
import com.foundation.service.evaluate.common.Constants;
import com.foundation.service.evaluate.common.EvaluateCal;
import com.foundation.service.evaluate.common.PregnantResultKeyEnum;
import com.foundation.service.evaluate.serviceInterface.vo.PregnantResultVO;
import com.foundation.service.record.service.IIndicatorRecordService;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class PregnantEvaluateReceive implements MessageListener {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IFamilyArchiveBiz familyArchiveBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IArchiveBiz archiveBiz;
	@Autowired
	private IEvaluateInfoBiz evaluateInfoBiz;
	@Autowired
	private IEvaluateParamBiz evaluateParamBiz;
	@Autowired
	private IEvaluateRuleBiz evaluateRuleBiz;
	@Autowired
	private IEvaluateConclusionContentBiz evaluateConclusionContentBiz;
	@Autowired
	private IEvaluateCategoryBiz evaluateCategoryBiz;
	@Autowired
	private IDistArchiveUtilService distArchiveUtilService;
	@Autowired
	private IIndicatorRecordService indicatorRecordService;
	/** 检查方 */
	private static final ImmutableMap<String, String> riskTargetMap = ImmutableMap.of("0", "男方", "1", "女方", "3", "双方");
	/** 危险因素分类 */
	private static final Map<String, String> riskCategoryMap = Maps.newHashMap();
	/** 不需要回显的item */
	private static final ImmutableSet<String> invalidItemSet = ImmutableSet.of("other_family_disease",
			"other_andropathy", "urt_abnormal_detail", "other_harmful_environment", "daily_drink_volumn", "common",
			"judge", "memory", "calculation", "cervix_deail","informed_consent_signed");
	/** 需转换阴阳的item */
	private static final ImmutableSet<String> yinYangSet = ImmutableSet.of("hbs_ag", "hbs_ab", "hbe_ag", "hbe_ab",
			"hbc_ab");
	private static final ImmutableMap<String, String> yinYangValMap = ImmutableMap.of("0", "阴性", "1", "阳性", "9", "可疑");

	/** 需转换压力的item */
	private static final ImmutableSet<String> pressureSet = ImmutableSet.of("is_pressure", "is_relationship_tense",
			"is_economic_pressure");
	private static final ImmutableMap<String, String> pressureValMap = ImmutableMap.of("0", "无", "1", "很少", "2", "有一点",
			"3", "比较大", "4", "很大");
	/** 特殊处理的值 */
	private static final ImmutableSet<String> specialSet = ImmutableSet.of("cutting_palace_date_time");

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DATE_STANDARD,
			java.util.Locale.US);
	private static final DecimalFormat formater = new DecimalFormat();
	/** 需要最大、最小值的指标 */
	private static final ImmutableSet<String> indicatorRange = ImmutableSet.of("tsh", "alt", "scr", "hb");
	/** 最大指标的后缀 */
	private static final String RANGE_MAX = "_rangeMax";
	/** 最小指标的后缀 */
	private static final String RANGE_MIN = "_rangeMin";
	/**规则中包含动态区间值的规则记录，因规则是前置的，不会每次计算都改变一次，则在第一次是会更新规则的初始化Service，后续不会再次初始化，提供效率*/
	private static final Set<String> infoReloadSet = Sets.newHashSet();

	static {
		riskCategoryMap.put("01", "感染");
		riskCategoryMap.put("02", "行为");
		riskCategoryMap.put("03", "环境");
		riskCategoryMap.put("04", "精神心理");
		riskCategoryMap.put("05", "慢病");
		riskCategoryMap.put("06", "生殖");
		riskCategoryMap.put("07", "药物");
		riskCategoryMap.put("08", "遗传");
		riskCategoryMap.put("09", "营养");
		riskCategoryMap.put("10", "其他");

		/**保留几位小数*/
		formater.setMaximumFractionDigits(1);
		/**模式 四舍五入*/ 
		formater.setRoundingMode(RoundingMode.UP);
	}
	/**
	 * 匹配参数在档案中所属类别，查询使用，大小无特殊意义，
	 * indicator--标准指标,descIndicator--描述性指标，concIndicator--结论性指标
	 */
	private static final Map<String, IndicatorType> paramMap = new HashMap<>(1024);

	@Override
	public void onMessage(Message message) {
		String getMsg = new String(message.getBody());
		logger.info("receive data :" + getMsg);
		try {
			long startTime = System.currentTimeMillis(); // 获取开始时间
			Map<String,Object> result = this.pregnantEvaluate(getMsg);
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println(JsonUtils.formateMap(result));
			System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
			String callbackUrl = Global.getConfig("callback.url");
			if(StringUtils.isNotBlank(callbackUrl)){
				String retrunMsg=doHttpPost(callbackUrl, JsonUtils.formateMap(result));//HttpClientUtils.doPost(callbackUrl,result);
				System.out.println(retrunMsg);
				logger.info("评估服务回调接口结果："+retrunMsg);
			}
		} catch (Exception e) {
			logger.error("PregnantEvaluateReceive pregnantEvaluate error.", e);
		}
	}
	
	private  String doHttpPost(String remoteurl, String httptext) {		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			logger.info("doHttpPost request:" + httptext);
			HttpPost httpPost = new HttpPost(remoteurl);
			
			httptext = URLEncoder.encode(httptext, "UTF-8");
			httpPost.setEntity(new StringEntity(httptext, Charset.forName("UTF-8")));  
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				HttpEntity entity2 = response2.getEntity();

				String result = EntityUtils.toString(entity2);
				logger.info("doHttpPost response:" + result);
				logger.info("doHttpPost result:" + result);
				return result;
			} finally {
				response2.close();
			}
		} catch (Exception e) {
			String errorMsg = e.getMessage();
			logger.error(errorMsg, e);
			if (e.getCause() != null) {
				errorMsg = e.getCause().getMessage();
			}
			return errorMsg;

		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 
	* @Title: pregnantEvaluate 
	* @Description: 孕检评估服务计算方法
	* @author chengchen
	* @date 2016年11月29日 下午4:04:06 
	* @param @param pregnancyArchiveId
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	private Map<String,Object> pregnantEvaluate(String pregnancyArchiveId) throws Exception {
		Map<String,Object> result = new HashMap<>();
		StringBuffer tip = new StringBuffer();
		List<JSONObject> requestlist = new ArrayList<>();
		tip.append("执行失败，原因：");
		String timeStampStr = DateUtils.formatDate(new Date(), DateUtils.DATE_All_KEY_STR_SS);
		Random random = new Random();
		long rannum = (long) (random.nextDouble() * (999999999999999L - 100000000000000L + 1L)) + 100000000000000L;// 获取15位随机数
		String requestId = timeStampStr + rannum;
		result.put("requestid", requestId);
		result.put("requestsource", "risk");
		result.put("requestuser", "risk");
		result.put("sessionid", "");
		result.put("method", "riskevalFacade.riskevalCallBack");
		result.put("version", "1.0");
		result.put("signature", "");
		try {
			FamilyArchive familyArchive = familyArchiveBiz.queryByPregnancyArchiveId(pregnancyArchiveId);
			JSONObject data = new JSONObject();
			if (null != familyArchive) {
				User user = new User();
				user.setPregnancyArchiveId(pregnancyArchiveId);
				List<User> family = userBiz.findUser(user);
				/** 分别获取档案信息（Mongodb） */
				Archive wifeArchive = null;
				Archive husbandArchive = null;
				if(null!=family){
					for(User _user:family){
						if(null==_user.getSex()){
							continue;
						}
						if(Constants.SEX_MAN.intValue()==_user.getSex().intValue()){
							husbandArchive = archiveBiz.queryByUserId(_user.getId());
						}else if(Constants.SEX_WOMAN.intValue()==_user.getSex().intValue()){
							wifeArchive = archiveBiz.queryByUserId(_user.getId());
						}
					}
				}
				/** 获取孕检的所有分类Id */
				EvaluateCategory wifeCategory = evaluateCategoryBiz.queryByEnname(Constants.Pregnant.PREGNANT_WOMAN);
				EvaluateCategory husbandCategory = evaluateCategoryBiz.queryByEnname(Constants.Pregnant.PREGNANT_MAN);
				EvaluateCategory familyCategory = evaluateCategoryBiz.queryByEnname(Constants.Pregnant.PREGNANT_FAMILY);
				/** 获取孕检的所有规则 */
				List<EvaluateInfo> wifeEvaluateInfoList = null;
				List<EvaluateInfo> husbandEvaluateInfoList = null;
				List<EvaluateInfo> familyEvaluateInfoList = null;
				if (null != wifeCategory) {
					wifeEvaluateInfoList = evaluateInfoBiz.queryList(wifeCategory.getId());
				} else {
					tip.append("，妻子评估分类为空，分类英文" + Constants.Pregnant.PREGNANT_WOMAN);
				}
				if (null != husbandCategory) {
					husbandEvaluateInfoList = evaluateInfoBiz.queryList(husbandCategory.getId());
				} else {
					tip.append("，丈夫评估分类为空，分类英文" + Constants.Pregnant.PREGNANT_MAN);
				}
				if (null != familyCategory) {
					familyEvaluateInfoList = evaluateInfoBiz.queryList(familyCategory.getId());
				} else {
					tip.append("，家庭评估分类为空，分类英文" + Constants.Pregnant.PREGNANT_FAMILY);
				}
				/** 初始化 */
				EvaluateCal cal = EvaluateCal.getInstance();
				int risksSize = 0;
				if (null != wifeEvaluateInfoList) {
					risksSize += wifeEvaluateInfoList.size();
				}
				if (null != husbandEvaluateInfoList) {
					risksSize += husbandEvaluateInfoList.size();
				}
				if (null != familyEvaluateInfoList) {
					risksSize += familyEvaluateInfoList.size();
				}
				List<JSONObject> risks = new ArrayList<>(risksSize);
				PregnantResultVO pregnantResult = new PregnantResultVO();
				/** 女方 */
				if(null!=wifeArchive){
					this.evaluateCal(familyArchive, wifeArchive, husbandArchive, wifeEvaluateInfoList, cal,
							Constants.Pregnant.W_FLAG, pregnantResult, risks, tip);
				}
				/** 男方 */
				if(null!=husbandArchive){
					this.evaluateCal(familyArchive, wifeArchive, husbandArchive, husbandEvaluateInfoList, cal,
							Constants.Pregnant.M_FLAG, pregnantResult, risks, tip);
				}
				/** 家庭 */
				if(null!=wifeArchive&&null!=husbandArchive){
					this.evaluateCal(familyArchive, wifeArchive, husbandArchive, familyEvaluateInfoList, cal,
							Constants.Pregnant.F_FLAG, pregnantResult, risks, tip);
				}
				/** 计算和判别规则 */
				/** 女方高风险标签 */
				if (pregnantResult.getFemaleHighRisk_5() > 0) {
					/** 任一一项及以上取值为5分者 */
					pregnantResult.setFemaleHighRiskTag(1);
				} else {
					/** 3项标签危害等级取值为3分者=有1项为4分者 */
					int femaleHighRisk_3 = pregnantResult.getFemaleHighRisk_3();
					int femaleHighRisk_4_count = femaleHighRisk_3 / 3;
					int femaleHighRisk_4 = pregnantResult.getFemaleHighRisk_4() + femaleHighRisk_4_count;
					int femaleHighRisk_5_count = femaleHighRisk_4 / 3;
					if (femaleHighRisk_5_count > 0) {
						pregnantResult.setFemaleHighRiskTag(1);
					}
				}
				/*** 男高风险标签 */
				if (pregnantResult.getMaleHighRisk_5() > 0) {
					/** 任一一项及以上取值为5分者 */
					pregnantResult.setMaleHighRiskTag(1);
				} else {
					/** 3项标签危害等级取值为3分者=有1项为4分者 */
					int maleHighRisk_3 = pregnantResult.getMaleHighRisk_3();
					int maleHighRisk_4_count = maleHighRisk_3 / 3;
					int maleHighRisk_4 = pregnantResult.getMaleHighRisk_4() + maleHighRisk_4_count;
					/** 3项标签危害等级取值为4分者=有1项为5分者 */
					int maleHighRisk_5_count = maleHighRisk_4 / 3;
					if (maleHighRisk_5_count > 0) {
						pregnantResult.setMaleHighRiskTag(1);
					}
				}
				/** 高风险标签 （1.女方，2.男方 3.双方） */
				if (1 == pregnantResult.getFemaleHighRiskTag() && 1 == pregnantResult.getMaleHighRiskTag()) {
					/** 双方：男方是高风险+女方是高风险 */
					pregnantResult.setHighRiskTag(3);
				} else if (1 == pregnantResult.getFemaleHighRiskTag()) {
					/** 女方：男方非高风险+女方是高风险 */
					pregnantResult.setHighRiskTag(1);
				} else if (1 == pregnantResult.getMaleHighRiskTag()) {
					/** 男方：男方是高风险+女方非高风险 */
					pregnantResult.setHighRiskTag(2);
				}
				/** 风险结论 */
				if (1 == pregnantResult.getFemaleHighRiskTag() || 1 == pregnantResult.getMaleHighRiskTag()) {
					/** F5：高风险人群判定中男方或女方至少一方为高风险者为3 */
					pregnantResult.setRiskConclusion("3");
				} else if ((!pregnantResult.isMaleNormalFlag() || !pregnantResult.isFemaleNormalFlag()
						|| !pregnantResult.isFamilyNormalFlag())
						&& (0 == pregnantResult.getFemaleHighRiskTag() || 0 == pregnantResult.getMaleHighRiskTag())
						&& ("1".equals(familyArchive.getInformedConsentSigned())
								|| "2".equals(familyArchive.getInformedConsentSigned()))) {
					/** F4：男方或女方所有涉及的变量标签任一一项及以上为“异常”者，且高风险人群判定男方或女方为非高风险者，且知情同意书为单方签署者为2.2 */
					pregnantResult.setRiskConclusion("2.2");
				} else if ((!pregnantResult.isMaleNormalFlag() || !pregnantResult.isFemaleNormalFlag()
						|| !pregnantResult.isFamilyNormalFlag())
						&& (0 == pregnantResult.getFemaleHighRiskTag() && 0 == pregnantResult.getMaleHighRiskTag())
						&& "3".equals(familyArchive.getInformedConsentSigned())) {
					/** F3：男女双方以及家庭表所有涉及的变量标签任一一项及以上为“异常”者，且是否高风险判定双方均为非高风险者，且知情同意书为双方签署者为2.1 */
					pregnantResult.setRiskConclusion("2.1");
				} else if (pregnantResult.isMaleNormalFlag() && pregnantResult.isFemaleNormalFlag()
						&& pregnantResult.isFamilyNormalFlag() && ("1".equals(familyArchive.getInformedConsentSigned())
								|| "2".equals(familyArchive.getInformedConsentSigned()))) {
					/** F2：男方或女方所有涉及的变量标签均为正常者，知情同意书为单方签署者为1.2 */
					pregnantResult.setRiskConclusion("1.2");
				} else if (pregnantResult.isMaleNormalFlag() && pregnantResult.isFemaleNormalFlag()
						&& pregnantResult.isFamilyNormalFlag()
						&& "3".equals(familyArchive.getInformedConsentSigned())) {
					/** F1：男女双方及家庭表所有涉及的变量标签均为正常者，知情同意书为双方签署者为1.1 */
					pregnantResult.setRiskConclusion("1.1");
				}
				data.put("archiveId", pregnancyArchiveId);
				if(null!=familyArchive){
					data.put("shardDBName", familyArchive.getProvinceCode());
				}
				data.put("risks", risks);
				JSONObject riskResult = new JSONObject();
				riskResult.put("comprehensivePregnantAdvice", pregnantResult.getComprehensivePregnantAdvice());
				riskResult.put("maleHighRiskTag", pregnantResult.getMaleHighRiskTag());
				riskResult.put("femaleHighRiskTag", pregnantResult.getFemaleHighRiskTag());
				riskResult.put("highRiskTag", pregnantResult.getHighRiskTag());
				riskResult.put("riskConclusion", pregnantResult.getRiskConclusion());
				data.put("riskResult", riskResult);
				if (pregnantResult.getFemaleMissingItem().size() > 0) {
					data.put("femaleMissingItem", pregnantResult.getFemaleMissingItem());
				}
				if (pregnantResult.getMaleMissingItem().size() > 0) {
					data.put("maleMissingItem", pregnantResult.getMaleMissingItem());
				}
			} else {
				tip.append("未找到夫妻家庭档案，档案id：" + pregnancyArchiveId);
			}
			/** 拼装json，通过url方式调用方法请求 */
			if ("执行失败，原因：".length() == tip.length()) {
				tip.delete(0, tip.length());
				tip.append("success");
				data.put("resultcode", 0);
			}else{
				data.put("resultcode", 1);
			}
			data.put("resultmsg", tip.toString());
			requestlist.add(data);
			result.put("requestlist", requestlist);
		} catch (Exception e) {
			logger.error("PregnantEvaluateReceive pregnantEvaluate error.", e);
			throw e;
		}
		return result;
	}

	/**
	 * 
	* @Title: evaluateCal 
	* @Description: 具体评估计算
	* @author chengchen
	* @date 2016年11月29日 下午4:04:20 
	* @param @param familyArchive
	* @param @param wifeArchive
	* @param @param husbandArchive
	* @param @param evaluateInfoList
	* @param @param cal
	* @param @param flag
	* @param @param pregnantResult
	* @param @param risks
	* @param @param tip
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	private void evaluateCal(FamilyArchive familyArchive, Archive wifeArchive, Archive husbandArchive,
			List<EvaluateInfo> evaluateInfoList, EvaluateCal cal, String flag, PregnantResultVO pregnantResult,
			List<JSONObject> risks, StringBuffer tip) throws Exception {
		if (null != evaluateInfoList) {
			try {
				for (EvaluateInfo info : evaluateInfoList) {
					JSONObject risk = new JSONObject();
					Map<String, Object> params = Maps.newHashMap();
					params.put("evaluateInfoId", info.getId());
					EvaluateInfo historyInfo = cal.getInfo(info);
					if(null==historyInfo||DateUtils.beforeDate(historyInfo.getCreateDate(), info.getCreateDate())){
						List<EvaluateParam> paramList = evaluateParamBiz.queryList(params);
						List<EvaluateRule> ruleList = evaluateRuleBiz.queryList(params);
						cal.init(info, paramList, ruleList, true, null);
						/*清除规则中包含动态区间值的规则记录，重新加载规则*/
						infoReloadSet.remove(info.getEnname());
					}
					List<EvaluateParam> calParamList = cal.getParam(info);
					List<EvaluateParam> extraParamList = Lists.newArrayList();
					if (null != calParamList) {
						Map<String, Object> data = Maps.newHashMap();
						for (EvaluateParam param : calParamList) {
							String indicator = "";
							IndicatorType type = null;
							/** 替换规则参数的前缀，通过方法查询参数属于档案中哪个集合下，获取对应的值 */
							if (param.getEnname().startsWith(Constants.Pregnant.W_)
									|| param.getEnname().startsWith(Constants.Pregnant.M_)) {
								indicator = param.getEnname().substring(2);
							} else {
								indicator = param.getEnname();
							}
							/**
							 * 档案中所属类别，
							 * indicator--标准指标,descIndicator--描述性指标，concIndicator--结论性指标
							 */
							if (paramMap.containsKey(indicator)) {
								type = paramMap.get(indicator);
							} else {
								type = distArchiveUtilService.getIndicatorType(indicator);
								if(null==type){
									type = IndicatorType.INDICATOR;
								}
								paramMap.put(indicator, type);
							}
							String value = null;
							if (null != type) {
								if (param.getEnname().startsWith(Constants.Pregnant.W_)) {
									value = this.getIndicatorVal(wifeArchive, type, indicator);
									if (StringUtils.isBlank(value) && !invalidItemSet.contains(indicator)) {
										pregnantResult.getFemaleMissingItem().add(param.getName());
									}
									this.getIndicatorRangeVal(wifeArchive, data, indicator,extraParamList);
								} else if (param.getEnname().startsWith(Constants.Pregnant.M_)) {
									value = this.getIndicatorVal(husbandArchive, type, indicator);
									if (StringUtils.isBlank(value) && !invalidItemSet.contains(indicator)) {
										pregnantResult.getMaleMissingItem().add(param.getName());
									}
									this.getIndicatorRangeVal(husbandArchive, data, indicator,extraParamList);
								} else {
									if (Constants.Pregnant.W_FLAG.equals(flag)) {
										// 当前参与计算的为妻子
										value = this.getIndicatorVal(wifeArchive, type, indicator);
										if (StringUtils.isBlank(value) && !invalidItemSet.contains(indicator)) {
											pregnantResult.getFemaleMissingItem().add(param.getName());
										}
										this.getIndicatorRangeVal(wifeArchive, data, indicator,extraParamList);
									} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
										// 当前参与计算的为丈夫
										value = this.getIndicatorVal(husbandArchive, type, indicator);
										if (StringUtils.isBlank(value) && !invalidItemSet.contains(indicator)) {
											pregnantResult.getMaleMissingItem().add(param.getName());
										}
										this.getIndicatorRangeVal(husbandArchive, data, indicator,extraParamList);
									}
								}
							}
							value = this.pregnantConvertVal(indicator, value);
							if(null==value&&"informed_consent_signed".equals(indicator)){
								value = familyArchive.getInformedConsentSigned();
							}
							data.put(param.getEnname(), EvaluateCal.converType(value, param.getType()));
						}
						if(calParamList.size()<data.size()&&!infoReloadSet.contains(info.getEnname())){
							/*
							 * 若规则参数数量小于传出值的数量，则表示有后缀为_rangeMin或_rangeMax的动态区间值存在
							 * 第一次时需要重新初始化EvaluateCal中的serviceMap中与此次info相同的记录，并在infoReloadSet记录下info
							 * 后续操作中，先通过infoReloadSet判断是否已经重新初始化过，是：则不用再次初始化 
							 */
							calParamList.addAll(extraParamList);
							cal.initService(info, calParamList, true);
							infoReloadSet.add(info.getEnname());
							/*
							 * calParamList指向单例中的参数Map
							 * 上述逻辑在Map中增加了_rangeMin和_rangeMax，将这2个参数注册到Service中
							 * 注册后要将参数从Map中剔除，防止影响其他业务逻辑
							 */
							calParamList.removeAll(extraParamList);
						}
						/** 评估计算 */
						String resultTag = cal.execute(data, info).toString();
						if (StringUtils.isNotBlank(resultTag)
								&& !Constants.Common.CONCLUSION_DEFAULT.equals(resultTag)) {
							params.put("resultTag", resultTag);
							/** 获取结论列表 */
							List<EvaluateConclusionContent> conclusions = evaluateConclusionContentBiz
									.queryList(params);
							/** 评估结论归档 */
							this.saveIndicatorRecord(familyArchive, wifeArchive, husbandArchive, info, conclusions,
									flag);
							if (null != conclusions && conclusions.size() > 0) {
								/** 拼装返回的json */
								for (EvaluateConclusionContent conclusionContent : conclusions) {
									/** 替换占位符 */
									String conclusion = EvaluateCal.replaceSpecialChar(conclusionContent.getConclusion(), data);
									if (StringUtils.isNotBlank(conclusion)) {
										PregnantResultKeyEnum key = PregnantResultKeyEnum
												.getByName(conclusionContent.getName());
										if(null==key){
											logger.error(info.getEnname()+"的结论的key不存在:"+conclusionContent.getId());
											continue;
										}
										switch (key) {
										case ADVICE_FLAG:
										case FAMILY_HARM_LEVEL:
										case MALE_HARM_LEVEL:
										case FEMALE_HARM_LEVEL:
											risk.put(conclusionContent.getEnname(),
													Integer.parseInt(conclusion.trim()));
											break;
										case RESULT_DESC:
										case HARM_DESC:
										case ADVICE_DESC:
										case CONTROL_LEVEL:
											risk.put(conclusionContent.getEnname(), conclusion);
											break;
										case PREGNANT_ADVICE:
											int _pregnant_advice = Integer.parseInt(conclusion.trim());
											if (_pregnant_advice > pregnantResult.getComprehensivePregnantAdvice()) {
												pregnantResult.setComprehensivePregnantAdvice(_pregnant_advice);
											}
											risk.put(conclusionContent.getEnname(), _pregnant_advice);
											break;
										case HARM_LEVEL:
											int _harm_level = Integer.parseInt(conclusion.trim());
											switch (_harm_level) {
											case 3:
												if (Constants.Pregnant.W_FLAG.equals(flag)) {
													// 女方
													int femaleHighRisk_3 = pregnantResult.getFemaleHighRisk_3();
													femaleHighRisk_3++;
													pregnantResult.setFemaleHighRisk_3(femaleHighRisk_3);
												} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
													// 男方
													int maleHighRisk_3 = pregnantResult.getMaleHighRisk_3();
													maleHighRisk_3++;
													pregnantResult.setMaleHighRisk_3(maleHighRisk_3);
												}
												break;
											case 4:
												if (Constants.Pregnant.W_FLAG.equals(flag)) {
													// 女方
													int femaleHighRisk_4 = pregnantResult.getFemaleHighRisk_4();
													femaleHighRisk_4++;
													pregnantResult.setFemaleHighRisk_4(femaleHighRisk_4);
												} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
													// 男方
													int maleHighRisk_4 = pregnantResult.getMaleHighRisk_4();
													maleHighRisk_4++;
													pregnantResult.setMaleHighRisk_4(maleHighRisk_4);
												}
												break;
											case 5:
												if (Constants.Pregnant.W_FLAG.equals(flag)) {
													// 女方
													int femaleHighRisk_5 = pregnantResult.getFemaleHighRisk_5();
													femaleHighRisk_5++;
													pregnantResult.setFemaleHighRisk_5(femaleHighRisk_5);
												} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
													// 男方
													int maleHighRisk_5 = pregnantResult.getMaleHighRisk_5();
													maleHighRisk_5++;
													pregnantResult.setMaleHighRisk_5(maleHighRisk_5);
												}
												break;
											default:
												break;
											}
											risk.put(conclusionContent.getEnname(), _harm_level);
											break;
										case ABNORMAL_FLAG:
											int _abnormal_flag = Integer.parseInt(conclusion.trim());
											if (_abnormal_flag == Constants.Pregnant.ABNORMAL_FLAG_DEFAULT) {
												if (Constants.Pregnant.W_FLAG.equals(flag)) {
													// 女方
													pregnantResult.setFemaleNormalFlag(false);
												} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
													// 男方
													pregnantResult.setMaleNormalFlag(false);
												} else if (Constants.Pregnant.F_FLAG.equals(flag)) {
													// 家庭
													pregnantResult.setFamilyNormalFlag(false);
												}
											}
											risk.put(conclusionContent.getEnname(), _abnormal_flag);
											break;
										default:
											break;
										}
									}
								}
							}
							if(risk.size()>0){
								risk.put("riskNo", info.getEnname());
								/** 从字典中获取数据 */
								risk.put("riskTarget", riskTargetMap.get(info.getEnname().substring(3, 4)));
								risk.put("riskcategory", riskCategoryMap.get(info.getEnname().substring(1, 3)));
								risks.add(risk);
							}
						}
					} else {
						tip.append("，评估参数列表为空");
					}
				}
			} catch (Exception e) {
				logger.error("PregnantEvaluateReceive evaluateCal error.", e);
				throw e;
			}
		} else {
			if (Constants.Pregnant.W_FLAG.equals(flag)) {
				tip.append("，妻子评估信息列表为空");
			} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
				tip.append("，丈夫评估信息列表为空");
			} else if (Constants.Pregnant.F_FLAG.equals(flag)) {
				tip.append("，家庭评估信息列表为空");
			}
		}
	}

	/**
	 * 
	* @Title: getIndicatorVal 
	* @Description: 获取档案中具体某项指标值
	* @author chengchen
	* @date 2016年11月29日 下午4:04:34 
	* @param @param archive
	* @param @param type
	* @param @param indicator
	* @param @return    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	private String getIndicatorVal(Archive archive, IndicatorType type, String indicator) {
		if (null != archive) {
			switch (type) {
			case INDICATOR:
				/** 标准指标 */
				Indicator _indicator = archive.getIndicator().get(indicator);
				if (null != _indicator) {
					return _indicator.getValue();
				}
				return null;
			case DESCINDICATOR:
				/** 结论性指标 */
				DescIndicator _descIndicator = archive.getDescIndicator().get(indicator);
				if (null != _descIndicator) {
					return _descIndicator.getValue();
				}
				return null;
			case CONCINDICATOR:
				/** 描述性指标 */
				ConcIndicator _concIndicator = archive.getConcIndicator().get(indicator);
				if (null != _concIndicator) {
					return _concIndicator.getValue();
				}
				return null;
			default:
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	* @Title: saveIndicatorRecord 
	* @Description: 评估结论归档
	* @author chengchen
	* @date 2016年11月29日 下午4:04:44 
	* @param @param familyArchive
	* @param @param wifeArchive
	* @param @param husbandArchive
	* @param @param info
	* @param @param conclusions
	* @param @param flag
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	private void saveIndicatorRecord(FamilyArchive familyArchive, Archive wifeArchive, Archive husbandArchive,
			EvaluateInfo info, List<EvaluateConclusionContent> conclusions, String flag) throws Exception {
		try {
			if (Constants.Pregnant.F_FLAG.equals(flag)) {
				/** 归档到家庭 */
				FamilyConcIndicator indicator = new FamilyConcIndicator();
				indicator.setCode(info.getEnname());
				indicator.setfId(familyArchive.getPregnancyArchiveId());
				indicator.setWifeCertNum(familyArchive.getWifeCertNum());
				indicator.setWifeName(familyArchive.getWifeName());
				indicator.setHusbandCertNum(familyArchive.getHusbandCertNum());
				indicator.setHusbandName(familyArchive.getHusbandName());
				indicator.setSource("评估服务接口");
				indicator.setEvent("评估计算");
				// indicator.setUpdateTime(new Date());
				if (null != conclusions) {
					for (EvaluateConclusionContent _tmp : conclusions) {
						ConcIndicatorItem item = new ConcIndicatorItem();
						item.setCode(_tmp.getEnname());
						item.setName(_tmp.getName());
						item.setValue(_tmp.getConclusion());
						indicator.getConcIndicatorItems().add(item);
					}
				}
				indicatorRecordService.saveFamilyIndicator(indicator, true);
			} else if (Constants.Pregnant.W_FLAG.equals(flag) || Constants.Pregnant.M_FLAG.equals(flag)) {
				/** 归档到个人 */
				ConcIndicator indicator = new ConcIndicator();
				if (Constants.Pregnant.W_FLAG.equals(flag)) {
					indicator.setUserId(wifeArchive.getUserId());
					indicator.setCertNum(familyArchive.getWifeCertNum());
				} else if (Constants.Pregnant.M_FLAG.equals(flag)) {
					indicator.setUserId(husbandArchive.getUserId());
					indicator.setCertNum(familyArchive.getHusbandCertNum());
				}
				indicator.setCode(info.getEnname());
				indicator.setSource("评估服务接口");
				indicator.setEvent("评估计算");
				//目前保存时会将updateTime转换为字符串，再获取时报错，待修复后打开注释
				// indicator.setUpdateTime(new Date());
				if (null != conclusions) {
					for (EvaluateConclusionContent _tmp : conclusions) {
						ConcIndicatorItem item = new ConcIndicatorItem();
						item.setCode(_tmp.getEnname());
						item.setName(_tmp.getName());
						item.setValue(_tmp.getConclusion());
						indicator.getConcIndicatorItems().add(item);
					}
				}
				indicatorRecordService.saveIndicator(indicator, true);
			}
		} catch (Exception e) {
			logger.error("PregnantEvaluateReceive saveIndicatorRecord error.", e);
			throw e;
		}
	}

	/**
	 * 
	* @Title: pregnantConvertVal 
	* @Description: 将数字型的值转换为字符串
	* @author chengchen
	* @date 2016年11月29日 下午4:04:53 
	* @param @param indicator
	* @param @param value
	* @param @return
	* @param @throws Exception    设定参数 
	* @return String    返回类型 
	* @throws
	 */
	private String pregnantConvertVal(String indicator, String value) throws Exception {
		if (null == value) {
			return null;
		}
		if (yinYangSet.contains(indicator) && yinYangValMap.containsKey(value)) {
			return yinYangValMap.get(value);
		} else if (pressureSet.contains(indicator) && pressureValMap.containsKey(value)) {
			return pressureValMap.get(value);
		} else if (specialSet.contains(indicator)) {
			switch (indicator) {
			/** 女方剖宫产距离现在的时间间隔 */
			case "cutting_palace_date_time":
				Date cuttingPalaceDate = dateFormat.parse(value);
				double years = DateUtils.pastYeas(cuttingPalaceDate);
				return formater.format(years);
			default:
				break;
			}
		}
		return value;

	}

	/**
	 *
	 * @Title: getIndicatorRangeVal
	 * @Description: 获取档案中具体某项指标的最大、最小值
	 * @author chengchen
	 * @date 2016年11月29日 下午4:04:34
	 * @param @param archive
	 * @param @param data
	 * @param @param indicator
	 * @return void
	 * @throws
	 */
	private void getIndicatorRangeVal(Archive archive, Map<String, Object> data, String indicator,List<EvaluateParam> extraParamList) {
		if (indicatorRange.contains(indicator)) {
			double rangeMaxVal = 0;
			double rangeMinVal = 0;
			Map<String, Indicator> indicatorMap = archive.getIndicator();
			if (indicatorMap != null && indicatorMap.get(indicator) != null) {
				Indicator indicatorArchive = indicatorMap.get(indicator);
				if(null!=indicatorArchive.getRangeMaxVal()){
					rangeMaxVal = indicatorArchive.getRangeMaxVal();
					
				}
				if(null!=indicatorArchive.getRangeMinVal()){
					rangeMinVal = indicatorArchive.getRangeMinVal();
				}
			}
			data.put(indicator + RANGE_MAX, rangeMaxVal);
			data.put(indicator + RANGE_MIN, rangeMinVal);
			EvaluateParam p1 = new EvaluateParam();
			p1.setEnname(indicator + RANGE_MAX);
			p1.setType("double");
			extraParamList.add(p1);
			EvaluateParam p2 = new EvaluateParam();
			p2.setEnname(indicator + RANGE_MIN);
			p2.setType("double");
			extraParamList.add(p2);
		}
	}

}
