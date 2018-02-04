package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: DiseaseinfoIndicatorRel
 * @Description: 疾病-疾病与指标信息关联表
 * @author wangsen
 * @date 2016/12/6 21:47
 * @version V1.0
 */
public class DiseaseIndicatorRel  implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键**/
    private String id;
    /** 疾病信息id**/
    private String diseaseId;
    /** 指标信息id**/
    private String indicatorId;

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

    public String getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }
}