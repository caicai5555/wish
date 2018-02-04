package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseGenelociRel;
import com.foundation.dao.modules.MybatisBaseDao;
/**
 * @ClassName: DiseaseGenelociRelDao
 * @Description: 疾病-疾病与基因位点关系表-CD
 * @author wangsen
 * @date 2016/12/6 21:51
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseGenelociRelDao extends MybatisBaseDao<String,DiseaseGenelociRel> {
    /**
     * @Description: 根据疾病信息id删除关联数据
     * @param diseaseId
     * @throws Exception
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception;
}