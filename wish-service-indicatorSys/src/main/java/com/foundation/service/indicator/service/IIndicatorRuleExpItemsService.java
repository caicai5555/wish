package com.foundation.service.indicator.service;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItems;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;

import java.util.List;

/**
 * @ClassName: IIndicatorRuleExpItems
 * @Description: 指标体系-指标规则表达式子项服务
 * @author wangsen
 * @date 2016/11/22 10:20
 * @version V1.0
 */
public interface IIndicatorRuleExpItemsService {

    /**
     * @Description: 批量插入
     * @param ruleExpItems
     * @throws Exception
     */
    public void saveIndRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) throws Exception;

    /**
     * @Description: 批量更新
     * @param ruleExpItems
     * @throws Exception
     */
    public void updateIndRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) throws Exception;

    /**
     * @Description: 根据规则id删除表达式子项
     * @param indruleId 规则组Id
     * @throws Exception
     */
    public void deleteByIndruleId(String indruleId) throws Exception;

    /**
     * @Description: 根据规则组id删除表达式子项
     * @param ruleGroupId 指标规则组Id->规则表groupId字段
     * @throws Exception
     */
    public void deleteByRuleGroupId(String ruleGroupId) throws Exception;

    /**
     * @Description:根据规则Id查询指标规则参数项
     * @param indRuleId
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleExpItemsDO> getRuleExpItemsRuleId(String indRuleId)throws Exception;
}
