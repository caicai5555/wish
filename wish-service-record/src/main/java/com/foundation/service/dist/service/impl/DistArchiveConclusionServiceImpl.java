package com.foundation.service.dist.service.impl;

import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.archive.DistArchiveConclusion;
import com.foundation.dao.modules.read.archive.DistArchiveConclusionDaoR;
import com.foundation.dao.modules.write.archive.DistArchiveConclusionDao;
import com.foundation.service.common.CacheConstants;
import com.foundation.service.common.IndicatorType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.service.IDistArchiveConclusionService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexServiceImpl
 * @Description: 档案-结论项字典服务
 * @date 2016/10/13 12:57
 */
@Service
public class DistArchiveConclusionServiceImpl implements IDistArchiveConclusionService {
    private Log logger = LogFactory.getLog(DistArchiveConclusionServiceImpl.class);

    @Autowired(required=false)
    DistArchiveConclusionDao distArchiveConclusionDao;
    @Autowired(required=false)
    DistArchiveConclusionDaoR distArchiveConclusionDaoR;

    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveConclusion save(DistArchiveConclusion distArchiveConclusion) throws Exception {
        if(distArchiveConclusion == null
                || StringUtils.isEmpty(distArchiveConclusion.getId())
                || StringUtils.isEmpty(distArchiveConclusion.getCode())){
            throw new Exception("[DistArchiveConclusionServiceImpl.save]方法入参id或code为空");
        }

        distArchiveConclusionDao.save(distArchiveConclusion);

        //保存成功，放入缓存
        RedisUtils.getTemplate()
                .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + distArchiveConclusion.getCode(),
                        IndicatorType.CONCINDICATOR.getType());

        return distArchiveConclusion;
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveConclusion queryById(String id) throws Exception {
        return distArchiveConclusionDaoR.queryById(id);
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveConclusion queryByCode(String code) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        return distArchiveConclusionDaoR.queryObject(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("delFlag", Constants.DEL_FLAG_DELETE);
        return distArchiveConclusionDao.updateByMap(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(DistArchiveConclusion distArchiveConclusion) throws Exception {
        if(distArchiveConclusion== null || StringUtils.isEmpty(distArchiveConclusion.getId())){
            throw new Exception("[DistArchiveConclusionServiceImpl.updateById]方法入参为null或更新条件id为空");
        }
        distArchiveConclusionDao.update(distArchiveConclusion);
    }

    @Override
    public Integer getCount(Map<String, Object> params) throws Exception {
        return distArchiveConclusionDaoR.getCount(params);
    }
}
