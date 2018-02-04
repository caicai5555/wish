package com.foundation.service.indicator.biz;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleGroupBiz
 * @Description: 指标体系-指标规则组业务
 * @author wangsen
 * @date 2016/11/23 14:52
 * @version V1.0
 */
public interface IIndicatorRuleGroupBiz {

    /**
     * @Description: 根据指标id获取指标规则组列表
     * @param indicatorId
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleGroup> getIndRuleGroupByIndId(String indicatorId) throws Exception;
    /**
     * @Description: 根据指标id及参数项获取指标规则
     * @param indicatorId
     * @param ruleParams
     * @return
     * @throws Exception
     */
    public IndicatorRuleGroup getIndRuleGroupByParams(String indicatorId, String ruleParams) throws Exception;

    /**
     * @Description: 保存规则组实体
     * @param indicatorRuleGroup
     * @throws Exception
     */
    public void saveIndicatorRuleGroup(IndicatorRuleGroup indicatorRuleGroup) throws Exception;

    /**
     * @Description 查询规则组及组下规则
     * @param params
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleGroupDO> queryRuleGroupAndRules(Map<String,Object> params) throws Exception;
}
