package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @ClassName: DiseaseAppraisalConclusionDaoR
 * @Description: 疾病-评估结论（后期描述性指标）-R
 * @author wangsen
 * @date 2016/12/2 15:08
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseAppraisalConclusionDaoR extends MybatisBaseDao<String, DiseaseAppraisalConclusion> {

    /**
     * @Descrption:根据指标信息Id查询评估结论（后期描述性指标）
     * @param deseaseId
     * @return
     * @throws Exception
     */
    public List<DiseaseAppraisalConclusion> queryByDeseaseId(String deseaseId) throws Exception;
}