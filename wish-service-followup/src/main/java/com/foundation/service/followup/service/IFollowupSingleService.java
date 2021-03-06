package com.foundation.service.followup.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.followup.FollowupSingle;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IFollowupSingleService
 * @Description: 单次随访的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IFollowupSingleService {
    /**
     * @Title: save
     * @Description: 保存单次随访
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param followupSingle 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(FollowupSingle followupSingle) throws Exception;

    /**
     * @Title: save
     * @Description: 批量保存单次随访
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param followupSingleList 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(List<FollowupSingle> followupSingleList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个单次随访
     * @Author cuiyaohua
     * @Date 2016-11-23 17:16
     * @Param followupSingle 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(FollowupSingle followupSingle) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取单次随访
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return followupSingle 返回类型
     * @Throws exception
     */
    FollowupSingle queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取单次随访分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<FollowupSingle> 返回类型
     * @Throws exception
     */
    Page<FollowupSingle> queryPage(Map<String, Object> params, Page<FollowupSingle> page) throws Exception;

    /**
     * @Title: getCount
     * @Description: 获取符合条件的记录数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:21
     * @Param params 查询条件
     * @Return int 返回类型
     * @Throws exception
     */
    int getCount(Map<String, Object> params) throws Exception;

    /**
     * @Title: deleteReal
     * @Description: 物理删除单次随访
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
