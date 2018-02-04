package com.foundation.service.questionnaire.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstUserQuestionBiz
 * @Description: 用户答题的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstUserQuestionBiz {
    /**
     * @Title: save
     * @Description: 保存用户答题
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstUserQuestion 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(RepstUserQuestion repstUserQuestion) throws Exception;

    /**
     * @Title: save
     * @Description: 保存用户答题
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstUserQuestionList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<RepstUserQuestion> repstUserQuestionList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个用户答题
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param repstUserQuestion 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(RepstUserQuestion repstUserQuestion) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取用户答题
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstUserQuestion 返回类型
     * @Throws exception
     */
    RepstUserQuestion queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取用户答题分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstUserQuestion> 返回类型
     * @Throws exception
     */
    Page<RepstUserQuestion> queryPage(Map<String, Object> params, Page<RepstUserQuestion> page) throws Exception;

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
     * @Description: 物理删除用户答题
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
