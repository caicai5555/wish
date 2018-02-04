package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: SurgeryHistoryDao
* @Description: 手术史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:33:24 
*
 */
@MyBatisRepository
public interface SurgeryHistoryDao extends MybatisBaseDao<String, SurgeryHistory> {

}
