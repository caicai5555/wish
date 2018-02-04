package com.foundation.service.indicator.service.impl;

import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.indicatorSys.IndicatorRule;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleIntervalDO;
import com.foundation.dao.modules.read.indicatorSys.IndicatorRuleDaoR;
import com.foundation.dao.modules.write.indicatorSys.IndicatorRuleDao;
import com.foundation.service.indicator.service.IIndicatorRuleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleServiceImpl
 * @Description: 指标规则服务实现类
 * @author wangsen
 * @date 2016/11/21 11:38
 * @version V1.0
 */
@Service
public class IndicatorRuleServiceImpl implements IIndicatorRuleService {

    private Log logger = LogFactory.getLog(IndicatorRuleServiceImpl.class);

    @Autowired
    private IndicatorRuleDao indicatorRuleDao;
    @Autowired
    private IndicatorRuleDaoR indicatorRuleDaoR;
    


    @Override
    public void saveIndicatorRule(IndicatorRule indicatorRule) throws Exception {
        indicatorRuleDao.save(indicatorRule);
    }


    @Override
    public void updateIndicatorRule(IndicatorRule indicatorRule) throws Exception {
        indicatorRuleDao.update(indicatorRule);
    }
    //ok
    @Override
    public List<IndicatorRule> getIndRulesByIndId(String indicatorId) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("indicatorId",indicatorId);
        params.put("delFlag", Constants.DEL_FLAG_NORMAL);
        return indicatorRuleDaoR.queryList(params);
    }

    @Override
    public IndicatorRule getIndRuleByIndId(String indicatorId) throws Exception {
        List<IndicatorRule> indicatorRules = this.getIndRulesByIndId(indicatorId);
        if (indicatorRules != null && indicatorRules.size() > 0) {
            return indicatorRules.get(0);
        }
        return null;
    }

    @Override
    public void delIndRuleById(String id) throws Exception {
        IndicatorRule indicatorRule = new IndicatorRule();
        indicatorRule.setId(id);
        indicatorRule.setDelFlag(Constants.DEL_FLAG_DELETE);
        indicatorRuleDao.update(indicatorRule);
    }

    @Override
    public void delIndRulesByGroupId(String groupId) throws Exception {
        IndicatorRule indicatorRule = new IndicatorRule();
        indicatorRule.setGroupId(groupId);
        indicatorRule.setDelFlag(Constants.DEL_FLAG_DELETE);
        indicatorRuleDao.updateByGroupId(indicatorRule);

    }

    @Override
    public IndicatorRule getIndRuleById(String id) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        return indicatorRuleDaoR.queryObject(params);
    }

    @Override
    public List<IndicatorRuleIntervalDO> getIndRuleInterval(List<String> ruleIds, Map<String, String> innerItem) throws Exception {
        return null;
    }
}



