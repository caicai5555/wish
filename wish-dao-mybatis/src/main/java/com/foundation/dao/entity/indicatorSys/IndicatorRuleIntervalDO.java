package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @Description: 指标匹配返回信息的区间值实体类
 * @ClassName: DisIndicatorRuleExpItemsVO
 * @Author Samwang
 * @Date 2016/9/8 10:23
 * @Version V1.0
 */
public class IndicatorRuleIntervalDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 指标id**/
    /** 标准值
     * 良:1
     * 中:3
     * 差:0
     * **/
    private Integer valueTab;
    /** 结论（提示信息）**/
    private String conclusion;
    /** 建议 **/
    private String suggest;
    /** 指标规则表达式项**/
    private Set<String> value = new TreeSet<>();

    public Integer getValueTab() {
        return valueTab;
    }

    public void setValueTab(Integer valueTab) {
        this.valueTab = valueTab;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<String> getValue() {
        return value;
    }

    public void setValue(Set<String> value) {
        this.value = value;
    }
}
