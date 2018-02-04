package com.foundation.service.questionnaire.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireCtgrRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionnaireCtgrRelBiz
 * @Description: 问卷与试题分类关系的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionnaireCtgrRelBiz {
    /**
     * @Title: save
     * @Description: 保存问卷与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionnaireCtgrRel 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception;

    /**
     * @Title: save
     * @Description: 保存问卷与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionnaireCtgrRelList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<RepstQuestionnaireCtgrRel> repstQuestionnaireCtgrRelList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个问卷与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param repstQuestionnaireCtgrRel 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(RepstQuestionnaireCtgrRel repstQuestionnaireCtgrRel) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取问卷与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstQuestionnaireCtgrRel 返回类型
     * @Throws exception
     */
    RepstQuestionnaireCtgrRel queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取问卷与试题分类关系分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionnaireCtgrRel> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionnaireCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionnaireCtgrRel> page) throws Exception;

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
     * @Description: 物理删除问卷与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
