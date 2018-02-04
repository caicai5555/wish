package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.Disease;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: DiseaseDao
 * @Description: 疾病-疾病信息-CUD
 * @author wangsen
 * @date 2016/12/2 15:17
 * @version V1.0
 */
@MyBatisRepository
public interface DiseaseDao extends MybatisBaseDao<String, Disease> {
  
}