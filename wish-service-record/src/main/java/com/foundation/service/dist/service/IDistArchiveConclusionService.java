package com.foundation.service.dist.service;

import com.foundation.dao.entity.archive.DistArchiveConclusion;

import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexService
 * @Description: 档案-结论项字典服务
 * @date 2016/10/13 12:56
 */
public interface IDistArchiveConclusionService {

    /**
     * @Description: 保存结论项实体
     * @param distArchiveConclusion
     * @return
     * @throws Exception
     */
    public DistArchiveConclusion save(DistArchiveConclusion distArchiveConclusion) throws Exception;

    /**
     * @Description: 根据主键查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveConclusion queryById(String id) throws Exception;

    /**
     * @Description: 根据编码查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveConclusion queryByCode(String code) throws  Exception;

    /**
     * @Description: 根据id删除实体（逻辑删除）
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteIndexById(String id) throws Exception;

    /**
     * @Description: 根据主键更新实体
     * @param distArchiveConclusion
     * @return
     * @throws Exception
     */
    public  void updateById(DistArchiveConclusion distArchiveConclusion)throws Exception;
    /**
     * @Description: 动态参数查询数量
     * @param params
     * @return
     * @throws Exception
     */
    public Integer getCount(Map<String, Object> params)throws Exception;
}
