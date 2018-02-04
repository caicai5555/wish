package com.foundation.service.dist.service.impl;

import com.foundation.cache.utils.RedisUtils;
import com.foundation.service.common.CacheConstants;
import com.foundation.service.common.IndicatorType;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.dist.service.IDistArchiveUtilService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: DistArchiveUtilServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2016/10/27 20:28
 */
@Service
public class DistArchiveUtilServiceImpl implements IDistArchiveUtilService {
    private Log logger = LogFactory.getLog(DistArchiveIndicatorServiceImpl.class);

    @Autowired(required=false)
    DistArchiveIndicatorServiceImpl distIndicatorService;
    @Autowired(required=false)
    DistArchiveDescindicatorServiceImpl distDescindicatorService;
    @Autowired(required=false)
    DistArchiveConclusionServiceImpl distConclusionServiceImpl;
    /**
     * {@inheritDoc}
    */
    @Override
    public IndicatorType getIndicatorType(String code) throws Exception {
        //定义查询参数Map
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);

        //先从缓存中获取，有则直接返回，无查询数据库
        String type = RedisUtils.getTemplate().get(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE+code);
        if(StringUtils.isNotEmpty(type)){
            logger.error("[DistArchiveUtilServiceImpl.getIndicatorType]:::根据指标code="+code+"从缓存中命中类型为："+IndicatorType.getByType(type));
            return IndicatorType.getByType(type);
        }

        //根据code查询是否在基本指标字典表中
        Integer indicatorCount =distIndicatorService.getCount(params);
        if(indicatorCount>0){
            //保存成功，放入缓存
            RedisUtils.getTemplate()
                    .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + code,
                            IndicatorType.INDICATOR.getType());
            logger.error("[DistArchiveUtilServiceImpl.getIndicatorType]:::根据指标code="+code+"获取指标类型为："+IndicatorType.INDICATOR);
            return IndicatorType.INDICATOR;
        }
        //根据code查询是否在描述性指标字典表中
        Integer bescindicatorCount =distDescindicatorService.getCount(params);
        if(bescindicatorCount>0){
            //保存成功，放入缓存
            RedisUtils.getTemplate()
                    .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + code,
                            IndicatorType.DESCINDICATOR.getType());

            logger.error("[DistArchiveUtilServiceImpl.getIndicatorType]:::根据指标code="+code+"获取指标类型为："+IndicatorType.DESCINDICATOR);
            return IndicatorType.DESCINDICATOR;
        }
        //根据code查询是否在结论性性指标字典表中
        Integer conclusionCount =distConclusionServiceImpl.getCount(params);
        if(conclusionCount>0){
            //保存成功，放入缓存
            RedisUtils.getTemplate()
                    .set(CacheConstants.CACHE_ARCHIVE_INDICATOR_TYPE + code,
                            IndicatorType.CONCINDICATOR.getType());

            logger.error("[DistArchiveUtilServiceImpl.getIndicatorType]:::根据指标code="+code+"获取指标类型为："+IndicatorType.CONCINDICATOR);
            return IndicatorType.CONCINDICATOR;
        }

        return null;
    }
}
