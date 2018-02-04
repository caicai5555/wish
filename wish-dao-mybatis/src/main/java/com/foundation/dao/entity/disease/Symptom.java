package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: Symptom
 * @Description:  疾病-症状信息
 * @author wangsen
 * @date 2016/12/6 18:56
 * @version V1.0
 */
public class Symptom implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 名称**/
    private String name;
    /** 现象**/
    private String phenomenon;
    /** 强度**/
    private String intensity;
    /** 描述信息**/
    private String remark;

    /**********附加字段，症状category**/
    private String categoryId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon == null ? null : phenomenon.trim();
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity == null ? null : intensity.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}