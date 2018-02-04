package com.foundation.dao.modules.read.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateConclusionContentDaoR 
* @Description: 评估结论内容Dao（查询）
* @author chengchen 
* @date 2016年10月12日 上午10:27:05 
*  
*/
@MyBatisRepository
public interface EvaluateConclusionContentDaoR extends MybatisBaseDao<String, EvaluateConclusionContent> {

}
