package com.foundation.dao.modules.read.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateCategoryDaoR 
* @Description: 评估分类Dao（查询）
* @author chengchen 
* @date 2016年10月12日 上午10:27:05 
*  
*/
@MyBatisRepository
public interface EvaluateCategoryDaoR extends MybatisBaseDao<String, EvaluateCategory> {

}
