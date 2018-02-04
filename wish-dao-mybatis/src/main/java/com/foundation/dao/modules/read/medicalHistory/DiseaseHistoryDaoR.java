package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DiseaseHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: DiseaseHistoryDaoR 
* @Description: 疾病史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:12:56 
*
 */
@MyBatisRepository
public interface DiseaseHistoryDaoR extends MybatisBaseDao<String, DiseaseHistory> {

}
