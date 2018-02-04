package com.foundation.dao.modules.read.subsystem;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.questionnaire.RepstQuestions;
import com.foundation.dao.entity.subsystem.Subsystem;
import com.foundation.dao.entity.sys.User;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface SubsystemDaoR extends MybatisBaseDao<String, Subsystem> {
    /**
     * @Title: queryList
     * @Description: 根据参数获取列表数据
     * @author cuiyaohua
     * @date 2016/12/12
     * @param  params
     * @param page    设定参数
     * @return List<T>    实体列表
     * @throws
     */
    List<Subsystem> queryPageList(@Param("map") Map<String, Object> params, @Param("page") Page<Subsystem> page);

}