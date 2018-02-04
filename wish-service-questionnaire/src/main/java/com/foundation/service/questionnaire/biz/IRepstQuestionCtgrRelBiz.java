package com.foundation.service.questionnaire.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.questionnaire.RepstQuestionCtgrRel;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: IRepstQuestionCtgrRelBiz
 * @Description: 试题与试题分类关系的业务层
 * @Author cuiyaohua
 * @Date 2016/11/23
 *
 */
public interface IRepstQuestionCtgrRelBiz {
    /**
     * @Title: save
     * @Description: 保存试题与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionCtgrRel 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception;

    /**
     * @Title: save
     * @Description: 保存试题与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:26
     * @Param repstQuestionCtgrRelList 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean save(List<RepstQuestionCtgrRel> repstQuestionCtgrRelList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个试题与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:27
     * @Param repstQuestionCtgrRel 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean update(RepstQuestionCtgrRel repstQuestionCtgrRel) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取试题与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:28
     * @Param id 参数
     * @Return repstQuestionCtgrRel 返回类型
     * @Throws exception
     */
    RepstQuestionCtgrRel queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取试题与试题分类关系分页信息
     * @Author cuiyaohua
     * @Date 2016-11-23 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<RepstQuestionCtgrRel> 返回类型
     * @Throws exception
     */
    Page<RepstQuestionCtgrRel> queryPage(Map<String, Object> params, Page<RepstQuestionCtgrRel> page) throws Exception;

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
     * @Description: 物理删除试题与试题分类关系
     * @Author cuiyaohua
     * @Date 2016-11-23 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;
}
