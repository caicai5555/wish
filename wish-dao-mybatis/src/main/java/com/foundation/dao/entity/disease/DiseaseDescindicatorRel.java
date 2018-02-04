package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: DiseaseinfoDescindicatorRel
 * @Description: 疾病-描述性指标
 * @author wangsen
 * @date 2016/12/6 21:42
 * @version V1.0
 */
public class DiseaseDescindicatorRel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**主键 **/
    private String id;
    /**疾病信息主键 **/
    private String diseaseId;
    /**描述性指标主键**/
    private String descindicatorId;

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

    public String getDescindicatorId() {
        return descindicatorId;
    }

    public void setDescindicatorId(String descindicatorId) {
        this.descindicatorId = descindicatorId;
    }
}