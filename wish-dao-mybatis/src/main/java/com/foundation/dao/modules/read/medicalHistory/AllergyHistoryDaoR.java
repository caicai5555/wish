package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.AllergyHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: AllergyHistoryDaoR 
* @Description: 过敏史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:11:20 
*
 */
@MyBatisRepository
public interface AllergyHistoryDaoR extends MybatisBaseDao<String, AllergyHistory> {

}
