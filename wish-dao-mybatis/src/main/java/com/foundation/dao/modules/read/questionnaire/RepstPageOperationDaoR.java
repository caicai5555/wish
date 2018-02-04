package com.foundation.dao.modules.read.questionnaire;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstPageOperation;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface RepstPageOperationDaoR extends MybatisBaseDao<String, RepstPageOperation> {
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
    List<RepstPageOperation> queryPageList(@Param("map") Map<String, Object> params, @Param("page") Page<RepstPageOperation> page);
}