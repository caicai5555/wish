package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.Symptom;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @ClassName: SymptomDaoR
 * @Description: 疾病-症状信息-R
 * @author wangsen
 * @date 2016/12/6 19:02
 * @version V1.0
 */
@MyBatisRepository
public interface SymptomDaoR extends MybatisBaseDao<String, Symptom> {
    /**
     * @Descrption:根据指标信息Id查询关联实体
     * @param diseaseId
     * @return
     * @throws Exception
     */
    public List<Symptom> queryByDiseaseId(String diseaseId) throws Exception;

}