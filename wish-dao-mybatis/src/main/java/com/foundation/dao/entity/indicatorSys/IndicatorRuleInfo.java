package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @Description: 指标规则匹配返回信息实体
 * @ClassName: DisIndicatorRuleGroupVO
 * @Author Samwang
 * @Date 2016/9/8 10:23
 * @Version V1.0
 */
public class IndicatorRuleInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 指标Id **/
    private String indicatorId;
    /** 指标代码*/
    private String indicatorType;
    /** 指标名称 **/
    private String indicatorName;
    /** 传入指标值 **/
    private String value;
    /** 标准值
     * 良:1
     * 中:3
     * 差:0
     * **/
    private String valueTab;
    private String unit;
    /** 颜色 **/
    private String color;
    /** 提示（结论）**/
    private String msg/*conclusion -> msg*/;
    /** 建议 **/
    private String suggest;
    /** 是否归档，默认1：归档，0：不归档'**/
    private Integer isSaveArchives /*isArchives*/;
    /** 匹配指标规则组区间值 **/
    private String intervalValue;
    /**最小值*/
    private Double minVal;
    /**最大值*/
    private Double maxVal;
    /** 默认最小值 **/
    private Double defaultMin;
    /** 默认最大值 **/
    private Double defaultMax;
    /** 刻度尺集合 **/
    private Set<Double> scaleList;

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueTab() {
        return valueTab;
    }

    public void setValueTab(String valueTab) {
        this.valueTab = valueTab;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Integer getIsSaveArchives() {
        return isSaveArchives;
    }

    public void setIsSaveArchives(Integer isSaveArchives) {
        this.isSaveArchives = isSaveArchives;
    }

    public String getIntervalValue() {
        return intervalValue;
    }

    public void setIntervalValue(String intervalValue) {
        this.intervalValue = intervalValue;
    }

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
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

    public Set<Double> getScaleList() {
        return scaleList;
    }

    public void setScaleList(Set<Double> scaleList) {
        this.scaleList = scaleList;
    }


}
