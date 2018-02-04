package com.foundation.dao.modules.write.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRule;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorRuleDao
 * @Description: 指标体系-指标规则数据持久类->CUD
 * @author wangsen
 * @date 2016/11/21 15:38
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleDao extends MybatisBaseDao<String, IndicatorRule> {

    /**
     * @Description 根据规则组id更新规则
     * @param indicatorRule
     */
    public void updateByGroupId(IndicatorRule indicatorRule);

}