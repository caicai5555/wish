package com.foundation.service.indicator.service;


import com.foundation.dao.entity.indicatorSys.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleService
 * @Description: 指标体系-指标规则服务
 * @author wangsen
 * @date 2016/11/21 11:36
 * @version V1.0
 */
public interface IIndicatorRuleService {

	/**
	 * @Description: 保存指标规则
	 * @return
	 * @throws Exception
	 */
	public void saveIndicatorRule(IndicatorRule IndexRule) throws Exception;
	/**
	 * @Description: 更新指标规则
	 * @return
	 * @throws Exception
	 */
	public void updateIndicatorRule(IndicatorRule indsRule)throws Exception;


	/**
	 * @Description: 根据指标id查询指标规则列表
	 * @return
	 * @throws Exception
	 */
	public List<IndicatorRule> getIndRulesByIndId(String indicatorId) throws Exception;
	/**
	 * @Description: 根据指标id查询一条指标规则
	 * @return
	 * @throws Exception
	 */
	public IndicatorRule getIndRuleByIndId(String indicatorId) throws Exception;

	/**
	 * @Description: 根据主键删除指标规则
	 * @return
	 * @throws Exception
	 */
	public void delIndRuleById(String id) throws Exception;

	/**
	 * @Description: 根据父id删除规则
	 * @param groupId
	 * @throws Exception
	 */
	public void delIndRulesByGroupId(String groupId) throws Exception;

	/**
	 * @Description 根据规则Id查询指标规则参数项
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public IndicatorRule getIndRuleById(String id)throws Exception;


	/**
	 * @Description 根据规则组内规则Id及参数项（类型为多选值）查询规则的不同区间的提示建议等信息
	 * @param ruleIds
	 * @param innerItem
	 * @return
	 * @throws Exception
	 */

	public List<IndicatorRuleIntervalDO> getIndRuleInterval(List<String> ruleIds, Map<String, String> innerItem) throws Exception;

}
