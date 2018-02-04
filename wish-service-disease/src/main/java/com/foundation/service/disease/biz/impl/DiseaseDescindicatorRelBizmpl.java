package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;
import com.foundation.service.disease.biz.IDiseaseDescindicatorRelBiz;
import com.foundation.service.disease.service.IDiseaseDescindicatorRelService;
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
public class DiseaseDescindicatorRelBizmpl implements IDiseaseDescindicatorRelBiz {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseDescindicatorRelBizmpl.class);

    @Autowired
    private IDiseaseDescindicatorRelService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseDescindicatorRel> queryList(Map<String, Object> params) throws Exception {
        return service.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getDescIndicatorIds(String diseaseId) throws Exception {
        List<String> ids = new ArrayList<>();

        Map<String,Object> params = new HashedMap();
        params.put("diseaseId",diseaseId);
        List<DiseaseDescindicatorRel> list = queryList(params);

        if(null!=list&& list.size()>0){
            for(DiseaseDescindicatorRel bean :list){
                ids.add(bean.getDescindicatorId());
            }
        }
        return ids;
    }
}
