package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseAppraisalConclusionDaoR
 * @Description: 疾病-评估结论-CUD
 * @author wangsen
 * @date 2016/12/2 15:08
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseAppraisalConclusionDao extends MybatisBaseDao<String, DiseaseAppraisalConclusion> {

}