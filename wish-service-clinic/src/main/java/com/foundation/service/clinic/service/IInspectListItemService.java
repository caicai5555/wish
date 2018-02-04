package com.foundation.service.clinic.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListItem;

import java.util.Map;

/**
 * @ClassName: IInspectListItemService
 * @Description: 检验单与检验项关系的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
public interface IInspectListItemService {
    /**
     * @Title: save
     * @Description: 保存检验单与检验项关系
     * @Author cuiyaohua
     * @Date 2016-10-17 17:14
     * @Param inspectListItem 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(InspectListItem inspectListItem) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个检验单与检验项关系
     * @Author cuiyaohua
     * @Date 2016-10-17 17:16
     * @Param inspectListItem 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(InspectListItem inspectListItem) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取检验单与检验项关系
     * @Author cuiyaohua
     * @Date 2016-10-17 17:17
     * @Param id 参数
     * @Return clinicRecord 返回类型
     * @Throws exception
     */
    InspectListItem queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取检验单与检验项关系分页信息
     * @Author cuiyaohua
     * @Date 2016-10-17 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<InspectListItem> 返回类型
     * @Throws exception
     */
    Page<InspectListItem> queryPage(Map<String, Object> params, Page<InspectListItem> page) throws Exception;

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
     * @Description: 物理删除检验单与检验项关系
     * @Author cuiyaohua
     * @Date 2016-10-17 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;

    /**
     * @Title: updateByMap
     * @Description: 有条件更新
     * @Author cuiyaohua
     * @Date 2016-10-17 17:22
     * @Param params 参数
     * @Return int 返回类型
     * @Throws exception
     */
    int updateByMap(Map<String, Object>params) throws Exception;

}
