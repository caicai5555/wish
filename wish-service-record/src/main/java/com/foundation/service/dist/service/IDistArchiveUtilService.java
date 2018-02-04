package com.foundation.service.dist.service;

import com.foundation.service.common.IndicatorType;

/**
 * @author wangsen
 * @ClassName: IDistArchiveUtilService
 * @Description: 档案字典通用服务
 * @date 2016/10/27 20:25
 */
public interface IDistArchiveUtilService {

    /**
     * @Description: 根据指标编号获取指标类型
     * @param code 指标编号code
     * @return
     */
    public IndicatorType getIndicatorType(String code) throws Exception;
}
