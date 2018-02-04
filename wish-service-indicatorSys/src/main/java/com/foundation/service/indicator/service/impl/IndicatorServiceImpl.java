package com.foundation.service.indicator.service.impl;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.indicatorSys.Indicator;
import com.foundation.dao.entity.indicatorSys.IndicatorTreeDO;
import com.foundation.dao.modules.read.indicatorSys.IndicatorDaoR;
import com.foundation.dao.modules.write.indicatorSys.IndicatorDao;
import com.foundation.service.indicator.service.IIndicatorService;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Service
public class IndicatorServiceImpl implements IIndicatorService {
    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IndicatorDao indicatorDao;
    @Autowired
    private IndicatorDaoR indicatorDaoR;
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Indicator indicator) throws Exception {
        try{
            if (StringUtils.isBlank(indicator.getId())) {
                indicator.setId(IdGen.uuid());
            }
            Long num = indicatorDao.save(indicator);
            if(1 == num){
                return true;
            }
        }catch(Exception e){
            logger.error("[IndicatorServiceImpl.save] error:", e);
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Indicator indicator) throws Exception {
        try{
            indicatorDao.update(indicator);
        }catch(Exception e){
            logger.error("[IndicatorServiceImpl.update] error.", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Indicator queryById(String id) throws Exception {
        Indicator indicator = null;
        try{
            indicator = indicatorDaoR.queryById(id);
        }catch(Exception e){
            logger.error("[IndicatorServiceImpl.queryById] error.", e);
        }
        return indicator;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Indicator> queryPage(Map<String, Object> params, Page<Indicator> pageBounds) throws Exception {
        try {
            Page<Indicator> pagination = pageBounds;
            List<Indicator> list = indicatorDaoR.queryPage(params, pageBounds);
            pagination.setList(list);
            return pagination;
        } catch (Exception e) {
            logger.error("[IndicatorServiceImpl.queryPage] error.", e);
        }
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Indicator> queryCategoryPage(Map<String, Object> params, Page<Indicator> pageBounds) throws Exception {
        Page<Indicator> pagination = pageBounds;
        List<Indicator> list = indicatorDaoR.queryCategoryPage(params, pageBounds);
        pagination.setList(list);
        return pagination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicator> queryList(Map<String, Object> params) throws Exception {
        return indicatorDaoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount(Map<String, Object> params) throws Exception {
        return indicatorDaoR.getCount(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<IndicatorTreeDO> queryTree(Map<String, Object> params) throws Exception {
        return indicatorDaoR.queryTree(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCountByParams(Map<String, Object> params) throws Exception {
        return indicatorDaoR.getCountByParams(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicator> queryChilds() throws Exception {
        Map<String,Object> params = Maps.newHashMap();
        params.put("childs",1);
        return indicatorDaoR.queryList(params);
    }

    @Override
    public List<Indicator> queryByDiseaseId(String diseaseId) throws Exception {
        return indicatorDaoR.queryByDiseaseId(diseaseId);
    }
}
