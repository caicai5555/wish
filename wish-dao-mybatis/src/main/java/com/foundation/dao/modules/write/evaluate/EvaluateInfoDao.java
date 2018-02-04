package com.foundation.dao.modules.write.evaluate;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.evaluate.EvaluateInfo;
import com.foundation.dao.modules.MybatisBaseDao;

/** 
* @ClassName: EvaluateInfoDao 
* @Description: 评估信息Dao 
* @author chengchen 
* @date 2016年10月12日 上午10:33:48 
*  
*/
@MyBatisRepository
public interface EvaluateInfoDao extends MybatisBaseDao<String, EvaluateInfo> {
	public void updateTimes(String id);
}
