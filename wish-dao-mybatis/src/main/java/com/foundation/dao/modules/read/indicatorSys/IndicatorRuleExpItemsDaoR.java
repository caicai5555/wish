package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItems;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;

/**
 * @ClassName: IndicatorRuleExpItemsDaoR
 * @Description: 指标体系-指标规则表达式子项数据持久类->R
 * @author wangsen
 * @date 2016/11/22 10:17
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleExpItemsDaoR extends MybatisBaseDao<String, IndicatorRuleExpItems> {

    /**
     * @Description:级联查询规则表达式项及其关联字典信息
     * @return
     * @throws Exception
     */
   public List<IndicatorRuleExpItemsDO> queryRuleExpItemsAndRuleItemInfo(String indRuleId)throws Exception;
}