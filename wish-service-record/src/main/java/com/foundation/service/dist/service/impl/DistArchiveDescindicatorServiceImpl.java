package com.foundation.service.dist.service.impl;

import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.bean.Constants;
import com.foundation.dao.entity.archive.DistArchiveDescindicator;
import com.foundation.dao.modules.read.archive.DistArchiveDescindicatorDaoR;
import com.foundation.dao.modules.write.archive.DistArchiveDescindicatorDao;
import com.foundation.service.common.CacheConstants;
import com.foundation.service.common.IndicatorType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.service.IDistArchiveDescindicatorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveIndexServiceImpl
 * @Description: 档案-描述性指标项字典服务
 * @date 2016/10/13 12:57
 */
@Service
public class DistArchiveDescindicatorServiceImpl implements IDistArchiveDescindicatorService {
    private Log logger = LogFactory.getLog(DistArchiveDescindicatorServiceImpl.class);

    @Autowired
    DistArchiveDescindicatorDao distArchiveDescindicatorDao;
    @Autowired
    DistArchiveDescindicatorDaoR distArchiveDescindicatorDaoR;

    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveDescindicator save(DistArchiveDescindicator distArchiveDescindicator) throws Exception {
        if(distArchiveDescindicator == null
                || StringUtils.isEmpty(distArchiveDescindicator.getId())
                || StringUtils.isEmpty(distArchiveDescindicator.getCode())){
            throw new Exception("[DistArchiveIndexServiceImpl.save]方法入参id或code为空");
        }

        distArchiveDescindicatorDao.save(distArchiveDescindicator);

        //保存成功，放入缓存
        RedisUtils.getTemplate()
                .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + distArchiveDescindicator.getCode(),
                        IndicatorType.DESCINDICATOR.getType());

        return distArchiveDescindicator;
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveDescindicator queryById(String id) throws Exception {
        return distArchiveDescindicatorDaoR.queryById(id);
    }
    /**
     * {@inheritDoc}
    */
    @Override
    public DistArchiveDescindicator queryByCode(String code) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        return distArchiveDescindicatorDaoR.queryObject(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer deleteIndexById(String id) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        params.put("delFlag", Constants.DEL_FLAG_DELETE);
        return distArchiveDescindicatorDao.updateByMap(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateById(DistArchiveDescindicator distArchiveDescindicator) throws Exception {
        if(distArchiveDescindicator== null || StringUtils.isEmpty(distArchiveDescindicator.getId())){
            throw new Exception("[DistArchiveDiscindicatorServiceImpl.updateById]方法入参为null或更新条件id为空");
        }
        distArchiveDescindicatorDao.update(distArchiveDescindicator);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount(Map<String, Object> params) throws Exception {
        return distArchiveDescindicatorDaoR.getCount(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DistArchiveDescindicator> queryList(Map<String, Object> params) throws Exception {
        return distArchiveDescindicatorDaoR.queryList(params);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DistArchiveDescindicator> queryByDiseaseId(String diseaseId) throws Exception {
        return distArchiveDescindicatorDaoR.queryByDiseaseId(diseaseId);
    }
}
