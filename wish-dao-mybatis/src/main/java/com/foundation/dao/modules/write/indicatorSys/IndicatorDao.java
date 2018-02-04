package com.foundation.dao.modules.write.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorDao
 * @Description: 指标体系-指标信息持久持久类->CUD
 * @author wangsen
 * @date 2016/11/24 11:33
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorDao extends MybatisBaseDao<String, Indicator> {

}