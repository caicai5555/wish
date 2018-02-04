package com.foundation.dao.modules.write.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItems;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorRuleExpItemsDao
 * @Description: 指标体系-指标规则表达式子项数据持久类->CUD
 * @author wangsen
 * @date 2016/11/22 10:16
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleExpItemsDao extends MybatisBaseDao<String, IndicatorRuleExpItems> {

    public Integer delByRuleGroupId(String ruleGroupId) throws Exception;
}