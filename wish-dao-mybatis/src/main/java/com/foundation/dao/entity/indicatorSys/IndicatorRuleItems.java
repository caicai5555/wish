package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleItems
 * @Description: 指标规则参数字典表
 * @author wangsen
 * @date 2016/11/21 16:31
 * @version V1.0
 */
public class IndicatorRuleItems implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 参数项名称 **/
    private String itemName;
    /** 参数项code **/
    private String itemCode;
    /** 参数项类型，0：确定值，1：区间值 **/
    private String itemType;
    /** 拼音简写用于分组 **/
    private String itemGroup;
    /** 参数项子组 **/
    private String itemChildGroup;
    /** 组排序 **/
    private String itemIndex;

    /** 参数项关联值 **/
    public List<Map<String,Object>> itemValues;


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

    public String getItemGroup() {
        return itemGroup;
    }

    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }

    public String getItemChildGroup() {
        return itemChildGroup;
    }

    public void setItemChildGroup(String itemChildGroup) {
        this.itemChildGroup = itemChildGroup;
    }

    public String getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(String itemIndex) {
        this.itemIndex = itemIndex;
    }

    public List<Map<String, Object>> getItemValues() {
        return itemValues;
    }

    public void setItemValues(List<Map<String, Object>> itemValues) {
        this.itemValues = itemValues;
    }
}
