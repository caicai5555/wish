package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DiseaseHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: DiseaseHistoryDao 
* @Description: 疾病史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:12:56 
*
 */
@MyBatisRepository
public interface DiseaseHistoryDao extends MybatisBaseDao<String, DiseaseHistory> {

}
