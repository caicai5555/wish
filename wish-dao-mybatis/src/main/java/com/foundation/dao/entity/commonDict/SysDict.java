package com.foundation.dao.entity.commonDict;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.util.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 系统公共字典Entity
 *
 * @author fqh
 * @version 2013-05-15
 */
public class SysDict extends BaseEntity<SysDict> {

    private static final long serialVersionUID = 1L;

    /**
     * 字典标签值
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 描述
     */
    private String description;
    /**
     * 分组
     */
    private String type;
    /**
     * 分组
     */
    private String childType;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 父Id
     */
    private String parentId;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 删除标记（0：正常；1：删除；2：审核）
     */
    private Integer delFlag;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新日期
     */
    private Date updateDate;


    public SysDict() {
        super();
    }

    public SysDict(String id) {
        super(id);
    }

    public SysDict(String type, String value) {
        this.value = value;
        this.type = type;
    }

    public SysDict(String type, String value, String description) {
        this(type, value);
        this.description = description;
    }

    public SysDict(String type, String value, String label, String description) {
        this(type, value);
        this.label = label;
        this.description = description;
    }

    @Length(min = 1, max = 100)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Length(min = 1, max = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min = 0, max = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Length(min = 1, max = 100)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "SysDict{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", childType='" + childType + '\'' +
                ", sort=" + sort +
                ", parentId='" + parentId + '\'' +
                ", remarks='" + remarks + '\'' +
                ", delFlag=" + delFlag +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                "} " + super.toString();
    }


    @Override
    public void preInsert() {
        this.id= IdGen.uuid();
    }

    @Override
    public void preUpdate() {

    }

}