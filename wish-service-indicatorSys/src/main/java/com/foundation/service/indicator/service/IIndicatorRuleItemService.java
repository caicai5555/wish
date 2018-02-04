package com.foundation.service.indicator.service;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleItemService
 * @Description: 指标体系-指标规则字典表数据服务
 * @author wangsen
 * @date 2016/11/21 17:14
 * @version V1.0
 */
public interface IIndicatorRuleItemService {


    /**
     * @Description: 根据入参查询实体列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleItems> getIndicatorRuleItems(Map params) throws Exception;

    /**
     * @Description: 根据code数组查询实体列表
     * @param itemCodes
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleItems> getRuleItemsByCodes(String[] itemCodes) throws Exception;
    /**
     * @Description: 根据组编号查询指标规则表达式项数据
     * @param itemGroup
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getRuleItemByGroup(String itemGroup) throws Exception;
    /**
     * @Description: 根据组编号查询指标规则表达式项数据，返回Map形式，键为itemCode,值为itemName
     * @param itemGroup
     * @return
     * @throws Exception
     */
    public Map<String, String> getRuleItemMapByGroup(String itemGroup) throws Exception;
    /**
     * @Description 从基础表根据参数项组名和参数项类型获取规则参数数据列表
     * @param itemGroup
     * @param itemType
     * @return
     * @throws Exception
     */
    public Map<String, String> getRuleItemByGroupAndType(String itemGroup, String itemType) throws Exception;

}
