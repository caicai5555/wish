package com.foundation.dao.entity.indicatorSys;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: IndicatorInfo
 * @Description: 指标信息实体
 * @author wangsen
 * @date 2016/11/24 11:26
 * @version V1.0
 */
public class Indicator implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键id**/
    private String id;
    /** 指标编码**/
    private String code;
    /** 类型**/
    private String type;
    /** 指标中文名**/
    private String name;
    /** 备注**/
    private String remark;

    /** 创建时间**/
    private Date createDate;
    /** 更新时间**/
    private Date updateDate;
    /** 父主键id**/
    private String parentId;
    /** 状态是否添加规则
     * 0：未添加
     * 1：已添加
     * **/
    private Integer state;
    /** 是否显示分组
     * 0：不显示
     * 1：显示
     * **/
    private Integer showClass;

    /** 删除标记
     * 0：未删除
     * 1：已删除
     * **/
    private Integer delFlag;
    /** 归档标记
     * 0：不归档
     * 1：归档
     * **/
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getShowClass() {
        return showClass;
    }

    public void setShowClass(Integer showClass) {
        this.showClass = showClass;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getArchiveFlag() {
        return archiveFlag;
    }

    public void setArchiveFlag(Integer archiveFlag) {
        this.archiveFlag = archiveFlag;
    }
}