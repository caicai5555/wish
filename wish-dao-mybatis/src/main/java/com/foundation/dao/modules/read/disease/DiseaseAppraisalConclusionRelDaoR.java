package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseAppraisalConclusionRelDaoR
 * @Description: 疾病-疾病与评估结论关联表-R
 * @author wangsen
 * @date 2016/12/6 21:52
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseAppraisalConclusionRelDaoR extends MybatisBaseDao<String, DiseaseAppraisalConclusionRel> {

}