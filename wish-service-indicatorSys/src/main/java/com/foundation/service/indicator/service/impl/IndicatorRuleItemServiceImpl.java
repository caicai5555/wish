package com.foundation.service.indicator.service.impl;

import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.dao.modules.read.indicatorSys.IndicatorRuleItemsDaoR;
import com.foundation.service.indicator.service.IIndicatorRuleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleItemServiceImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/21 17:21
 * @version V1.0
 */
@Service
public class IndicatorRuleItemServiceImpl implements IIndicatorRuleItemService{

    @Autowired
    private IndicatorRuleItemsDaoR indicatorRuleItemsDaoR;

    @Override
    public List<IndicatorRuleItems> getIndicatorRuleItems(Map params) throws Exception {
        return indicatorRuleItemsDaoR.queryList(params);
    }

    @Override
    public List<IndicatorRuleItems> getRuleItemsByCodes(String[] itemCodes) throws Exception {
        return indicatorRuleItemsDaoR.queryByItemCodes(itemCodes);
    }

    @Override
    public List<Map<String, Object>> getRuleItemByGroup(String itemGroup) throws Exception {
        return indicatorRuleItemsDaoR.queryByItemGroup(itemGroup);
    }

    @Override
    public Map<String, String> getRuleItemMapByGroup(String itemGroup) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itemGroup",itemGroup);
        return extractToMap(indicatorRuleItemsDaoR.queryList(params));
    }

    @Override
    public Map<String, String> getRuleItemByGroupAndType(String itemGroup, String itemType) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("itemGroup",itemGroup);
        params.put("itemType",itemType);
        return extractToMap(indicatorRuleItemsDaoR.queryList(params));
    }


    /**
     * @Description 将结果集抽取成两列,结果转map key->value
     * @param result
     * @return
     * @throws Exception
     */
    private Map<String, String> extractToMap(List<IndicatorRuleItems> result) throws Exception {
        if(null == result || result.size()==0) {
            return  null;
        }
        Map<String, String> extractResult = new HashMap<>();
        for(IndicatorRuleItems indicatorRuleItem : result){
            extractResult.put(indicatorRuleItem.getItemCode(), indicatorRuleItem.getItemName());
        }
        return extractResult;
    }
}
