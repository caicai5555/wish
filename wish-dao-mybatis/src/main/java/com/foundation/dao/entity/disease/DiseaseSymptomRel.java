package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: DiseaseinfoSymptomRel
 * @Description: 疾病-疾病与症状关联表
 * @author wangsen
 * @date 2016/12/6 21:46
 * @version V1.0
 */
public class DiseaseSymptomRel  implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键**/
    private String id;
    /** 疾病id**/
    private String diseaseId;
    /** 症状id**/
    private String symptomId;
    /** 症状分类ID（目前从枚举中取，后期从字典中获取）**/
    private String categoryId;
    /** 描述信息**/
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(String symptomId) {
        this.symptomId = symptomId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}