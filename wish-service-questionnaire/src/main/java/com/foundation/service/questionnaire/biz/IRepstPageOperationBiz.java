package com.foundation.service.questionnaire.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstPageOperation;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstPageOperationBiz
 * @Description: js页面验证的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstPageOperationBiz {
    /**
     * @Title: save
     * @Description: 保存js页面验证
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstPageOperation 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(RepstPageOperation repstPageOperation) throws Exception;

    /**
     * @Title: save
     * @Description: 保存js页面验证
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstPageOperationList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<RepstPageOperation> repstPageOperationList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个js页面验证
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param repstPageOperation 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(RepstPageOperation repstPageOperation) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取js页面验证
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstPageOperation 返回类型
     * @Throws exception
     */
    RepstPageOperation queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取js页面验证分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstPageOperation> 返回类型
     * @Throws exception
     */
    Page<RepstPageOperation> queryPage(Map<String, Object> params, Page<RepstPageOperation> page) throws Exception;

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
     * @Description: 物理删除js页面验证
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
