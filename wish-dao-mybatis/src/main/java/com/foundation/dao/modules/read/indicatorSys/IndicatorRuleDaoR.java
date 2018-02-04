package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRule;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorRuleDaoR
 * @Description: 指标体系-指标规则数据持久类->R
 * @author wangsen
 * @date 2016/11/21 15:39
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleDaoR extends MybatisBaseDao<String, IndicatorRule> {

}