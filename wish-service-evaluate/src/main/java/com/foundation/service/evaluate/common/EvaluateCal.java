package com.foundation.service.evaluate.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.simpleEL.eval.DefaultExpressEvalService;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.entity.evaluate.EvaluateRule;

/**
 * 
 * @ClassName: EvaluateCal
 * @Description: 评估计算（单例）SimpleEl实现
 * @author chengchen
 * @date 2016年10月18日 上午9:36:34
 *
 */
public class EvaluateCal {
	private static Log logger = LogFactory.getLog(EvaluateCal.class);
	/**
	 * 宽断言 
	 * (?=exp) 匹配exp前面的位置 
	 * (?<=exp) 匹配exp后面的位置 
	 * (?!exp) 匹配后面跟的不是exp的位置
	 * (?<!exp) 匹配前面不是exp的位置
	 */
	private static final String REG_EX = "(?<=@\\{)(.+?)(?=\\})";

	/** 信息Map,key为evaluateInfo表中的enName，value为EvaluateInfo的列表数据 */
	private Map<String, EvaluateInfo> infoMap = new HashMap<>();

	/** 参数Map,key为evaluateInfo表中的enName，value为EvaluateParam的列表数据 */
	private Map<String, List<EvaluateParam>> paramMap = new HashMap<>();

	/** 规则Map,key为evaluateInfo表中的enName，value为EvaluateRule的列表数据 */
	private Map<String, List<EvaluateRule>> ruleMap = new HashMap<>();

	/** 服务Map,key为evaluateInfo表中的enName，value为DefaultExpressEvalService */
	private Map<String, DefaultExpressEvalService> serviceMap = new HashMap<>();
	/** 字典占位符（初始化时插入数据） */
	private static Map<String, Object> dictMap = new HashMap<>();

	/** 构造函数（私有化） */
	private EvaluateCal() {
	}

	/** 创建实例（通过内部类方式） */
	private static class EvaluateCalSingleton {
		private static EvaluateCal instance = new EvaluateCal();
	}

	public static EvaluateCal getInstance() {
		return EvaluateCalSingleton.instance;
	}

	/**
	 * 
	 * @Title: initInfo @Description: 初始化信息实体Map @author chengchen @date
	 * 2016年10月18日 上午9:42:39 @param @param info @param @param updateFlag
	 * 设定参数 @return void 返回类型 @throws
	 */
	public boolean initInfo(EvaluateInfo info, boolean updateFlag) {
		if (null == info) {
			logger.error("[EvaluateCal.initInfo] info is null.");
			return false;
		}
		String key = info.getEnname();
		if (!updateFlag && infoMap.containsKey(key)) {
			return true;
		}
		infoMap.put(key, info);
		return true;
	}

	/**
	 * 
	 * @Title: initParam @Description: 初始化参数列表Map @author chengchen @date
	 * 2016年10月18日 上午9:43:08 @param @param info @param @param
	 * paramList @param @param updateFlag 设定参数 @return void 返回类型 @throws
	 */
	public boolean initParam(EvaluateInfo info, List<EvaluateParam> paramList, boolean updateFlag) {
		if (null == info || null == paramList || paramList.size() <= 0) {
			logger.error("[EvaluateCal.initParam] info is null or paramList is null or paramList.size == 0.");
			return false;
		}
		String key = info.getEnname();
		if (!updateFlag && paramMap.containsKey(key)) {
			return true;
		}
		paramMap.put(key, paramList);
		return true;
	}
	
	/**
	 * 
	* @Title: getParam 
	* @Description: 根据info获取参数列表
	* @author chengchen
	* @date 2016年12月20日 下午6:38:09 
	* @param @param info
	* @param @return    设定参数 
	* @return List<EvaluateParam>    返回类型 
	* @throws
	 */
	public List<EvaluateParam> getParam(EvaluateInfo info){
		if(null==info){
			logger.error("[EvaluateCal.getParam] info is null.");
			return null;
		}
		return paramMap.get(info.getEnname());
	}
	
	/**
	 * 
	* @Title: getInfo 
	* @Description:  根据info获取缓存中的info
	* @author chengchen
	* @date 2016年12月20日 下午6:39:59 
	* @param @param info
	* @param @return    设定参数 
	* @return EvaluateInfo    返回类型 
	* @throws
	 */
	public EvaluateInfo getInfo(EvaluateInfo info){
		if(null!=info){
			return infoMap.get(info.getEnname());
		}
		return null;
	}

	/**
	 * 
	 * @Title: initRule @Description: 初始化规则列表Map @author chengchen @date
	 * 2016年10月18日 上午9:43:42 @param @param info @param @param
	 * ruleList @param @param updateFlag 设定参数 @return void 返回类型 @throws
	 */
	public boolean initRule(EvaluateInfo info, List<EvaluateRule> ruleList, boolean updateFlag) {
		if (null == info || null == ruleList || ruleList.size() <= 0) {
			logger.error("[EvaluateCal.initRule] info is null or ruleList is null or ruleList.size == 0.");
			return false;
		}
		String key = info.getEnname();
		if (!updateFlag && ruleMap.containsKey(key)) {
			return true;
		}
		ruleMap.put(key, ruleList);
		return true;
	}

	/**
	 * 
	 * @Title: initService @Description: 初始化服务Map @author chengchen @date
	 * 2016年10月18日 上午9:44:24 @param @param info @param @param
	 * paramList @param @param updateFlag 设定参数 @return void 返回类型 @throws
	 */
	public boolean initService(EvaluateInfo info, List<EvaluateParam> paramList, boolean updateFlag) {
		if (null == info || null == paramList || paramList.size() <= 0) {
			logger.error("[EvaluateCal.initService] info is null or paramList is null or paramList.size == 0.");
			return false;
		}
		String key = info.getEnname();
		if (!updateFlag && serviceMap.containsKey(key)) {
			return true;
		}
		DefaultExpressEvalService service = new DefaultExpressEvalService();
		for (EvaluateParam param : paramList) {
			regsiterVariant(service, param);
		}
		service.setAllowMultiStatement(true); // 设置支持多行语句
		serviceMap.put(info.getEnname(), service);
		return true;
	}

	/**
	 * 
	 * @Title: regsiterVariant @Description: 通过字符串注册参数类型 @author chengchen @date
	 * 2016年10月18日 上午9:44:53 @param @param service @param @param param
	 * 设定参数 @return void 返回类型 @throws
	 */
	private void regsiterVariant(DefaultExpressEvalService service, EvaluateParam param) {
		switch (param.getType().toLowerCase()) {
		case "string":
			service.regsiterVariant(String.class, param.getEnname());
			break;
		case "int":
			service.regsiterVariant(int.class, param.getEnname());
			break;
		case "double":
			service.regsiterVariant(double.class, param.getEnname());
			break;
		default:
			service.regsiterVariant(String.class, param.getEnname());
			break;
		}
	}

	/**
	 * 
	 * @Title: init @Description: 初始化汇总，info，paramList，ruleList @author
	 * chengchen @date 2016年10月18日 上午9:45:17 @param @param info @param @param
	 * paramList @param @param ruleList @param @param updateFlag
	 * 设定参数 @param @param dirtMap 字典数据Map(key值请以dict_开头) @return void
	 * 返回类型 @throws
	 */
	public boolean init(EvaluateInfo info, List<EvaluateParam> paramList, List<EvaluateRule> ruleList,
			boolean updateFlag, Map<String, Object> dirt) {
		boolean infoFlag = initInfo(info, updateFlag);
		boolean paramFlag = initParam(info, paramList, updateFlag);
		boolean ruleFlag = initRule(info, ruleList, updateFlag);
		boolean serviceFlag = initService(info, paramList, updateFlag);
		if (!infoFlag) {
			logger.error("[EvaluateCal.initInfo] is error.");
			return false;
		}
		if (!paramFlag) {
			logger.error("[EvaluateCal.initParam] is error.");
			return false;
		}
		if (!ruleFlag) {
			logger.error("[EvaluateCal.initRule] is error.");
			return false;
		}
		if (!serviceFlag) {
			logger.error("[EvaluateCal.initService] is error.");
			return false;
		}
		if (null != dirt) {
			dictMap.putAll(dirt);
		}
		return true;
	}

	/**
	 * 
	* @Title: clear 
	* @Description: 清空类中的Map缓存
	* @author chengchen
	* @date 2016年12月20日 下午2:20:39 
	* @param     设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void clear() {
		infoMap.clear();
		paramMap.clear();
		ruleMap.clear();
		serviceMap.clear();
		dictMap.clear();
	}
	
	/**
	 * 
	* @Title: remove 
	* @Description: 从map缓存中移除指定的info
	* @author chengchen
	* @date 2016年12月20日 下午2:20:46 
	* @param @param info    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void remove(EvaluateInfo info) {
		if(null!=info){
			infoMap.remove(info.getEnname());
			paramMap.remove(info.getEnname());
			ruleMap.remove(info.getEnname());
			serviceMap.remove(info.getEnname());
		}
	}
	/**
	 * 
	* @Title: clearDict 
	* @Description: 清空类中的Map缓存
	* @author chengchen
	* @date 2016年12月20日 下午2:21:38 
	* @param     设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void clearDict() {
		dictMap.clear();
	}

	/**
	 * 
	 * @Title: execute @Description: 执行评估计算 @author chengchen @date 2016年10月18日
	 * 上午9:46:19 @param @param data @param @param
	 * info @param @return @param @throws Exception 设定参数 @return Object
	 * 返回类型 @throws
	 */
	public Object execute(Map<String, Object> data, EvaluateInfo info) throws Exception {
		if (null == info) {
			logger.error("[EvaluateCal.execute] info is null");
			throw new Exception("[EvaluateCal.execute] info is null");
		}
		String key = info.getEnname();
		/*if (!infoMap.containsKey(key)) {
			logger.error("[EvaluateCal.execute] infoMap not contain info.enName,please init");
			throw new Exception("[EvaluateCal.execute] infoMap not contain info.enName,please init");
		}
		if (!paramMap.containsKey(key)) {
			logger.error("[EvaluateCal.execute] paramMap not contain info.enName,please init");
			throw new Exception("[EvaluateCal.execute] paramMap not contain info.enName,please init");
		}*/
		if (!ruleMap.containsKey(key)) {
			logger.error("[EvaluateCal.execute] ruleMap not contain info.enName,please init");
			throw new Exception("[EvaluateCal.execute] ruleMap not contain info.enName,please init");
		}
		if (!serviceMap.containsKey(key)) {
			logger.error("[EvaluateCal.execute] serviceMap not contain info.enName,please init");
			throw new Exception("[EvaluateCal.execute] serviceMap not contain info.enName,please init");
		}
		List<EvaluateRule> rules = ruleMap.get(key);
		DefaultExpressEvalService service = serviceMap.get(key);
		if (null != rules && null != service) {
			/** 结论类型，只返回最后结果 */
			if (RuleEnum.CONCLUSION.getId() == info.getMode()) {
				for (EvaluateRule rule : rules) {
					Object tmp = service.eval(data, rule.getRule());
					if (!Constants.Common.CONCLUSION_DEFAULT.equals(tmp.toString())) {
						return tmp;
					}
				}
				return Constants.Common.CONCLUSION_DEFAULT;
			}
			/** 求和类型，返回总数 */
			if (RuleEnum.SUM.getId() == info.getMode()) {
				double score = 0D;
				for (EvaluateRule rule : rules) {
					Object tmp = service.eval(data, rule.getRule());
					score += Double.parseDouble(tmp.toString());
				}
				return score;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: converType @Description: 将val转换为对应的类型 @author chengchen @date
	 * 2016年10月18日 上午9:46:47 @param @param val @param @param
	 * type @param @return @param @throws Exception 设定参数 @return Object
	 * 返回类型 @throws
	 */
	public static Object converType(String val, String type) throws Exception {
		if (null == type) {
			return null;
		}
		switch (type.toLowerCase()) {
		case "int":
			if (StringUtils.isBlank(val)) {
				return null;
			}
			return Integer.parseInt(val);
		case "double":
			if (StringUtils.isBlank(val)) {
				return null;
			}
			return Double.parseDouble(val);
		case "string":
			return String.valueOf(val);
		default:
			return val;
		}
	}

	/**
	 * 
	 * @Title: replaceSpecialChar @Description:
	 * 替换规则中的占位符，@{占位符}，占位符以dict_开头为字典，以val_开头为变量本身 @author chengchen @date
	 * 2016年10月18日 下午5:20:32 @param @param rule @param @return @param @throws
	 * Exception 设定参数 @return String 返回类型 @throws
	 */
	public static String replaceSpecialChar(String rule, Map<String, Object> data) throws Exception {
		Pattern pat = Pattern.compile(REG_EX);
		Matcher mat = pat.matcher(rule);
		while (mat.find()) {
			String specialChar = mat.group();
			for (SpecialCharEnum tmp : SpecialCharEnum.values()) {
				if (specialChar.indexOf(tmp.getName()) > -1) {
					String key = specialChar.replace(tmp.getName(), "");
					switch (tmp.getFlag()) {
					case 1:
						// 字典中获取数据
						if (!dictMap.containsKey(specialChar) && !dictMap.containsKey(key)) {
							return "";
						}
						if (dictMap.containsKey(specialChar)) {
							rule = rule.replace("@{" + specialChar + "}", dictMap.get(specialChar).toString());
						}
						if (dictMap.containsKey(key)) {
							rule = rule.replace("@{" + specialChar + "}", dictMap.get(key).toString());
						}
						break;
					case 2:
						// 值自身替换
						if (!data.containsKey(key)) {
							return "";
						}
						rule = rule.replace("@{" + specialChar + "}", data.get(key).toString());
						break;
					default:
						break;
					}
				}
			}
		}
		return rule;
	}

	/**
	 * 
	* @Title: contains 
	* @Description: 通过评估信息的英文判断是否已经缓存
	* @author chengchen
	* @date 2016年10月29日 下午9:56:33 
	* @param @param info
	* @param @return    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean contains(EvaluateInfo info) {
		return infoMap.containsKey(info.getEnname()) && paramMap.containsKey(info.getEnname())
				&& ruleMap.containsKey(info.getEnname()) && serviceMap.containsKey(info.getEnname());
	}


}
