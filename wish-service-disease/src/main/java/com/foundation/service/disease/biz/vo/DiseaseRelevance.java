package com.foundation.service.disease.biz.vo;

import com.foundation.dao.entity.disease.Disease;

/**
 * @ClassName: DiseaseVO
 * @Description: 疾病信息及关联主键数据封装实体
 * @author wangsen
 * @date 2016/12/8 2:13
 */
public class DiseaseRelevance extends Disease {

    /** 指标主键组**/
    private String indicatorIds;
    /** 评估结论(描述性指标)主键组**/
    private String descIndicatorIds;
    /** 症状选择主键组**/
    private String symptomSelectedIds;
    /** 基因选择主键组**/
    private String geneLociSelectedIds;

    public String getIndicatorIds() {
        return indicatorIds;
    }

    public void setIndicatorIds(String indicatorIds) {
        this.indicatorIds = indicatorIds;
    }

    public String getDescIndicatorIds() {
        return descIndicatorIds;
    }

    public void setDescIndicatorIds(String descIndicatorIds) {
        this.descIndicatorIds = descIndicatorIds;
    }

    public String getSymptomSelectedIds() {
        return symptomSelectedIds;
    }

    public void setSymptomSelectedIds(String symptomSelectedIds) {
        this.symptomSelectedIds = symptomSelectedIds;
    }

    public String getGeneLociSelectedIds() {
        return geneLociSelectedIds;
    }

    public void setGeneLociSelectedIds(String geneLociSelectedIds) {
        this.geneLociSelectedIds = geneLociSelectedIds;
    }
}
