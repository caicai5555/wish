package com.foundation.service.questionnaire.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireRecord;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionnaireRecordService
 * @Description: 问卷记录的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionnaireRecordService {
    /**
     * @Title: save
     * @Description: 保存问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionnaire 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception;

    /**
     * @Title: save
     * @Description: 批量保存问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionnaireRecordList 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(List<RepstQuestionnaireRecord> repstQuestionnaireRecordList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:16
     * @Param repstQuestionnaireRecord 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(RepstQuestionnaireRecord repstQuestionnaireRecord) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return repstQuestionnaireRecord 返回类型
     * @Throws exception
     */
    RepstQuestionnaireRecord queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取问卷记录分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionnaireRecord> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionnaireRecord> queryPage(Map<String, Object> params, Page<RepstQuestionnaireRecord> page) throws Exception;

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
     * @Description: 物理删除问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;


    /**
     * @Title: queryDetailById
     * @Description: 通过id获取问卷记录
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return repstQuestionnaireRecord 返回类型
     * @Throws exception
     */
    RepstQuestionnaireRecord queryDetailById(String id) throws Exception;
}
