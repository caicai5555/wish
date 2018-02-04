package com.foundation.service.disease.service.impl;

import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.disease.DiseaseDescindicatorRel;
import com.foundation.dao.modules.read.disease.DiseaseDescindicatorRelDaoR;
import com.foundation.dao.modules.write.disease.DiseaseDescindicatorRelDao;
import com.foundation.service.disease.service.IDiseaseDescindicatorRelService;
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
public class DiseaseDescindicatorRelServiceImpl implements IDiseaseDescindicatorRelService {

    private final static Logger logger = LoggerFactory.getLogger(DiseaseDescindicatorRelServiceImpl.class);

    @Autowired
    private DiseaseDescindicatorRelDao dao;
    @Autowired
    private DiseaseDescindicatorRelDaoR daoR;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(DiseaseDescindicatorRel bean) throws Exception {
        try {
            if (StringUtils.isBlank(bean.getId())) {
                bean.setId(IdGen.uuid());
            }
            Long num = dao.save(bean);
            if (num > 0) return true;
        } catch (Exception e) {
            logger.error("[DiseaseDescindicatorRelServiceImpl.save]--------> error:", e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String diseaseId, String descindicatorIds) throws Exception {
        String ids = descindicatorIds.replaceAll("[\\[\\] ]", "");
        String[] idsArray = ids.split(",");

        //组装实体，批量保存
        List<DiseaseDescindicatorRel> diseaseIndicatorRels = Lists.newArrayList();
        for (String id : idsArray) {
            DiseaseDescindicatorRel diseaseDescindicatorRel = new DiseaseDescindicatorRel();
            diseaseDescindicatorRel.setDiseaseId(diseaseId);
            diseaseDescindicatorRel.setDescindicatorId(id);
            diseaseDescindicatorRel.setId(IdGen.uuid());
            diseaseIndicatorRels.add(diseaseDescindicatorRel);
        }
        batchSave(diseaseIndicatorRels);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void batchSave(List<DiseaseDescindicatorRel> beanList) throws Exception {
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
    public List<DiseaseDescindicatorRel> queryList(Map<String, Object> params) throws Exception {
        return daoR.queryList(params);
    }
}
