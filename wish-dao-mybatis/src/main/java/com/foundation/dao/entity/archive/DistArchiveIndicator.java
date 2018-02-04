/**
 * @Title: DistArchiveIndex.java
 * @Package com.foundation.dao.entity.archive
 * @Description: 档案-指标项字典实体类
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/13 11:46
 */
package com.foundation.dao.entity.archive;


import com.foundation.common.bean.Constants;
import com.foundation.dao.util.DataEntity;

import java.util.Date;

/**
 * @Title: DistArchiveIndex
 * @Description: 档案-指标项字典实体类
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/13 11:45
 */
public class DistArchiveIndicator extends DataEntity<DistArchiveIndicator> {

    public DistArchiveIndicator() {
        this.delFlag= Constants.DEL_FLAG_NORMAL;
        this.createDate=new Date();
        this.updateDate=new Date();
    }

    /**
     * 主键
     */
    private String id;
    /**
     * 指标编码
     */
    private String code;

    /**
     * 标签标题
     */
    private String name;
    /**
     * 标签类型
     */
    private String type;
    /**
     * 标签描述
     */
    private String remark;
    /**
     * 单位
     */
    private String unit;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     *删除标志，0是未删除1是删除
     */
    private Integer delFlag;
    /**
     * 添加指标规则标志，0已经添加规则1未添加规则
     */
    private String addedRuleFlag;
    /**
     * 是否归档标志，是否归档:默认1：归档，0：不归档
     */
    private String archiveFlag;
    /**
     * 最小值
     */
    private Float minValue;
    /**
     * 最大值
     */
    private Float maxValue;
    /**
     * 性别,0:男，1：女
     */
    private Integer sex;

    //----------孕检同步
    /**
     * 孕检指标存在表名
     */
    private String tableName;

    /**
     * 孕检指标所在表的列名
     */
    private String columnName;

    /**
     * 列多值，1：是，0：否
     */
    private Integer isChar;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public Integer getDelFlag() {
        return delFlag;
    }

    @Override
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getAddedRuleFlag() {
        return addedRuleFlag;
    }

    public void setAddedRuleFlag(String addedRuleFlag) {
        this.addedRuleFlag = addedRuleFlag;
    }

    public String getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(String archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public Float getMinValue() {
        return minValue;
    }

    public void setMinValue(Float minValue) {
        this.minValue = minValue;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Float maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getIsChar() {
        return isChar;
    }

    public void setIsChar(Integer isChar) {
        this.isChar = isChar;
    }
}
