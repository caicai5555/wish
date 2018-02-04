package com.foundation.service.questionnaire.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionsCtgrBiz
 * @Description: 问卷试题分类的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionsCtgrBiz {
    /**
     * @Title: save
     * @Description: 保存问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionsCtgr 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception;

    /**
     * @Title: save
     * @Description: 保存问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionsCtgrList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<RepstQuestionsCtgr> repstQuestionsCtgrList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param repstQuestionsCtgr 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(RepstQuestionsCtgr repstQuestionsCtgr) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstQuestionsCtgr 返回类型
     * @Throws exception
     */
    RepstQuestionsCtgr queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取问卷试题分类分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionsCtgr> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionsCtgr> queryPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception;

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
     * @Description: 物理删除问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取详细的问卷试题分类
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstQuestionsCtgr 返回类型
     * @Throws exception
     */
    RepstQuestionsCtgr queryDetailById(String id) throws Exception;

    /**
     * @Title: queryDetailPage
     * @Description: 获取问卷试题分类分页详细信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionsCtgr> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionsCtgr> queryDetailPage(Map<String, Object> params, Page<RepstQuestionsCtgr> page) throws Exception;
}
