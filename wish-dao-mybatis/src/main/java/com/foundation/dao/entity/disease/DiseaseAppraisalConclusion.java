package com.foundation.dao.entity.disease;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DiseaseAppraisalConclusion
 * @Description:  疾病-疾病评估结论
 * @author wangsen
 * @date 2016/12/2 14:37
 * @version V1.0
 */
public class DiseaseAppraisalConclusion implements Serializable{
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 指标编码**/
    private String code;
    /** 指标分类**/
    private String type;
    /** 标签标题**/
    private String name;
    /** 标签描述**/
    private String remark;
    /** 创建时间**/
    private Date createDate;
    /** 更新时间**/
    private Date updateDate;
    /** 是否删除0是未删除1是删除**/
    private Integer delFlag;
    /** 父id**/
    private String parentId;
    /** 是否归档**/
    private Integer archiveFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(Integer archiveFlag) {
        this.archiveFlag = archiveFlag;
    }
}