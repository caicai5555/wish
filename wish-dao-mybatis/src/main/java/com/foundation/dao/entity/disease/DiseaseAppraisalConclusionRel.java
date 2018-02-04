package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: DiseaseAppraisalConclusionRel
 * @Description: 疾病-疾病与评估结论关联表
 * @author wangsen
 * @date 2016/12/6 21:42
 * @version V1.0
 */
public class DiseaseAppraisalConclusionRel implements Serializable{
    private static final long serialVersionUID = 1L;

    /**主键 **/
    private String id;
    /**疾病信息主键 **/
    private String diseaseId;
    /** 评估结论主键（disease_appraisal_conclusion）**/
    private String appraisalConclusionId;

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

    public String getAppraisalConclusionId() {
        return appraisalConclusionId;
    }

    public void setAppraisalConclusionId(String appraisalConclusionId) {
        this.appraisalConclusionId = appraisalConclusionId;
    }
}