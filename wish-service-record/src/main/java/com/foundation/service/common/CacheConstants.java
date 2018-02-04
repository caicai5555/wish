package com.foundation.service.common;

/**
 * @ClassName: CacheConstants
 * @Description: 档案缓存常量定义类
 * User: SamWang
 * 2016/8/4 16:23
 */
public interface CacheConstants {

    /*********************缓存key前缀***/
    /** 指标类型缓存前缀
     * key=前缀+IndicatorType
     * */
    String CACHE_ARCHIVE_INDICATOR_TYPE="archive:indicatorType:";
    /** 指标所属表缓存前缀（从孕检系统同步用）
     * 00
     * key=前缀+distArchiveIndicator表tableName字段
     * */
    String CACHE_DISTARCHIVEINDICATOR_TABLENAME = "archive:distArchiveIndicator:tableName:";
}
