package com.foundation.dao.modules.write.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateCategory;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateCategoryDao 
* @Description: 评估分类Dao 
* @author chengchen 
* @date 2016年10月12日 上午10:33:48 
*  
*/
@MyBatisRepository
public interface EvaluateCategoryDao extends MybatisBaseDao<String, EvaluateCategory> {
}
