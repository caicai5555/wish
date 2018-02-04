package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: IndicatorRuleExpItems
 * @Description: 指标规则表达式子项实体
 * @author wangsen
 * @date 2016/11/21 15:08
 * @version V1.0
 */
public class IndicatorRuleExpItems implements Serializable {
    private static final long serialVersionUID = 1L;

    public IndicatorRuleExpItems() {
        this.createDate = new Date();
    }

    /** 主键ID **/
    private String id;
    /** 指标规则ID **/
    private String indruleId;
    /** 参数项code **/
    private String itemCode;
    /** 参数操作符 **/
    private String itemOpt;
    /** 参数值 **/
    private String itemValue;
    /** 删除标记
     * 0：未删除
     * 1：删除
     * **/
    private Integer delFlag;

    /** 创建时间**/
    private Date createDate;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndruleId() {
        return indruleId;
    }

    public void setIndruleId(String indruleId) {
        this.indruleId = indruleId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemOpt() {
        return itemOpt;
    }

    public void setItemOpt(String itemOpt) {
        this.itemOpt = itemOpt;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
