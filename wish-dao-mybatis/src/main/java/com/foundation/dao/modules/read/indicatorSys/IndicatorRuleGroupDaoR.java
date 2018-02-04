package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleGroupDaoR
 * @Description: 指标体系-指标规则组表数据持久层->R操作
 * @author wangsen
 * @date 2016/11/21 18:03
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleGroupDaoR extends MybatisBaseDao<String,IndicatorRuleGroup> {


    /**
     * @Description: 查询规则组及组下规则集合
     * @param params
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleGroupDO> queryRuleGroupAndRules(Map<String,Object> params)throws Exception;
}