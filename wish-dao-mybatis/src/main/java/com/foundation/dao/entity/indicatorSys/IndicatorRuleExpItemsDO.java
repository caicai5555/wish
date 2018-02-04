package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 指标规则表达式及关联表达式项信息
 * @ClassName: DisIndicatorRuleExpItemsDO
 * @Author Samwang
 * @Date 2016/9/8 10:23
 * @Version V1.0
 */
public class IndicatorRuleExpItemsDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 表达式项名称 **/
    private String itemName;
    /** 表达式项code **/
    private String itemCode;
    /** 表达式项类型，0：确定值，1：区间值 **/
    private String itemType;
    /** 表达式项子组 **/
    private String itemChildGroup;
    /** 表达式参数项列表 **/
    private List<IndicatorRuleExpItems> ruleExpItems = new ArrayList<IndicatorRuleExpItems>();
    /** 参数项关联值 **/
    private List<Map<String,Object>> itemValues;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemChildGroup() {
        return itemChildGroup;
    }

    public void setItemChildGroup(String itemChildGroup) {
        this.itemChildGroup = itemChildGroup;
    }

    public List<IndicatorRuleExpItems> getRuleExpItems() {
        return ruleExpItems;
    }

    public void setRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) {
        this.ruleExpItems = ruleExpItems;
    }

    public List<Map<String, Object>> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<Map<String, Object>> itemValues) {
        this.itemValues = itemValues;
    }
}
