package com.foundation.service.clinic.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.mongo.entity.ClinicRecordDetail;

import java.util.Map;

/** 
 * @ClassName: IClinicRecordBiz
 * @Description: 临床记录的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
public interface IClinicRecordBiz {

	/** 
	 * @Title: save 
	 * @Description: 保存临床记录
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:26
	 * @Param clinicRecord 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean save(ClinicRecord clinicRecord) throws Exception;
	
	/** 
	 * @Title: update 
	 * @Description: 修改整个临床记录
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param clinicRecord 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean update(ClinicRecord clinicRecord) throws Exception;

	/**
	 * @Title: queryById 
	 * @Description: 通过id获取临床记录
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:28
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

	/**
	 * @Title: queryDetailById
	 * @Description: 通过id获取临床记录
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:28
	 * @Param recordId 参数
	 * @Return ClinicRecordDetail 返回类型
	 * @Throws exception
	 */
	ClinicRecordDetail queryDetailById(String recordId) throws Exception;


	/**
	 * @Title: update
	 * @Description: 修改整个临床记录, 同时更新mongo中数据
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param clinicRecord 参数
	 * @Param inspectItemsValues 参数
	 * @Param codes 指标编号
	 * @Param names 指标名称
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean update(ClinicRecord clinicRecord, String[] inspectItemsValues, String[] codes, String[] names) throws Exception;
}
