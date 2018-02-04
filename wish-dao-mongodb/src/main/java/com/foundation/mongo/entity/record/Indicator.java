package com.foundation.mongo.entity.record;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

/**
 * @author wangsen
 * @ClassName: Indicator
 * @Description: 指标记录实体（mongo）
 * @date 2016/10/19 9:25
 */
@Document
public class Indicator extends BaseRecordEntity{
    @Transient
    private Log logger = LogFactory.getLog(Indicator.class);

    /** 用户Id */
/*    private String userId;*/
    /** 指标Id **/
   /* private Long indicatorId;*/
    /** 指标代码*/
/*    private String code;*/
    /** 指标名称 **/
    private String name;
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
    private String conclusion /*as msg*/;
    /** 建议 **/
    private String suggest;
    /** 匹配指标规则组区间值 **/
    private String intervalValue;
    /**最小值*/
    private Double minVal;
    /**最大值*/
    private Double maxVal;
    /**试剂盒指标范围最小值*/
    private Double rangeMinVal;
    /**试剂盒指标范围最大值*/
    private Double rangeMaxVal;
    /** 默认最小值 **/
    private Double defaultMin;
    /** 默认最大值 **/
    private Double defaultMax;
    /** 刻度尺集合 **/
    private Set<Double> scaleList;

    @Override
    public boolean validate() {
        if(StringUtils.isEmpty(userId)
                ||StringUtils.isEmpty(code)
                || StringUtils.isEmpty(value)
                || StringUtils.isEmpty(certNum)){
            logger.error("入参不正确："+ this.toString());

            return false;
        }
        return true;
    }

/*    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }*/

/*    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getRangeMinVal() {
        return rangeMinVal;
    }

    public void setRangeMinVal(Double rangeMinVal) {
        this.rangeMinVal = rangeMinVal;
    }

    public Double getRangeMaxVal() {
        return rangeMaxVal;
    }

    public void setRangeMaxVal(Double rangeMaxVal) {
        this.rangeMaxVal = rangeMaxVal;
    }

    @Override
    public String toString() {
        return "Indicator{" +
                "***userId='" + userId + '\'' +
                ", ***code='" + code + '\'' +
                ",***certNum='" + certNum + '\'' +
                ", name='" + name + '\'' +
                ", ***value='" + value + '\'' +
                ", valueTab='" + valueTab + '\'' +
                ", unit='" + unit + '\'' +
                ", color='" + color + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", suggest='" + suggest + '\'' +
                ", intervalValue='" + intervalValue + '\'' +
                ", minVal=" + minVal +
                ", maxVal=" + maxVal +
                ", defaultMin=" + defaultMin +
                ", defaultMax=" + defaultMax +
                ", scaleList=" + scaleList +
                '}';
    }
}
