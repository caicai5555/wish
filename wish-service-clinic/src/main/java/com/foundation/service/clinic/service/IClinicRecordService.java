package com.foundation.service.clinic.service;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.ClinicRecord;

import java.util.Map;

/**
 * @ClassName: IClinicRecordService
 * @Description: 临床记录的服务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
public interface IClinicRecordService {
    /**
     * @Title: save
     * @Description: 保存临床记录
     * @Author cuiyaohua
     * @Date 2016-10-17 17:14
     * @Param clinicRecord 设定参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean save(ClinicRecord clinicRecord) throws Exception;

    /**
     * @Title: update
     * @Description: 修改整个临床记录
     * @Author cuiyaohua
     * @Date 2016-10-17 17:16
     * @Param clinicRecord 参数
     * @Return boolean 返回类型
     * @Throws exception 异常
     */
    boolean update(ClinicRecord clinicRecord) throws Exception;

    /**
     * @Title: queryById
     * @Description: 通过id获取临床记录
     * @Author cuiyaohua
     * @Date 2016-10-17 17:17
     * @Param id 参数
     * @Return clinicRecord 返回类型
     * @Throws exception
     */
    ClinicRecord queryById(String id) throws Exception;

    /**
     * @Title: queryPage
     * @Description: 获取临床记录分页信息
     * @Author cuiyaohua
     * @Date 2016-10-17 17:19
     * @Param params 查询字段条件
     * @Param page 分页条件
     * @Return Page<ClinicRecord> 返回类型
     * @Throws exception
     */
    Page<ClinicRecord> queryPage(Map<String, Object> params, Page<ClinicRecord> page) throws Exception;

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
     * @Description: 物理删除临床记录
     * @Author cuiyaohua
     * @Date 2016-10-17 17:22
     * @Param id 参数
     * @Return boolean 返回类型
     * @Throws exception
     */
    boolean deleteReal(String id) throws Exception;

}
