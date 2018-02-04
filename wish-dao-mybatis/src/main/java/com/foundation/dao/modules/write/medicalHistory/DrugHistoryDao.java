package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: DrugHistoryDao
* @Description: 用药史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:13:56 
*
 */
@MyBatisRepository
public interface DrugHistoryDao extends MybatisBaseDao<String, DrugHistory> {

}
