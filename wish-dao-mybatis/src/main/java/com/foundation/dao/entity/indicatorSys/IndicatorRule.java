package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;

/**
 * @ClassName: IndicatorRule
 * @Description: 指标规则实体
 * @author wangsen
 * @date 2016/11/21 15:07
 * @version V1.0
 */
public class IndicatorRule implements Serializable{
    private static final long serialVersionUID = 1L;

    /** 主键id**/
    private String id;
    /** 指标id**/
    private String indicatorId;
    /** 指标code**/
    private String indicatorCode;
    /** 标准值
     * 良:1
     * 中:3
     * 差:0
     * **/
    private String valueTab;
    /** 标准值颜色**/
    private String color;
    /** 结论（提示信息）**/
    private String conclusion;
    /** 建议 **/
    private String suggest;
    /** 表达式 **/
    private String expression;
    /** 父Id **/
    private String groupId;
    /** 删除标志 **/
    private Integer delFlag;
    /** 单位 **/
    private String unit;
    /** 默认最小值 **/
    private Double defaultMin;
    /** 默认最大值 **/
    private Double defaultMax;
    /** 参数项个数 **/
    private Integer itemCount;

    /**指标名称**/
    public String indicatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
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

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }
}
