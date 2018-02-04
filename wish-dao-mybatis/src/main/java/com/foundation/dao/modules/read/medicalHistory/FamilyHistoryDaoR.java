package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.FamilyHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: FamilyHistoryDaoR 
* @Description: 家族病史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:32:48 
*
 */
@MyBatisRepository
public interface FamilyHistoryDaoR extends MybatisBaseDao<String, FamilyHistory> {

}
