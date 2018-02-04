package com.foundation.service.indicator.service.impl;

import com.foundation.common.bean.Constants;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroup;
import com.foundation.dao.entity.indicatorSys.IndicatorRuleGroupDO;
import com.foundation.dao.modules.read.indicatorSys.IndicatorRuleGroupDaoR;
import com.foundation.dao.modules.write.indicatorSys.IndicatorRuleGroupDao;
import com.foundation.service.indicator.service.IIndicatorRuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndicatorRuleGroupServiceImpl
 * @Description: @TODO
 * @author wangsen
 * @date 2016/11/21 19:08
 * @version V1.0
 */
@Service
public class IndicatorRuleGroupServiceImpl implements IIndicatorRuleGroupService {
    @Autowired
    IndicatorRuleGroupDaoR indicatorRuleGroupDaoR;
    @Autowired
    IndicatorRuleGroupDao indicatorRuleGroupDao;

    @Override
    public IndicatorRuleGroup getIndRuleGroupByParams(String indicatorId, String itemCodes) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("indicatorId",indicatorId);
        params.put("itemCodes",itemCodes);
        params.put("delFlag", Constants.DEL_FLAG_NORMAL);


        return indicatorRuleGroupDaoR.queryObject(params);
    }

    @Override
    public List<IndicatorRuleGroup> getIndRuleGroupByIndId(String indicatorId) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("indicatorId",indicatorId);
        params.put("delFlag", Constants.DEL_FLAG_NORMAL);
        return indicatorRuleGroupDaoR.queryList(params);
    }

    @Override
    public void saveIndicatorRuleGroup(IndicatorRuleGroup indicatorRuleGroup) throws Exception {
        if(StringUtils.isEmpty(indicatorRuleGroup.getId())){
            indicatorRuleGroup.setId(IdGen.uuid());
        }
        indicatorRuleGroupDao.save(indicatorRuleGroup);
    }

    @Override
    public Integer delIndRuleGroupById(String id) throws Exception {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        params.put("delFlag",Constants.DEL_FLAG_DELETE);
        return indicatorRuleGroupDao.updateByMap(params);
    }

    @Override
    public List<IndicatorRuleGroupDO> getIndGroupAndRules(Map<String,Object> params ) throws Exception {
        return indicatorRuleGroupDaoR.queryRuleGroupAndRules(params);
    }

}
