package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: FamilyHistoryDao
* @Description: 家族病史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:32:48 
*
 */
@MyBatisRepository
public interface FamilyHistoryDao extends MybatisBaseDao<String, FamilyHistory> {

}
