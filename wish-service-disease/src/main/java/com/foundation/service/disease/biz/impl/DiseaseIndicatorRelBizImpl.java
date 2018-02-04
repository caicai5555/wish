package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.DiseaseIndicatorRel;
import com.foundation.service.disease.biz.IDiseaseIndicatorRelBiz;
import com.foundation.service.disease.service.IDiseaseIndicatorRelService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Service
public class DiseaseIndicatorRelBizImpl implements IDiseaseIndicatorRelBiz {
    private final static Logger logger = LoggerFactory.getLogger(DiseaseAppraisalConclusionRelBizmpl.class);

    @Autowired
    private IDiseaseIndicatorRelService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseIndicatorRel> queryList(Map<String, Object> params) throws Exception {
        return service.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getIndicatorIds(String diseaseId) throws Exception {
        List<String> ids = new ArrayList<>();

        Map<String,Object> params = new HashedMap();
        params.put("diseaseId",diseaseId);
        List<DiseaseIndicatorRel> list = queryList(params);

        if(null!=list&& list.size()>0){
            for(DiseaseIndicatorRel bean :list){
                ids.add(bean.getIndicatorId());
            }
        }
        return ids;
    }
}
