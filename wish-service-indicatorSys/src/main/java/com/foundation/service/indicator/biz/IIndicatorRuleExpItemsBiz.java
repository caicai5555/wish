package com.foundation.service.indicator.biz;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;

import java.util.List;

/**
 * @ClassName: IIndicatorRuleExpItems
 * @Description: 指标体系-指标规则表达式项服务
 * @author wangsen
 * @date 2016/11/23 15:48
 * @version V1.0
 */
public interface IIndicatorRuleExpItemsBiz {

    /**
     * @Description:根据规则Id查询指标规则参数项
     * @param indRuleId
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleExpItemsDO> getRuleExpItemsRuleId(String indRuleId)throws Exception;
}
