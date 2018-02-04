package com.foundation.dao.modules.read.medicalHistory;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.medicalHistory.DrugHistoryDetail;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * 
* @ClassName: DrugHistoryDetailDaoR 
* @Description: 用药史Dao（查询）
* @author chengchen 
* @date 2016年11月22日 上午11:32:17 
*
 */
@MyBatisRepository
public interface DrugHistoryDetailDaoR extends MybatisBaseDao<String, DrugHistoryDetail> {

}
