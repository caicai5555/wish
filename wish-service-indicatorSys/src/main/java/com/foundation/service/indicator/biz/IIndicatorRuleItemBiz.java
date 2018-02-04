package com.foundation.service.indicator.biz;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleItemBiz
 * @Description: 指标体系-指标规则字典业务
 * @author wangsen
 * @date 2016/11/21 17:41
 * @version V1.0
 */
public interface IIndicatorRuleItemBiz {

    /**
     * @Description: 根据code数组查询实体列表
     * @param itemCodes
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleItems> getRuleItemsByCodes(String[] itemCodes) throws Exception;

    /**
     * @Description: 根据组编号查询指标规则表达式项数据，返回Map形式，键为itemCode,值为itemName
     * @param itemGroup
     * @return
     * @throws Exception
     */
    public Map<String,String> getRuleItemMapByGroup(String itemGroup) throws Exception;

    /**
     * @Description: 根据组编号查询指标规则表达式项数据
     * @param itemGroup
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRuleItemByGroup(String itemGroup) throws Exception;

    /**
     * @Description 获取指标规则参数项列表
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleItems> getIndicatorRuleItems() throws Exception;
}
