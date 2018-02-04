package com.foundation.service.record.biz.impl;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.entity.record.BaseRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foundation.service.record.biz.IIndicatorRecordBiz;
import com.foundation.service.record.service.impl.IndicatorRecordService;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IndicatorRecordBizImpl
 * @Description: 指标记录公用业务类
 * @date 2016/10/25 10:06
 */
@Service
public class IndicatorRecordBizImpl implements IIndicatorRecordBiz {

    @Autowired
    IndicatorRecordService indicatorRecordService;
    @Override
    public boolean saveIndicator(BaseRecordEntity recordEntity, boolean archiveFlag) throws Exception {
        return indicatorRecordService.saveIndicator(recordEntity,archiveFlag);
    }

    @Override
    public Page queryPageList(int currentPage, int pageSize, Map<String, Object> params, Class clazz) throws Exception {
        return indicatorRecordService.queryPageList(currentPage,pageSize,params,clazz);
    }

}
