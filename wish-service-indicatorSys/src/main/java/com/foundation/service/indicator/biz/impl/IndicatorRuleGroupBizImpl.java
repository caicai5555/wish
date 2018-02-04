package com.foundation.service.indicator.biz.impl;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.service.common.IndicatorConstants;
import com.foundation.service.indicator.biz.IIndicatorRuleGroupBiz;
import com.foundation.service.indicator.service.IIndicatorRuleGroupService;
import com.foundation.service.indicator.service.IIndicatorRuleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleGroupBizImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/23 14:53
 * @version V1.0
 */
@Service
public class IndicatorRuleGroupBizImpl implements IIndicatorRuleGroupBiz{

    @Autowired
    private IIndicatorRuleGroupService indicatorRuleGroupService;
    @Autowired
    private IIndicatorRuleItemService indicatorRuleItemService;

    @Override
    public List<IndicatorRuleGroup> getIndRuleGroupByIndId(String indicatorId) throws Exception {
        return indicatorRuleGroupService.getIndRuleGroupByIndId(indicatorId);
    }

    @Override
    public IndicatorRuleGroup getIndRuleGroupByParams(String indicatorId, String ruleParams) throws Exception {
        return indicatorRuleGroupService.getIndRuleGroupByParams(indicatorId,ruleParams);
    }

    @Override
    public void saveIndicatorRuleGroup(IndicatorRuleGroup indicatorRuleGroup) throws Exception {

        //拼接参数组名
        String[] itemsCodeArray = indicatorRuleGroup.getItemCodes().split(IndicatorConstants.SYMBOL_COLON);
        List<IndicatorRuleItems> ruleItems = indicatorRuleItemService.getRuleItemsByCodes(itemsCodeArray);
        StringBuffer itemNames = new StringBuffer();
        for (IndicatorRuleItems item : ruleItems) {
            itemNames.append(item.getItemName()).append(":");
        }
        itemNames.deleteCharAt(itemNames.length() - 1);
        indicatorRuleGroup.setItemNames(itemNames.toString());
        indicatorRuleGroupService.saveIndicatorRuleGroup(indicatorRuleGroup);
    }

    @Override
    public List<IndicatorRuleGroupDO> queryRuleGroupAndRules(Map<String,Object> params) throws Exception {
        return indicatorRuleGroupService.getIndGroupAndRules(params);
    }
}
