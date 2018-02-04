package com.foundation.dao.modules.read.clinic;

import com.foundation.common.persistence.Page;
import com.foundation.common.persistence.annotation.MyBatisRepository;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.dao.modules.MybatisBaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface InspectListDaoR extends MybatisBaseDao<String, InspectList> {
    /**
     * @Title: queryList
     * @Description: 根据参数获取列表数据
     * @author cuiyaohua
     * @date 2016年10月12日 上午10:11:38
     * @param  params
     * @param page    设定参数
     * @return List<T>    实体列表
     * @throws
     */
    List<InspectList> queryPageList(@Param("map")Map<String, Object> params, @Param("page")Page<InspectList> page);
}