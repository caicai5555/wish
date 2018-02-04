package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: AllergyHistoryDetailDao
* @Description: 过敏史明细Dao
* @author chengchen 
* @date 2016年11月22日 上午11:12:05 
*
 */
@MyBatisRepository
public interface AllergyHistoryDetailDao extends MybatisBaseDao<String, AllergyHistoryDetail> {
	
}
