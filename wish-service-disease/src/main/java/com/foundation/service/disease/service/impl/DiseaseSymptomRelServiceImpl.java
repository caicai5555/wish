package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseSymptomRel;
import com.foundation.dao.modules.write.disease.DiseaseSymptomRelDao;
import com.foundation.service.disease.service.IDiseaseSymptomRelService;
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
public class DiseaseSymptomRelServiceImpl implements IDiseaseSymptomRelService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseSymptomRelServiceImpl.class);

    @Autowired
    private DiseaseSymptomRelDao dao;

    /**
     * {@inheritDoc}
     */
    public boolean save(DiseaseSymptomRel bean) throws Exception {
        try {
            if (StringUtils.isBlank(bean.getId())) {
                bean.setId(IdGen.uuid());
            }
            Long num = dao.save(bean);
            if (num > 0) return true;
        } catch (Exception e) {
            logger.error("[DiseaseSymptomRelServiceImpl.save]--------> error:", e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String diseaseId, String symptomIds) throws Exception {
        //组装实体，批量保存
        String[] idsArray = symptomIds.split(",");
        List<DiseaseSymptomRel> diseaseSymptomRels = Lists.newArrayList();
        for (String id : idsArray) {
            DiseaseSymptomRel diseaseSymptomRel = new DiseaseSymptomRel();
            diseaseSymptomRel.setDiseaseId(diseaseId);
            //再次分隔前端拼接的CategoryId#SymptomId
            String[] array = id.split("#");
            diseaseSymptomRel.setCategoryId(array[0]);
            diseaseSymptomRel.setSymptomId(array[1]);
            diseaseSymptomRel.setId(IdGen.uuid());
            diseaseSymptomRels.add(diseaseSymptomRel);

        }
        batchSave(diseaseSymptomRels);
    }

    /**
     * {@inheritDoc}
     */
    public void batchSave(List<DiseaseSymptomRel> beanList) throws Exception {
        dao.batchSave(beanList);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteByDiseaseId(String diseaseId) throws Exception {
        dao.deleteByDiseaseId(diseaseId);
    }
}
