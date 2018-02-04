package com.foundation.service.evaluate.common;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.security.MD5Util;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.PropertiesLoader;
import com.foundation.common.utils.XMLUtils;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.Exp;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.ExpGroup;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.Param;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.Rule;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.RuleGroup;
import com.foundation.service.evaluate.xsdEntity.evaluationTemplate.RuleModel;

/**
 * 
 * @ClassName: EvaluateUtilsV2
 * @Description: 评估工具类
 * @author chengchen
 * @date 2016年10月17日 下午5:35:07
 *
 */
public class EvaluateUtils {
	/** xsd默认路径 */
	private static final String XSDURL_DEFAULT = "/evaluationTemplate.xsd";
	/** xsd生成的java类的包路径 */
	private static final String PACKAGENAME_DEFAULT = "com.foundation.service.evaluate.xsdEntity.evaluationTemplate";
	/**加载配置文件 */
	private static final PropertiesLoader PROPERTIES_LOADER = new PropertiesLoader("evaluate.properties");

	/**
	 * 
	 * @Title: validatorXML @Description: 验证上传的xml是否符合xsd的规范 @author
	 * chengchen @date 2016年10月17日 下午5:36:45 @param @param
	 * inputStream @param @return @param @throws Exception 设定参数 @return
	 * JSONObject 返回类型 @throws
	 */
	public static JSONObject validatorXML(InputStream inputStream) throws Exception {
		String xsdUrl = PROPERTIES_LOADER.getProperty(Constants.Configure.XSD_URL);
		if (StringUtils.isBlank(xsdUrl)) {
			xsdUrl = XSDURL_DEFAULT;
		}
		return XMLUtils.validatorXML(inputStream, xsdUrl);
	}
	
	/**
	 * 
	* @Title: xml2Obj 
	* @Description: xml转换为实体
	* @author chengchen
	* @date 2016年10月17日 下午5:42:19 
	* @param @param inputStream
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Object    返回类型 
	* @throws
	 */
	public static Object xml2Obj(String packageName,InputStream inputStream) throws Exception{
		if(null==packageName){
			packageName = PACKAGENAME_DEFAULT;
		}
		return XMLUtils.xml2Obj(packageName, inputStream);
	}
	
	/**
	 * 
	* @Title: verifyParamCount 
	* @Description: 验证规则规则的参数数量是否大于等于表达式中的参数数量
	* @author chengchen
	* @date 2016年10月17日 下午5:50:08 
	* @param @param ruleModel
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean verifyParamCount(RuleModel ruleModel) throws Exception {
		Set<String> expParams = new HashSet<>();
		try {
			for (Rule rule : ruleModel.getRules().getRule()) {
				for (Exp exp : rule.getExps().getExp()) {
					expParams.add(exp.getExpParam().getValue());
				}
				for (ExpGroup expGroup : rule.getExps().getExpGroup()) {
					for (Exp exp : expGroup.getExp()) {
						expParams.add(exp.getExpParam().getValue());
					}
				}
			}
			for (RuleGroup ruleGroup : ruleModel.getRules().getRuleGroup()) {
				for (Rule rule : ruleGroup.getRule()) {
					for (Exp exp : rule.getExps().getExp()) {
						expParams.add(exp.getExpParam().getValue());
					}
					for (ExpGroup expGroup : rule.getExps().getExpGroup()) {
						for (Exp exp : expGroup.getExp()) {
							expParams.add(exp.getExpParam().getValue());
						}
					}

				}
			}
			if (ruleModel.getParams().getParam().size() < expParams.size()) {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("XMLUtil.verifyParamCount error.", e);
		}
		return true;
	}

	/**
	 * 
	* @Title: getParsingResult 
	* @Description: 根据规则实体对象解析其中的规则
	* @author chengchen
	* @date 2016年10月17日 下午5:51:19 
	* @param @param ruleModel
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Map<String,String>    返回类型 
	* @throws
	 */
	public static Map<String, String> getParsingResult(RuleModel ruleModel) throws Exception {
		Map<String, String> result = new HashMap<>();
		int index = 1;
		try {
			String modeStr = "";
			if(null!=ruleModel.getRules().getMode()){
				modeStr = ruleModel.getRules().getMode().value();
			}
			RuleEnum mode = RuleEnum.getByName(modeStr);
			/**简单规则*/
			List<Rule> ruleList = ruleModel.getRules().getRule();
			if (null != ruleList && ruleList.size() > 0) {
				for (Rule rule : ruleList) {
					StringBuffer ruleStr = new StringBuffer();
					createRule(ruleModel, rule, ruleStr);
					createElseDefault(mode, ruleStr);
					StringBuffer key = new StringBuffer(ruleModel.getEnName() + Constants.Common._RULE + index);
					if(RuleEnum.CONCLUSION==mode){
						key.append("_"+rule.getResult().getValue());
					}
					result.put(key.toString(), ruleStr.toString());
					index++;
				}
			}
			/**组合规则*/
			List<RuleGroup> ruleGroupList = ruleModel.getRules().getRuleGroup();
			if (null != ruleGroupList && ruleGroupList.size() > 0) {
				for (RuleGroup ruleGroup : ruleGroupList) {
					StringBuffer ruleStr = new StringBuffer();
					List<Rule> _ruleList = ruleGroup.getRule();
					StringBuffer sub_key = new StringBuffer();
					for (int j = 0, jsize = _ruleList.size(); j < jsize; j++) {
						Rule rule = _ruleList.get(j);
						if (j != 0) {
							ruleStr.append(Constants.SpecialSymbol.BLANK).append(Constants.Common.ELSE)
							.append(Constants.SpecialSymbol.BLANK);
						}
						createRule(ruleModel, rule, ruleStr);
						sub_key.append(rule.getResult().getValue());
						if(j!=jsize-1){
							sub_key.append(",");
						}
					}
					createElseDefault(mode, ruleStr);
					StringBuffer key = new StringBuffer(ruleModel.getEnName() + Constants.Common._RULE + index);
					if(RuleEnum.CONCLUSION==mode){
						key.append("_"+sub_key.toString());
					}
					result.put(key.toString(), ruleStr.toString());
					index++;
				}
			}
		} catch (Exception e) {
			throw new Exception("XMLUtil.getParsingResult error.", e);
		}
		return result;
	}

	/**
	 * 
	* @Title: createElseDefault 
	* @Description: 生成else默认输出字符串：”else {return \\\"不匹配\\\";}“
	* @author chengchen
	* @date 2016年10月17日 下午5:54:26 
	* @param @param mode
	* @param @param ruleStr    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	private static void createElseDefault(RuleEnum mode, StringBuffer ruleStr) {
		ruleStr.append(Constants.Common.ELSE).append(Constants.SpecialSymbol.BLANK)
		.append(Constants.SpecialSymbol.LEFT_BRACE).append(Constants.Common.RETURN)
		.append(Constants.SpecialSymbol.BLANK)
		.append(Constants.SpecialSymbol.DOUBLE_QUOTATION);
		if(mode==RuleEnum.SUM){
			ruleStr.append(Constants.Common.SUM_DEFAULT);
		}else{
			ruleStr.append(Constants.Common.CONCLUSION_DEFAULT);
		}
		ruleStr.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
		.append(Constants.SpecialSymbol.SEMICOLON).append(Constants.SpecialSymbol.RIGHT_BRACE);
	}

	/**
	 * 
	* @Title: createRule 
	* @Description: 生成规则并追加到ruleStr
	* @author chengchen
	* @date 2016年10月17日 下午5:54:36 
	* @param @param ruleModel
	* @param @param rule
	* @param @param ruleStr
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	private static void createRule(RuleModel ruleModel, Rule rule, StringBuffer ruleStr) throws Exception {
		try {
			ruleStr.append(Constants.Common.IF).append(Constants.SpecialSymbol.BLANK)
			.append(Constants.SpecialSymbol.LEFT_);
			List<Exp> expList = rule.getExps().getExp();
			List<ExpGroup> expGroupList = rule.getExps().getExpGroup();
			/** 简单表达式 */
			if (null != expList && expList.size() > 0) {
				createExp(ruleStr, expList, ruleModel);
			}
			/** 组合表达式 */
			createExp(ruleStr, expGroupList, ruleModel, expList.size());
			ruleStr.append(Constants.SpecialSymbol.RIGHT_)
			.append(Constants.SpecialSymbol.LEFT_BRACE)
			.append(Constants.Common.RETURN).append(Constants.SpecialSymbol.BLANK)
			.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
			.append(rule.getResult().getValue())
			.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
			.append(Constants.SpecialSymbol.SEMICOLON).append(Constants.SpecialSymbol.RIGHT_BRACE);
		} catch (Exception e) {
			throw new Exception("XMLUtil.createRule error.", e);
		}
	}

	/**
	 * 生成表达式并追加到ruleStr
	 * @param ruleStr
	 * @param expGroupList
	 * @param ruleModel
	 * @param expSize
	 * @throws Exception
	 */
	private static void createExp(StringBuffer ruleStr, List<ExpGroup> expGroupList, RuleModel ruleModel, int expSize) throws Exception {
		if (null != expGroupList && expGroupList.size() > 0) {
            for (int i = 0, size = expGroupList.size(); i < size; i++) {
                ExpGroup expGroup = expGroupList.get(i);
                if (i != 0 || expSize > 0) {
                    String relStr = "";
                    if(null!=expGroup.getRel()){
                        relStr = expGroup.getRel().value();
                    }
                    RelationEnum relGroupEnum = RelationEnum.getByName(relStr);
                    ruleStr.append(relGroupEnum.getCode());
                }
				if (null != expGroup.getExp() && expGroup.getExp().size() > 0) {
					ruleStr.append(Constants.SpecialSymbol.LEFT_);
					createExp(ruleStr, expGroup.getExp(), ruleModel);
					ruleStr.append(Constants.SpecialSymbol.RIGHT_);
				}
				/* 遍历 expGroup 内部的 expGroupList 和 expList */
				if (null != expGroup.getExpGroup() && expGroup.getExpGroup().size() > 0){
					ruleStr.append(Constants.SpecialSymbol.LEFT_);
					createExp(ruleStr, expGroup.getExpGroup(), ruleModel, expGroup.getExp().size());
					ruleStr.append(Constants.SpecialSymbol.RIGHT_);
				}
            }
        }
	}

	/**
	 * 
	* @Title: createExp 
	* @Description: 生成表达式并追加到ruleStr
	* @author chengchen
	* @date 2016年10月17日 下午5:54:48 
	* @param @param ruleStr
	* @param @param expList
	* @param @param ruleModel
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	private static void createExp(StringBuffer ruleStr, List<Exp> expList, RuleModel ruleModel) throws Exception  {
		try {
			List<Param> paramList = ruleModel.getParams().getParam();
			for (int i = 0, size = expList.size(); i < size; i++) {
				Exp exp = expList.get(i);
				boolean flag = validParamTypeString(paramList, exp.getExpParam().getValue());
				String operStr = "";
				if(null!=exp.getExpOper().getValue()){
					operStr = exp.getExpOper().getValue().value();
				}
				OperatorEnum oper = OperatorEnum.getByName(operStr);
				if (i != 0) {
					String relStr = "";
					if(null!=exp.getRel()){
						relStr = exp.getRel().value();
					}
					RelationEnum relEnum = RelationEnum.getByName(relStr);
					ruleStr.append(relEnum.getCode());
				}
				if(flag&&!Constants.Common.NULL.equals(exp.getExpValue().getValue())){
					if(oper==OperatorEnum.EQ||oper==OperatorEnum.NOT_EQ){
						if(oper==OperatorEnum.NOT_EQ){
							ruleStr.append("!");
						}
						ruleStr.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
						.append(exp.getExpValue().getValue())
						.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
						.append(Constants.SpecialSymbol.POINT).append(Constants.Common.EQUALS)
						.append(Constants.SpecialSymbol.LEFT_)
						.append(Constants.SpecialSymbol.AT).append(exp.getExpParam().getValue())
						.append(Constants.SpecialSymbol.RIGHT_);
					}else{
						ruleStr.append(Constants.SpecialSymbol.AT).append(exp.getExpParam().getValue()).append(oper.getCode())
						.append(Constants.SpecialSymbol.DOUBLE_QUOTATION)
						.append(exp.getExpValue().getValue())
						.append(Constants.SpecialSymbol.DOUBLE_QUOTATION);
					}
				}else{
					ruleStr.append(Constants.SpecialSymbol.AT).append(exp.getExpParam().getValue()).append(oper.getCode())
					.append(exp.getExpValue().getValue());
				}
			}
		} catch (Exception e) {
			throw new Exception("XMLUtil.createExp error.", e);
		}
	}

	/**
	 * 
	* @Title: validParamTypeString 
	* @Description: 验证参数类型是否为String（需要加引号）
	* @author chengchen
	* @date 2016年10月17日 下午5:55:02 
	* @param @param paramList
	* @param @param paramStr
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	private static boolean validParamTypeString(List<Param> paramList, String paramStr) throws Exception {
		try {
			for (Param tmp : paramList) {
				String tmpStr = "";
				if(null!=tmp.getType()){
					tmpStr = tmp.getType().value();
				}
				if (paramStr.equals(tmp.getEnName()) && "string".equals(tmpStr.toLowerCase())) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new Exception("XMLUtil.validParamTypeString error.", e);
		}
		return false;
	}
	
	/**
	 * 
	* @Title: getParsingRuleResult 
	* @Description: 解析评估结论内容，并用评估结论内容id（多个以+连接）替换ruleModel中的result
	* @author chengchen
	* @date 2016年10月19日 下午5:05:30 
	* @param @param ruleModel
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String,Object> getParsingRuleResult(RuleModel ruleModel,int version) throws Exception {
		/**list中的map为评估结果，key为评估结论内容id（多个以+连接）*/
		Map<String,List<Map<String,Object>>> map = new HashMap<>();
		/**简单规则*/
		List<Rule> ruleList = ruleModel.getRules().getRule();
		if (null != ruleList && ruleList.size() > 0) {
			for (Rule rule : ruleList) {
				Map<String,List<Map<String,Object>>> result = replaceResult(rule.getResult().getValue(),version);
				rule.getResult().setValue(result.keySet().iterator().next());
				map.putAll(result);
			}
		}
		/**组合规则*/
		List<RuleGroup> ruleGroupList = ruleModel.getRules().getRuleGroup();
		if (null != ruleGroupList && ruleGroupList.size() > 0) {
			for (RuleGroup ruleGroup : ruleGroupList) {
				List<Rule> _ruleList = ruleGroup.getRule();
				for (int j = 0, jsize = _ruleList.size(); j < jsize; j++) {
					Rule rule = _ruleList.get(j);
					Map<String,List<Map<String,Object>>> result = replaceResult(rule.getResult().getValue(),version);
					rule.getResult().setValue(result.keySet().iterator().next());
					map.putAll(result);
				}
			}
		}
		Map<String,Object> result = new HashMap<>();
		result.put("ruleModel", ruleModel);
		result.put("ruleResultMap", map);
		return result;
	}
	/**
	 * 
	* @Title: replaceResult 
	* @Description: 将结论转换为map，根据版本更替会提供多个方案
	* @author chengchen
	* @date 2016年10月19日 下午6:19:10 
	* @param @param rule
	* @param @param version
	* @param @return    设定参数 
	* @return Map<String,List<Map<String,Object>>>    返回类型 
	* @throws
	 */
	private static Map<String,List<Map<String,Object>>> replaceResult(String rule,int version){
		switch (version) {
		case 1:
			return replaceResult_1(rule,version);
		default:
			return null;
		}
	}
	
	/**
	 * 
	* @Title: replaceResult_1 
	* @Description: 针对孕检系统的xml解析结果
	* @author chengchen
	* @date 2016年10月19日 下午6:20:01 
	* @param @param rule
	* @param @param version
	* @param @return    设定参数 
	* @return Map<String,List<Map<String,Object>>>    返回类型 
	* @throws
	 */
	private static Map<String,List<Map<String,Object>>> replaceResult_1(String rule,int version){
		Map<String,List<Map<String,Object>>> result = new HashMap<>();
		List<Map<String,Object>> list = new ArrayList<>();
		String[] ruleArr = rule.split("\\+");
		StringBuffer key = new StringBuffer();
		for(int i=0,size=ruleArr.length;i<size;i++){
			String tmp = ruleArr[i];
			Map<String,Object> _sub = new HashMap<>();
			String id = IdGen.uuid();
			key.append(id);
			_sub.put("id", id);
			int pos_s = tmp.indexOf("（");
			int pos_e = tmp.lastIndexOf("）");
			String name = "";
			String conclusion = "";
			if(-1==pos_s||-1==pos_e){
				name = PregnantResultKeyEnum.DEFAULT.getName();
				conclusion = tmp;
			}else{
				name = tmp.substring(0,pos_s);
				conclusion = tmp.substring(pos_s+1,pos_e);
			}
			_sub.put("name", name);
			_sub.put("conclusion", conclusion);
			if(i!=size-1){
				key.append("+");
			}
			list.add(_sub);
		}
		result.put(MD5Util.MD5(key.toString()), list);
		return result;
	}
	
}
