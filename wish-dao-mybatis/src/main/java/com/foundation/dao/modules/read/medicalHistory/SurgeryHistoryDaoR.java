package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: SurgeryHistoryDaoR 
* @Description: 手术史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:33:24 
*
 */
@MyBatisRepository
public interface SurgeryHistoryDaoR extends MybatisBaseDao<String, SurgeryHistory> {

}
