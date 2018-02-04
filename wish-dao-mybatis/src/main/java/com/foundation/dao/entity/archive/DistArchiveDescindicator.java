package com.foundation.dao.entity.archive;


import com.foundation.dao.util.DataEntity;

import java.util.Date;

/**
 * @Title: DistArchiveConclusion
 * @Description: 档案-描述性指标项实体类
 * @author SamWang(wangsammu@gmail.com)
 * @date 2016/10/14 16:07 
 */
 
public class DistArchiveDescindicator extends DataEntity<DistArchiveDescindicator> {
    /**
     *
     */
    private String id;
    /**
     *结论编码
     */
    private String code;
    /**
     *结论分类
     */
    private String type;
    /**
     *标签标题
     */
    private String name;
    /**
     *标签描述
     */
    private String remark;
    /**
     *创建时间
     */
    private Date createDate;
    /**
     *更新时间
     */
    private Date updateDate;
    /**
     *父节点id
     */
    private String parentId;

    private Integer archiveFlag;

    /**
     *删除标志，0是未删除1是删除
     */
    //private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(Integer archiveFlag) {
        this.archiveFlag = archiveFlag;
    }
}