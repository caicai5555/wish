package com.foundation.service.record.biz;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.entity.record.BaseRecordEntity;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IIndicatorRecordBiz
 * @Description: 指标项公共业务类
 * @date 2016/10/25 1:44
 */
public interface IIndicatorRecordBiz {
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
     * @Description: 获取指标记录页列表
     * @param currentPage
     * @param pageSize
     * @param params
     * @param clazz
     * @return
     * @throws Exception
     */
    public Page queryPageList(int currentPage, int pageSize, Map<String, Object> params, Class clazz) throws Exception;
}
