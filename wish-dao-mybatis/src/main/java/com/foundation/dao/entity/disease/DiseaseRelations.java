package com.foundation.dao.entity.disease;

import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import com.foundation.dao.entity.indicatorSys.Indicator;

import java.util.List;

/**
 * @ClassName: DiseaseInfo
 * @Description: 疾病-疾病信息与指标、描述性指标、症状、基因组合实体
 * @author wangsen
 * @date 2016/12/2 14:50
 */
public class DiseaseRelations extends Disease {


    /** 关联指标列表**/
    private List<Indicator> indicators;
    /** 关联描述性指标列表**/
    private List<DistArchiveDescindicator> descIndicators;
    /** 关联症状列表**/
    private List<Symptom> symptoms;
    /** 关联基因列表**/
    private List<GeneLoci> geneLocis;

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }

    public List<DistArchiveDescindicator> getDescIndicators() {
        return descIndicators;
    }

    public void setDescIndicators(List<DistArchiveDescindicator> descIndicators) {
        this.descIndicators = descIndicators;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public List<GeneLoci> getGeneLocis() {
        return geneLocis;
    }

    public void setGeneLocis(List<GeneLoci> geneLocis) {
        this.geneLocis = geneLocis;
    }
}