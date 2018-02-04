package com.foundation.dao.modules.write.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.dao.modules.MybatisBaseDao;

/**
 * @ClassName: IndicatorRuleItemsDao
 * @Description: 指标体系-指标规则字典表数据持久层->R操作
 * @author wangsen
 * @date 2016/11/21 16:37
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleItemsDao extends MybatisBaseDao<String, IndicatorRuleItems> {
}