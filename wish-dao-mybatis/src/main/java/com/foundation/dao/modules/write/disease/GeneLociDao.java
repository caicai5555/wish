package com.foundation.dao.modules.write.disease;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.disease.GeneLoci;
import com.foundation.dao.modules.MybatisBaseDao;
/**
 * @ClassName: GeneLociDao
 * @Description: 疾病-基因位点-CUD
 * @author wangsen
 * @date 2016/12/6 19:03
 * @version V1.0
 */
@MyBatisRepository
public interface GeneLociDao extends MybatisBaseDao<String, GeneLoci> {

}