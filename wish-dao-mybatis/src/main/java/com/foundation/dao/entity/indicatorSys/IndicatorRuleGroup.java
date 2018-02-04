package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;

/**
 * @ClassName: IndicatorRuleGroup
 * @Description: 指标规则组实体
 * @author wangsen
 * @date 2016/11/21 15:09
 * @version V1.0
 */
public class IndicatorRuleGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id**/
    private String id;
    /** 指标id**/
    private String indicatorId;
    /** 指标code**/
    private String indicatorCode;
    /** 规则参数(英文) **/
    private String itemCodes;
    /** 规则组，规则参数中文**/
    private String itemNames;
    /** 删除标志 **/
    private Integer delFlag;

    /**----kendo空间指定父子节点用 **/
    private String groupId;


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

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public String getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(String itemCodes) {
        this.itemCodes = itemCodes;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
