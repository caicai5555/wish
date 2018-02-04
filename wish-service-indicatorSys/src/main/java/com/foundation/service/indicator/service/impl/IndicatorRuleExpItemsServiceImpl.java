package com.foundation.service.indicator.service.impl;

import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItems;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleExpItemsDO;
import com.foundation.dao.modules.read.indicatorSys.IndicatorRuleExpItemsDaoR;
import com.foundation.dao.modules.write.indicatorSys.IndicatorRuleExpItemsDao;
import com.foundation.service.indicator.service.IIndicatorRuleExpItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: IndicatorRuleExpItemsServiceImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/22 10:32
 * @version V1.0
 */
@Service
public class IndicatorRuleExpItemsServiceImpl implements IIndicatorRuleExpItemsService{
    @Autowired
    private IndicatorRuleExpItemsDao indicatorRuleExpItemsDao;
    @Autowired
    private IndicatorRuleExpItemsDaoR indicatorRuleExpItemsDaoR;

    @Override
    public void saveIndRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) throws Exception {
        indicatorRuleExpItemsDao.batchSave(ruleExpItems);
    }

    @Override
    public void updateIndRuleExpItems(List<IndicatorRuleExpItems> ruleExpItems) throws Exception {
        indicatorRuleExpItemsDao.batchUpdate(ruleExpItems);
    }

    @Override
    public void deleteByIndruleId(String indruleId) throws Exception {
        IndicatorRuleExpItems bean = new IndicatorRuleExpItems();
        bean.setDelFlag(Constants.DEL_FLAG_DELETE);
        bean.setIndruleId(indruleId);
        indicatorRuleExpItemsDao.update(bean);

    }

    @Override
    public void deleteByRuleGroupId(String ruleGroupId) throws Exception {
        indicatorRuleExpItemsDao.delByRuleGroupId(ruleGroupId);
    }

    @Override
    public List<IndicatorRuleExpItemsDO> getRuleExpItemsRuleId(String indRuleId) throws Exception {
        return indicatorRuleExpItemsDaoR.queryRuleExpItemsAndRuleItemInfo(indRuleId);
    }
}
