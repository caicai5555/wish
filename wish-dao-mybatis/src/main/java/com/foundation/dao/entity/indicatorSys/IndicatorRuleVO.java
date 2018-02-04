package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 封装页面数据实体类，用于View,Controller，model间数据传递
 * @ClassName: DisIndicatorRuleVO
 * @Author Samwang
 * @Date 2016/9/8 10:20
 * @Version V1.0
 */


public class IndicatorRuleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 指标Id **/
    private String indicatorId;
    /** 指标名称 **/
    private String indicatorName;
    /** 指标规则组id **/
    private String groupId;
    /** 指标规则Id **/
    private String indruleId;
    /** 标准值 **/
    private String valueTab;
    /** 颜色 **/
    private String color;
    /** 结论（提示信息）**/
    private String conclusion;
    /** 建议 **/
    private String suggest;
    /** 单位 **/
    private String unit;
    /** 默认最小值**/
    private Double defaultMin;
    /** 默认最大值 **/
    private Double defaultMax;

    /** 表达式项列表 **/
    private List<IndicatorRuleExpItems> ruleExpItems ;

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIndruleId() {
        return indruleId;
    }

    public void setIndruleId(String indruleId) {
        this.indruleId = indruleId;
    }

    public String getValueTab() {
        return valueTab;
    }

    public void setValueTab(String valueTab) {
        this.valueTab = valueTab;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getDefaultMin() {
        return defaultMin;
    }

    public void setDefaultMin(Double defaultMin) {
        this.defaultMin = defaultMin;
    }

    public Double getDefaultMax() {
        return defaultMax;
    }

    public void setDefaultMax(Double defaultMax) {
        this.defaultMax = defaultMax;
    }

    public List<IndicatorRuleExpItems> getRuleExpItems() {
        return ruleExpItems;
    }

    public void setRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) {
        this.ruleExpItems = ruleExpItems;
    }


    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

}
