package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseSymptomRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseinfoSymptomRelDao
 * @Description: 疾病-疾病与症状关联表-CD
 * @author wangsen
 * @date 2016/12/6 21:49
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseSymptomRelDao extends MybatisBaseDao<String, DiseaseSymptomRel> {
    /**
     * @Description: 根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}