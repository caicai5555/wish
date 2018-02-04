package com.foundation.dao.entity.disease;

import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName: DiseaseCategory
 * @Description:  疾病-疾病分类
 * @author wangsen
 * @date 2016/12/2 15:11
 * @version V1.0
 */
public class DiseaseCategory implements Serializable{
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 父类，0为顶级**/
    private String parentId;
    /** 分类名称**/
    private String name;
    /** 排序**/
    private Integer sort;
    /** 是否有子类:0有，1没有**/
    private Integer isParent;
    /** 是否启用：1启用，0不启用**/
    private Integer activeFlag;
    /** 删除标记:1删除；0正常2**/
    private Integer delFlag;
    /** 新增时间**/
    private Date createDate;
    /** 更新时间**/
    private Date updateDate;
    /** 备注**/
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}