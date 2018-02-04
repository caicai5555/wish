package com.foundation.service.indicator.biz.impl;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;
import com.foundation.service.indicator.biz.IIndicatorRuleExpItemsBiz;
import com.foundation.service.indicator.service.IIndicatorRuleExpItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: IndicatorRuleExpItemsBizImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/23 15:49
 * @version V1.0
 */
@Service
public class IndicatorRuleExpItemsBizImpl implements IIndicatorRuleExpItemsBiz{

    @Autowired
    private IIndicatorRuleExpItemsService indicatorRuleExpItemsService;

    @Override
    public List<IndicatorRuleExpItemsDO> getRuleExpItemsRuleId(String indRuleId) throws Exception {
        return indicatorRuleExpItemsService.getRuleExpItemsRuleId(indRuleId);
    }
}
