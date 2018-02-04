package com.foundation.service.dist.service;

import com.foundation.dao.entity.archive.DistArchiveIndicator;

import java.util.List;
import java.util.Map;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexService
 * @Description: 档案-指标项字典服务
 * @date 2016/10/13 12:56
 */
public interface IDistArchiveIndicatorService {

    /**
     * @Description: 保存指标字典
     * @param distArchiveIndicator
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator save(DistArchiveIndicator distArchiveIndicator) throws Exception;

    /**
     * @Description: 批量保存指标字典
     * @param distArchiveIndicatorList
     * @throws Exception
     */
    public void batchSave(List<DistArchiveIndicator> distArchiveIndicatorList) throws Exception;

    /**
     * @Description: 查询实体
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> query() throws Exception;

    /**
     * @Description: 根据Id查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator queryById(String id) throws Exception;


    /**
     * @Description: 根据表名查询实体
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> queryByTableName(String tableName) throws Exception;

    /**
     * @Description: 根据code查询实体
     * @param code
     * @return
     * @throws Exception
     */
    @Deprecated
    public DistArchiveIndicator queryByCode(String code) throws Exception;

    /**
     * @Description: 根据动态入残查询实体
     * @param params
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator queryObject(Map<String, Object> params);

    /**
     * @Description: 根据父id查询实体列表
     * @param parentId
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> queryByParentId(String parentId) throws  Exception;

    /**
     * @Description: 根据id删除实体（逻辑删除）
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteById(String id) throws Exception;

    /**
     * @Description: 根据code及性别删除实体（逻辑删除）
     * @param code
     * @param sex
     * @throws Exception
     */
    public void deleteByCodeAndSex(String code, Integer sex) throws Exception;

    /**
     * @Description: 根据code更新实体
     * @param distArchiveIndicator
     * @return
     * @throws Exception
     */
    public  Integer updateByCode(DistArchiveIndicator distArchiveIndicator)throws Exception;

    /**
     * @Description: 查询所有实体列表
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> queryAll()throws Exception;

    /**
     * @Description: 动态参数查询数量
     * @param params
     * @return
     * @throws Exception
     */
    public Integer getCount(Map<String, Object> params)throws Exception;

}
