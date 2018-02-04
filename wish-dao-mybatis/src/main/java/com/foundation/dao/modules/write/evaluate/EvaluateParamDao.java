package com.foundation.dao.modules.write.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateParam;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateParamDao 
* @Description: 评估参数Dao 
* @author chengchen 
* @date 2016年10月12日 上午10:33:48 
*  
*/
@MyBatisRepository
public interface EvaluateParamDao extends MybatisBaseDao<String, EvaluateParam> {

}
