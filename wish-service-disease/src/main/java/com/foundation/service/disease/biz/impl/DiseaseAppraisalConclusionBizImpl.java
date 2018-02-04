package com.foundation.service.disease.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.disease.DiseaseAppraisalConclusion;
import com.foundation.service.disease.biz.IDiseaseAppraisalConclusionBiz;
import com.foundation.service.disease.service.IDiseaseAppraisalConclusionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Service
public class DiseaseAppraisalConclusionBizImpl implements IDiseaseAppraisalConclusionBiz {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseAppraisalConclusionBizImpl.class);

    @Autowired
    private IDiseaseAppraisalConclusionService dacService;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception {
        return dacService.save(diseaseAppraisalConclusion);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DiseaseAppraisalConclusion diseaseAppraisalConclusion) throws Exception {
        dacService.update(diseaseAppraisalConclusion);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DiseaseAppraisalConclusion queryById(String id) throws Exception {
        return dacService.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseAppraisalConclusion> queryList(Map<String, Object> params) throws Exception {
        return dacService.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<DiseaseAppraisalConclusion> queryPage(Map<String, Object> params, Page<DiseaseAppraisalConclusion> pageBounds) throws Exception {
        return dacService.queryPage(params,pageBounds);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseAppraisalConclusion> queryByDeseaseId(String diseaseId) throws Exception {
        return dacService.queryByDeseaseId(diseaseId);
    }
}
