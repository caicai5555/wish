package com.foundation.service.record.service;

import com.foundation.common.persistence.Page;
import com.foundation.mongo.entity.record.BaseRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IBaseRecordService
 * @Description: 指标记录基础服务
 * @date 2016/10/18 17:04
 */
public interface IBaseRecordService<T extends BaseRecordEntity> {

    /**
     * @Description: 保存指标记录
     * @param recordEntity
     * @return
     * @throws Exception
     */
    public boolean saveRecord(T recordEntity) throws Exception;


    /**
     * @Description: 获取指标记录列表
     * @param params
     * @param clazz
     * @return
     * @throws Exception
     */
    public List<T> queryList(Map<String, Object> params, Class<T> clazz) throws Exception;

    /**
     * @Description: 分页获取记录列表
     * @param currentPage
     * @param pageSize
     * @param params
     * @param clazz 返回实体的类类型
     * @return
     * @throws Exception
     */
    public Page<T> queryPageList(int currentPage, int pageSize, Map<String, Object> params, Class clazz) throws Exception;
}
