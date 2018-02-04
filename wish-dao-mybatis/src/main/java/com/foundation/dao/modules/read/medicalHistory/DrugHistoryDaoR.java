package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DrugHistory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
 * 
* @ClassName: DrugHistoryDaoR 
* @Description: 用药史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:13:56 
*
 */
@MyBatisRepository
public interface DrugHistoryDaoR extends MybatisBaseDao<String, DrugHistory> {

}
