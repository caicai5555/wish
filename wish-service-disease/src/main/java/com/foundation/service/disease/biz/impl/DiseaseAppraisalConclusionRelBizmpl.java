package com.foundation.service.disease.biz.impl;

import com.foundation.dao.entity.disease.DiseaseAppraisalConclusionRel;
import com.foundation.service.disease.biz.IDiseaseAppraisalConclusionRelBiz;
import com.foundation.service.disease.service.IDiseaseAppraisalConclusionRelService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DiseaseAppraisalConclusionRelBizmpl
 * @Description:疾病-疾病与评估结论关联业务
 * @author wangsen
 * @date 2016/12/1 11:35
 * @version V1.0
 */
@Service
public class DiseaseAppraisalConclusionRelBizmpl implements IDiseaseAppraisalConclusionRelBiz {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseAppraisalConclusionRelBizmpl.class);

    @Autowired
    private IDiseaseAppraisalConclusionRelService service;

    @Override
    public List<DiseaseAppraisalConclusionRel> queryList(Map<String, Object> params) throws Exception {
        return service.queryList(params);
    }

    @Override
    public List<String> getConclusionIds(String diseaseId) throws Exception {
        List<String> ids = new ArrayList<>();

        Map<String,Object> params = new HashedMap();
        params.put("diseaseId",diseaseId);
        List<DiseaseAppraisalConclusionRel> list = queryList(params);

        if(null!=list&& list.size()>0){
            for(DiseaseAppraisalConclusionRel bean :list){
                ids.add(bean.getAppraisalConclusionId());
            }
        }
        return ids;
    }
}
