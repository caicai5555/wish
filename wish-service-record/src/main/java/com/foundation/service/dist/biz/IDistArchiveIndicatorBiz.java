package com.foundation.service.dist.biz;

import com.foundation.dao.entity.archive.DistArchiveIndicator;

import java.util.List;

/**
 * @author wangsen
 * @ClassName: IDistArchiveIndexBiz
 * @Description: 档案-指标字典表业务类
 * @date 2016/10/13 13:33
 */
public interface IDistArchiveIndicatorBiz {

    /**
     * @Description: 保存指标字典
     * @param distArchiveIndicator
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator save(DistArchiveIndicator distArchiveIndicator) throws Exception;

    /**
     * @Description: 批量保存指标字典
     * @param distArchiveIndexList
     * @throws Exception
     */
/*    public void batchSave(List<DistArchiveIndex> distArchiveIndexList) throws Exception;*/

    /**
     * @Description: 查询实体
     * @return
     * @throws Exception
     */
/*    public List<DistArchiveIndex> query() throws Exception;*/

    /**
     * @Description: 根据Id查询实体
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator queryById(String id) throws Exception;

    /**
     * @Description: 根据code,性别 查询实体
     * @param code
     * @return
     * @throws Exception
     */
    @Deprecated
    public DistArchiveIndicator queryByCode(String code) throws Exception;

    /**
     * @Description: 根据code,性别 查询实体(表中存在code相同,性别不同情况,故添加性别限制)
     * @param code
     * @param sex 0:男, 1:女
     * @return
     * @throws Exception
     */
    public DistArchiveIndicator queryByCodeAndSex(String code, Integer sex) throws Exception;
    /**
     * @Description: 根据表名查询实体
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> queryByTableName(String tableName) throws Exception;

    /**
     * @Description: 根据父id查询实体列表
     * @param parentId
     * @return
     * @throws Exception
     */
/*    public List<DistArchiveIndex> queryByParentId(String parentId) throws  Exception;*/


    /**
     * @Description: 根据父id删除实体
     * @param id
     * @return
     * @throws Exception
     */
    public Integer deleteById(String id) throws Exception;

    /**
     * @Description: 根据指标code,性别删除实体(表中存在code相同,性别不同情况,故添加性别限制)
     * @param code
     * @param sex 0:男,1:女
     * @return
     * @throws Exception
     */
    public Integer deleteByCodeAndSex(String code, Integer sex) throws  Exception;

    /**
     * @Description: 根据指标code更新实体
     * @param distArchiveIndicator
     * @return
     * @throws Exception
     */
    public Integer updateByCode(DistArchiveIndicator distArchiveIndicator) throws Exception;

    /**
     * @Description: 查询所有实体列表
     * @return
     * @throws Exception
     */
    public List<DistArchiveIndicator> queryAll() throws Exception;
}
