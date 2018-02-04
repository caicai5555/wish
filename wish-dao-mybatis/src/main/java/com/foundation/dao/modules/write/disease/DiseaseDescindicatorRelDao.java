package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseDescindicatorRelDao
 * @Description: 疾病-描述性关联表-CD
 * @author wangsen
 * @date 2016/12/20 14:31
 */
@MyBatisRepository
public interface DiseaseDescindicatorRelDao extends MybatisBaseDao<String, DiseaseDescindicatorRel> {

    /**
     * @Description: 根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}