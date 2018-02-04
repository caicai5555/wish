package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: AllergyHistoryDao
* @Description: 过敏史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:11:20 
*
 */
@MyBatisRepository
public interface AllergyHistoryDao extends MybatisBaseDao<String, AllergyHistory> {

}
