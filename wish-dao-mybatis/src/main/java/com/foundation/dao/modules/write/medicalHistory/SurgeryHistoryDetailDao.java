package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: SurgeryHistoryDetailDao 
* @Description: 手术史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:33:51 
*
 */
@MyBatisRepository
public interface SurgeryHistoryDetailDao extends MybatisBaseDao<String, SurgeryHistoryDetail> {

}
