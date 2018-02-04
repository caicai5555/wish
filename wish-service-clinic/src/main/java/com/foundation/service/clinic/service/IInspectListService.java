package com.foundation.service.clinic.service;

import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectList;

/**
 * @ClassName: IInspectListService
 * @Description: 检验单的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
public interface IInspectListService {
    /**
     * @Title: save
     * @Description: 保存检验单
     * @Author cuiyaohua
     * @Date 2016-10-17 17:14
     * @Param inspectList 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(InspectList inspectList) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个检验单
     * @Author cuiyaohua
     * @Date 2016-10-17 17:16
     * @Param inspectList 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(InspectList inspectList) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取检验单
     * @Author cuiyaohua
     * @Date 2016-10-17 17:17
     * @Param id 参数
     * @Return clinicRecord 返回类型
     * @Throws exception
     */
    InspectList queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取检验单分页信息
     * @Author cuiyaohua
     * @Date 2016-10-17 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<InspectList> 返回类型
     * @Throws exception
     */
    Page<InspectList> queryPage(Map<String, Object> params, Page<InspectList> page) throws Exception;

    /**
     * @Title: getCount
     * @Description: 获取符合条件的记录数
     * @Author cuiyaohua
     * @Date 2016-10-17 17:21
     * @Param params 查询条件
     * @Return int 返回类型
     * @Throws exception
     */
    int getCount(Map<String, Object> params) throws Exception;

    /**
     * @Title: deleteReal
     * @Description: 物理删除检验单
     * @Author cuiyaohua
     * @Date 2016-10-17 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;

}
