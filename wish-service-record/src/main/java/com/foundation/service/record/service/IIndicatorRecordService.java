package com.foundation.service.record.service;


import com.foundation.mongo.entity.record.BaseRecordEntity;
import com.foundation.mongo.entity.record.FamilyConcIndicator;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IIndicatorRecordService
 * @Description: 指标记录服务
 * @date 2016/10/18 17:04
 */
public interface IIndicatorRecordService /*extends BaseRecordServiceImpl */{

    /**
     * @Description: 保存指标记录
     * @param recordEntity
     * @param archiveFlag 归档标识,true：归档对应数据库状态1；
     *                    false:不归档对应数据库状态0；
     * @return
     * @throws Exception
     */
    public boolean saveIndicator(BaseRecordEntity recordEntity, boolean archiveFlag) throws Exception;

    /**
     * @Description: 保家庭指标记录存指标 记录，归档到家庭档案
     * @param recordEntity
     * @param archiveFlag 归档标识,true：归档对应数据库状态1；
     *                    false:不归档对应数据库状态0；
     * @return
     * @throws Exception
     */
    public boolean saveFamilyIndicator(FamilyConcIndicator recordEntity, boolean archiveFlag) throws Exception;

    /**
     * @Description: 保存指标记录
     * @param recordEntitys
     * @param archiveFlag 归档标识,true：归档对应数据库状态1；
     *                    false:不归档对应数据库状态0；
     * @return
     * @throws Exception
     */
    public boolean saveIndicators(Map<String, BaseRecordEntity> recordEntitys, boolean archiveFlag) throws Exception;


}
