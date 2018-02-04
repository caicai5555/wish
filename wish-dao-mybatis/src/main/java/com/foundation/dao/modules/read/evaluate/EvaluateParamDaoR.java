package com.foundation.dao.modules.read.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateParamDaoR 
* @Description: 评估参数Dao（查询）
* @author chengchen 
* @date 2016年10月12日 上午10:27:05 
*  
*/
@MyBatisRepository
public interface EvaluateParamDaoR extends MybatisBaseDao<String, EvaluateParam> {

}
