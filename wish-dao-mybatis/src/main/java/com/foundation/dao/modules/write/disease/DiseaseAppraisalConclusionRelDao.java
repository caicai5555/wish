package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;
import com.foundation.dao.modules.MybatisBaseDao;
/**
 * @ClassName: DiseaseAppraisalConclusionRelDao
 * @Description: 疾病-疾病与评估结论关联表-CD
 * @author wangsen
 * @date 2016/12/6 21:52
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseAppraisalConclusionRelDao extends MybatisBaseDao<String, DiseaseAppraisalConclusionRel> {

    /**
     * @Description: 根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}