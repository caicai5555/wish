package com.foundation.dao.modules.read.indicatorSys;

import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleItems;
import com.foundation.dao.modules.MybatisBaseDao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleItemsDaoR
 * @Description: 指标体系-指标规则字典表数据持久层->R操作
 * @author wangsen
 * @date 2016/11/21 16:36
 * @version V1.0
 */
@MyBatisRepository
public interface IndicatorRuleItemsDaoR extends MybatisBaseDao<String,IndicatorRuleItems> {

    /**
     * @Description 根据指标规则项codes查询列表
     * @param itemCodes
     * @return
     * @throws Exception
     */
    public List<IndicatorRuleItems> queryByItemCodes(String[] itemCodes) throws Exception;

    /**
     * @Description 根据分组查询实体列表
     * @param itemGroup
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> queryByItemGroup(String itemGroup) throws Exception;
}