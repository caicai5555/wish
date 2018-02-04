package com.foundation.dao.modules.write.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateConclusionContentDao 
* @Description: 评估结论内容Dao 
* @author chengchen 
* @date 2016年10月12日 上午10:33:48 
*  
*/
@MyBatisRepository
public interface EvaluateConclusionContentDao extends MybatisBaseDao<String, EvaluateConclusionContent> {

}
