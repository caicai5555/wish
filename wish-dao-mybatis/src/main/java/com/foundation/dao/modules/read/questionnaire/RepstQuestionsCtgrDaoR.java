package com.foundation.dao.modules.read.questionnaire;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestionsCtgr;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface RepstQuestionsCtgrDaoR extends MybatisBaseDao<String, RepstQuestionsCtgr> {
    /**
     * @Title: queryList
     * @Description: 根据参数获取列表数据
     * @author cuiyaohua
     * @date 2016/11/23
     * @param  params
     * @param page    设定参数
     * @return List<T>    实体列表
     * @throws
     */
    List<RepstQuestionsCtgr> queryPageList(@Param("map") Map<String, Object> params, @Param("page") Page<RepstQuestionsCtgr> page);

    /**
     * @Title: queryDetailById
     * @Description: 根据参数获取详细数据
     * @author cuiyaohua
     * @date 2016/11/23
     * @param id    设定参数
     * @return RepstQuestionsCtgr
     * @throws
     */
    RepstQuestionsCtgr queryDetailById(String id);

    /**
     * @Title: queryDetailPage
     * @Description: 根据参数获取列表数据
     * @author cuiyaohua
     * @date 2016/11/23
     * @param  params
     * @param page    设定参数
     * @return List<T>    实体列表
     * @throws
     */
    List<RepstQuestionsCtgr> queryDetailPage(@Param("map") Map<String, Object> params, @Param("page") Page<RepstQuestionsCtgr> page);
}