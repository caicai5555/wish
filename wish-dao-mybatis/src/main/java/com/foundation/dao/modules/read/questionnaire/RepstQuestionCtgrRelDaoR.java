package com.foundation.dao.modules.read.questionnaire;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestionCtgrRel;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface RepstQuestionCtgrRelDaoR extends MybatisBaseDao<String, RepstQuestionCtgrRel> {
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
    List<RepstQuestionCtgrRel> queryPageList(@Param("map") Map<String, Object> params, @Param("page") Page<RepstQuestionCtgrRel> page);
}