package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseGenelociRel;
import com.foundation.dao.modules.write.disease.DiseaseGenelociRelDao;
import com.foundation.service.disease.service.IDiseaseGenelociRelService;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DiseaseGenelociRelServiceImpl implements IDiseaseGenelociRelService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseGenelociRelServiceImpl.class);

    @Autowired
    private DiseaseGenelociRelDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseGenelociRel bean) throws Exception {
        try {
            if (StringUtils.isBlank(bean.getId())) {
                bean.setId(IdGen.uuid());
            }
            Long num = dao.save(bean);
            if (num > 0) return true;
        } catch (Exception e) {
            logger.error("[DiseaseGenelociRelServiceImpl.save]--------> error:", e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String diseaseId, String genelociIds) throws Exception {
        //组装实体，批量保存
        String[] idsArray = genelociIds.split(",");
        List<DiseaseGenelociRel> diseaseGenelociRels = Lists.newArrayList();
        for (String id : idsArray) {
            DiseaseGenelociRel diseaseGenelociRel = new DiseaseGenelociRel();
            diseaseGenelociRel.setDiseaseId(diseaseId);
            diseaseGenelociRel.setGeneLociId(id);
            diseaseGenelociRel.setId(IdGen.uuid());
            diseaseGenelociRels.add(diseaseGenelociRel);
        }
        batchSave(diseaseGenelociRels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void batchSave(List<DiseaseGenelociRel> beanList) throws Exception {
        dao.batchSave(beanList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByDiseaseId(String diseaseId) throws Exception {
        dao.deleteByDiseaseId(diseaseId);
    }
}
