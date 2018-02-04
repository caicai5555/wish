package com.foundation.service.dist.biz.impl;


import com.foundation.common.utils.IdGen;
import com.foundation.dao.entity.archive.DistArchiveConclusion;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.biz.IDistArchiveConclusionBiz;
import com.foundation.service.dist.service.IDistArchiveConclusionService;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexBizImpl
 * @Description: 档案-指标字典表业务类
 * @date 2016/10/13 13:34
 */
@Service
public class DistArchiveConclusionBizImpl implements IDistArchiveConclusionBiz {
    private Log logger = LogFactory.getLog(DistArchiveConclusionBizImpl.class);

    @Autowired
    IDistArchiveConclusionService iDistArchiveConclusionService;
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveConclusion save(DistArchiveConclusion distArchiveConclusion) throws Exception {
        if(distArchiveConclusion!=null && StringUtils.isEmpty(distArchiveConclusion.getId())){
            distArchiveConclusion.setId(IdGen.uuid());
        }
        try{
            iDistArchiveConclusionService.save(distArchiveConclusion);
        }catch(Exception e){
            logger.error("[DistArchiveConclusionBizImpl.save] error:"+e);
        }
        return distArchiveConclusion;

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DistArchiveConclusion queryById(String id) throws Exception {
        return iDistArchiveConclusionService.queryById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DistArchiveConclusion queryByCode(String code) throws Exception {
        return iDistArchiveConclusionService.queryByCode(code);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {
        return iDistArchiveConclusionService.deleteIndexById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(DistArchiveConclusion distArchiveConclusion) throws Exception {
        try {
            iDistArchiveConclusionService.updateById(distArchiveConclusion);
        } catch (Exception e) {
            logger.error("[DistArchiveConclusionBizImpl.updateById] error:"+e);
        }

    }
}
