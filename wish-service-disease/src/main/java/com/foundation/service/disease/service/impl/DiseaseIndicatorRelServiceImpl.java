package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseIndicatorRel;
import com.foundation.dao.modules.read.disease.DiseaseIndicatorRelDaoR;
import com.foundation.dao.modules.write.disease.DiseaseIndicatorRelDao;
import com.foundation.service.disease.service.IDiseaseIndicatorRelService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
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
public class DiseaseIndicatorRelServiceImpl implements IDiseaseIndicatorRelService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseIndicatorRelServiceImpl.class);

    @Autowired
    private DiseaseIndicatorRelDao dao;
    @Autowired
    private DiseaseIndicatorRelDaoR daoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseIndicatorRel bean) throws Exception {
        try {
            if (StringUtils.isBlank(bean.getId())) {
                bean.setId(IdGen.uuid());
            }
            Long num = dao.save(bean);
            if (num > 0) return true;
        } catch (Exception e) {
            logger.error("[DiseaseIndicatorRelServiceImpl.save]--------> error:", e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String diseaseId, String indicatorIds) throws Exception {
        String ids = indicatorIds.replaceAll("[\\[\\] ]", "");
        String[] idArray = ids.split(",");

        //组装实体，批量保存
        List<DiseaseIndicatorRel> diseaseIndicatorRels = Lists.newArrayList();
        for (String id : idArray) {
            DiseaseIndicatorRel diseaseIndicatorRel = new DiseaseIndicatorRel();
            diseaseIndicatorRel.setDiseaseId(diseaseId);
            diseaseIndicatorRel.setIndicatorId(id);
            diseaseIndicatorRel.setId(IdGen.uuid());
            diseaseIndicatorRels.add(diseaseIndicatorRel);
        }
        batchSave(diseaseIndicatorRels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void batchSave(List<DiseaseIndicatorRel> beanList) throws Exception {
        dao.batchSave(beanList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByDiseaseId(String diseaseId) throws Exception {
        dao.deleteByDiseaseId(diseaseId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DiseaseIndicatorRel> queryList(Map<String, Object> params) throws Exception {
        return daoR.queryList(params);
    }
}
