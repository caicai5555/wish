package com.foundation.dao.modules.write.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateRule;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateRuleDao 
* @Description: 评估规则Dao 
* @author chengchen 
* @date 2016年10月12日 上午10:33:48 
*  
*/
@MyBatisRepository
public interface EvaluateRuleDao extends MybatisBaseDao<String, EvaluateRule> {

}
