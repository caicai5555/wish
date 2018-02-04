package com.foundation.service.indicator.biz;

import com.foundation.dao.entity.indicatorSys.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleBiz
 * @Description: 指标体系-指标规则业务
 * @author wangsen
 * @date 2016/11/21 17:40
 * @version V1.0
 */
public interface IIndicatorRuleBiz {

	/**
	 * @Description 指标值相关参数匹配相应指标规则相关信息
	 * @param paramsMap 入参Map，包含指标code,value及其他附加信息
	 * @return
	 * @throws Exception
	 */
	public IndicatorRuleInfo getIndRuleResult(Map<String, Object> paramsMap) throws Exception;

	/**
	 * @Description 根据指标id获取指标规则列表
	 * @param
	 * @return
	 */
	public List<IndicatorRule> getIndRulesByIndId(String indicatorId) throws Exception;
	/**
	 * @Description 根据指标id获取一条指标规则
	 * @param
	 * @return
	 */
	public IndicatorRule getIndRuleByIndId(String indicatorId) throws Exception;

	/**
	 * @Description 删除指标规则组及组下规则
	 * @param
	 * @return
	 */
	public void delIndRules(String groupId) throws Exception;

	/**
	 * @Description 根据主键删除指标规则
	 * @param
	 * @return
	 */
	public void delIndRule(String id) throws Exception;

	/**
	 * @Description 添加指标规则
	 * @param indicatorRuleVO
	 * @return
	 * @throws Exception
	 */
	public void addIndicatorRule(IndicatorRuleVO indicatorRuleVO)throws Exception;

	/**
	 * @Description 修改指标规则
	 * @param indicatorRuleVO
	 * @return
	 * @throws Exception
	 */
	public void editIndicatorRule(IndicatorRuleVO indicatorRuleVO)throws Exception;

	/**
	 * @Description 根据规则Id查询指标规则参数项
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IndicatorRule getIndRuleById(String id)throws Exception;

	/**
	 * @Description 更新指标规则附加信息默认最大最小值等等
	 * @param indRule
	 * @return
	 * @throws Exception
	 */
	public void updateRuleAppendInfo(IndicatorRule indRule) throws Exception;

	/**
	 * @Description 获取指标规则匹配后的相关区间值，并格式化区间值（含表达式）
	 * @param ruleIds 规则组内规则id组
	 * @param params 初始的入参Map
	 * @param groupRuleParams 规则组规则编码字串（如value:sex）
	 * @return
	 */
	List<IndicatorRuleIntervalDO> getIndRuleInterval(List<String> ruleIds, Map<String, String> params, String groupRuleParams) throws Exception;

}
