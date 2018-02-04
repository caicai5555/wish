package com.foundation.service.indicator.biz.impl;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.service.common.IndicatorConstants;
import com.foundation.service.indicator.biz.IIndicatorRuleItemBiz;
import com.foundation.service.indicator.service.IIndicatorRuleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleItemBizImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/21 17:42
 * @version V1.0
 */
@Service
public class IndicatorRuleItemBizImpl implements IIndicatorRuleItemBiz {

    @Autowired
    private IIndicatorRuleItemService indicatorRuleItemService;

    @Override
    public List<IndicatorRuleItems> getRuleItemsByCodes(String[] itemCodes) throws Exception {
        return indicatorRuleItemService.getRuleItemsByCodes(itemCodes);
    }

    @Override
    public Map<String, String> getRuleItemMapByGroup(String itemGroup) throws Exception {
        return indicatorRuleItemService.getRuleItemMapByGroup(itemGroup);
    }

    @Override
    public List<Map<String, Object>> getRuleItemByGroup(String itemGroup) throws Exception {
        return indicatorRuleItemService.getRuleItemByGroup(itemGroup);
    }

    @Override
    public List<IndicatorRuleItems> getIndicatorRuleItems() throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itemGroup", IndicatorConstants.BASEDATA_PARAM);
        return indicatorRuleItemService.getIndicatorRuleItems(params);
    }
}
