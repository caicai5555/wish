package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseDescindicatorRelDaoR
 * @Description: 疾病-描述性关联表-R
 * @author wangsen
 * @date 2016/12/20 16:20
 */
@MyBatisRepository
public interface DiseaseDescindicatorRelDaoR extends MybatisBaseDao<String, DiseaseDescindicatorRel> {

}