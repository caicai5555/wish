package com.foundation.service.dist.biz.impl;


import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.biz.IDistArchiveDescindicatorBiz;
import com.foundation.service.dist.service.IDistArchiveDescindicatorService;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexBizImpl
 * @Description: 档案-指标字典表业务类
 * @date 2016/10/13 13:34
 */
@Service
public class DistArchiveDescindicatorBizImpl implements IDistArchiveDescindicatorBiz {
    private Log logger = LogFactory.getLog(DistArchiveDescindicatorBizImpl.class);

    @Autowired
    IDistArchiveDescindicatorService distArchiveDiscindicatorService;
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveDescindicator save(DistArchiveDescindicator distArchiveDescindicator) throws Exception {
        if(distArchiveDescindicator!=null && StringUtils.isEmpty(distArchiveDescindicator.getId())){
            distArchiveDescindicator.setId(IdGen.uuid());
        }
        try{
            distArchiveDiscindicatorService.save(distArchiveDescindicator);
        }catch(Exception e){
            logger.error("[DistArchiveDiscindicatorBizImpl.save] error:"+e);
        }
        return distArchiveDescindicator;

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DistArchiveDescindicator queryById(String id) throws Exception {
        return distArchiveDiscindicatorService.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DistArchiveDescindicator queryByCode(String code) throws Exception {
        return distArchiveDiscindicatorService.queryByCode(code);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {
        return distArchiveDiscindicatorService.deleteIndexById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(DistArchiveDescindicator distArchiveDescindicator) throws Exception {
        try {
            distArchiveDiscindicatorService.updateById(distArchiveDescindicator);
        } catch (Exception e) {
            logger.error("[DistArchiveDiscindicatorBizImpl.updateById] error:"+e);
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DistArchiveDescindicator> queryList(Map<String, Object> params) throws Exception {
        return distArchiveDiscindicatorService.queryList(params);
    }
}
