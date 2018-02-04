package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.Symptom;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: SymptomDao
 * @Description: 疾病-症状信息-CUD
 * @author wangsen
 * @date 2016/12/6 19:02
 * @version V1.0
 */
@MyBatisRepository
public interface SymptomDao extends MybatisBaseDao<String, Symptom> {

}