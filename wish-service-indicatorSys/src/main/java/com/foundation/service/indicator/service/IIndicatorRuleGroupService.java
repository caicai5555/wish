package com.foundation.service.indicator.service;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IIndicatorRuleItemService
 * @Description: 指标体系-指标规则组数据服务
 * @author wangsen
 * @date 2016/11/21 17:14
 * @version V1.0
 */
public interface IIndicatorRuleGroupService {

    /**
     * @Description: 根据指标id及参数项获取指标规则
     * @param indicatorId
     * @param itemCodes
     * @return
     * @throws Exception
     */
    public IndicatorRuleGroup getIndRuleGroupByParams(String indicatorId, String itemCodes) throws Exception;

    /**
     * @Description: 根据指标id获取指标规则组列表
     * @param indicatorId
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleGroup> getIndRuleGroupByIndId(String indicatorId) throws Exception;

    /**
     * @Description: 保存规则组实体
     * @param indicatorRuleGroup
     * @throws Exception
     */
    public void saveIndicatorRuleGroup(IndicatorRuleGroup indicatorRuleGroup) throws Exception;

    /**
     * @Description: 根据主键Id删除规则组（逻辑删除，更新del_flag=1）
     * @param id
     * @return
     * @throws Exception
     */
    public Integer delIndRuleGroupById(String id) throws  Exception;
    /**
     * @Description 根据指标code查询规则组&规则列表
     * @param params
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleGroupDO> getIndGroupAndRules(Map<String,Object> params) throws Exception;

}
