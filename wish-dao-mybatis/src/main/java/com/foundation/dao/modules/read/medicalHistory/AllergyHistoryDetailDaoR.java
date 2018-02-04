package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.AllergyHistoryDetail;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: AllergyHistoryDetailDaoR 
* @Description: 过敏史明细Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:12:05 
*
 */
@MyBatisRepository
public interface AllergyHistoryDetailDaoR extends MybatisBaseDao<String, AllergyHistoryDetail> {

}
