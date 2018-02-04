package com.foundation.service.dist.biz;

import com.foundation.dao.entity.archive.DistArchiveConclusion;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexBiz
 * @Description: 档案-指标字典表业务类
 * @date 2016/10/13 13:33
 */
public interface IDistArchiveConclusionBiz {

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

}
