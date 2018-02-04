package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: DiseaseinfoGenelociRel
 * @Description: 疾病-疾病与基因位点关系表
 * @author wangsen
 * @date 2016/12/6 21:44
 * @version V1.0
 */
public class DiseaseGenelociRel  implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键**/
    private String id;
    /** 疾病信息主键**/
    private String diseaseId;
    /** 基因位点**/
    private String geneLociId;
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

    public String getGeneLociId() {
        return geneLociId;
    }

    public void setGeneLociId(String geneLociId) {
        this.geneLociId = geneLociId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}