package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseIndicatorRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseIndicatorRelDao
 * @Description: 疾病-疾病与指标信息关联表-CD
 * @author wangsen
 * @date 2016/12/6 21:50
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseIndicatorRelDao extends MybatisBaseDao<String, DiseaseIndicatorRel> {
    /**
     * @Description: 根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}