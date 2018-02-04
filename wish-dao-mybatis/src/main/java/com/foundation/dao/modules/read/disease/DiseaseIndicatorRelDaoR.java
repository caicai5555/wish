package com.foundation.dao.modules.read.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.DiseaseIndicatorRel;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseIndicatorRelDaoR
 * @Description: 疾病-疾病与指标信息关联表-R
 * @author wangsen
 * @date 2016/12/6 21:50
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseIndicatorRelDaoR extends MybatisBaseDao<String, DiseaseIndicatorRel> {

}