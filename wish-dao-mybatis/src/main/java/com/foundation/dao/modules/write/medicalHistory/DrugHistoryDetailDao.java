package com.foundation.dao.modules.write.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * 
* @ClassName: DrugHistoryDetailDao
* @Description: 用药史Dao
* @author chengchen 
* @date 2016年11月22日 上午11:32:17 
*
 */
@MyBatisRepository
public interface DrugHistoryDetailDao extends MybatisBaseDao<String, DrugHistoryDetail> {

}
