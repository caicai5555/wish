package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Description: 指标规则组信息及组关联规则列表
 * @ClassName: IndicatorRuleGroupDO
 * @Author Samwang
 * @Date 2016/9/8 10:23
 * @Version V1.0
 */
public class IndicatorRuleGroupDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 指标Id **/
    private String indicatorId;
    /** 指标code **/
    private String indicatorCode;
    /** 规则组Id **/
    private String ruleGroupId;
    /** 参数项code集合，value:sex... **/
    private String itemCodes;
    /** 参数项name集合**/
    private String itemNames;
    /** 组内规则Id数组，知道干啥用不，就不告诉你查询指标规则表达式项值用的**/
    private List<String> ruleIds;
    /** 规则表达式列表 **/
    private List<IndicatorRule> indicatorRules;

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getRuleGroupId() {
        return ruleGroupId;
    }

    public void setRuleGroupId(String ruleGroupId) {
        this.ruleGroupId = ruleGroupId;
    }

    public String getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(String itemCodes) {
        this.itemCodes = itemCodes;
    }

    public List<String> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<String> ruleIds) {
        this.ruleIds = ruleIds;
    }

    public List<IndicatorRule> getIndicatorRules() {
        return indicatorRules;
    }

    public void setIndicatorRules(List<IndicatorRule> indicatorRules) {
        this.indicatorRules = indicatorRules;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }
}
