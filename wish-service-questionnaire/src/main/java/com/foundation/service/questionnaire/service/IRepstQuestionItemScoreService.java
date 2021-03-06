package com.foundation.service.questionnaire.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionItemScore;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionItemScoreService
 * @Description: 问题项计算复杂分数的服务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionItemScoreService {
    /**
     * @Title: save
     * @Description: 保存问题项计算复杂分数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionItemScore 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(RepstQuestionItemScore repstQuestionItemScore) throws Exception;

    /**
     * @Title: save
     * @Description: 批量保存问题项计算复杂分数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:14
     * @Param repstQuestionItemScoreList 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(List<RepstQuestionItemScore> repstQuestionItemScoreList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个问题项计算复杂分数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:16
     * @Param repstQuestionItemScore 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(RepstQuestionItemScore repstQuestionItemScore) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取问题项计算复杂分数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:17
     * @Param id 参数
     * @Return repstQuestionItemScore 返回类型
     * @Throws exception
     */
    RepstQuestionItemScore queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取问题项计算复杂分数分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionItemScore> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionItemScore> queryPage(Map<String, Object> params, Page<RepstQuestionItemScore> page) throws Exception;

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
     * @Description: 物理删除问题项计算复杂分数
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
