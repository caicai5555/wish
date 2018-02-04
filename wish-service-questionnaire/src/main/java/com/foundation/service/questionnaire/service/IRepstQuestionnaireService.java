package com.foundation.service.questionnaire.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaire;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionnaireService
 * @Description: 问卷的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionnaireService {
    /**
     * @Title: save
     * @Description: 保存问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionnaire 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(RepstQuestionnaire repstQuestionnaire) throws Exception;

    /**
     * @Title: save
     * @Description: 批量保存问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionnaireList 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(List<RepstQuestionnaire> repstQuestionnaireList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:16
     * @Param repstQuestionnaire 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(RepstQuestionnaire repstQuestionnaire) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return repstQuestionnaire 返回类型
     * @Throws exception
     */
    RepstQuestionnaire queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取问卷分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionnaire> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionnaire> queryPage(Map<String, Object> params, Page<RepstQuestionnaire> page) throws Exception;

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
     * @Description: 物理删除问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;


    /**
     * @Title: queryDetailById
     * @Description: 通过id获取问卷
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return repstQuestionnaire 返回类型
     * @Throws exception
     */
    RepstQuestionnaire queryDetailById(String id) throws Exception;
}
