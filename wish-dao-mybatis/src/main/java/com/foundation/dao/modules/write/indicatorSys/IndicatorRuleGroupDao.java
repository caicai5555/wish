package com.foundation.dao.modules.write.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorRuleGroupDao
 * @Description: 指标体系-指标规则组表数据持久层->CUD操作
 * @author wangsen
 * @date 2016/11/21 18:03
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleGroupDao extends MybatisBaseDao<String, IndicatorRuleGroup> {

}