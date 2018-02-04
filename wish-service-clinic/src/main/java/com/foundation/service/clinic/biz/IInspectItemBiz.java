package com.foundation.service.clinic.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectItem;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: IInspectItemBiz
 * @Description: 检验项的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
public interface IInspectItemBiz {

	/** 
	 * @Title: save 
	 * @Description: 保存检验项
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:26
	 * @Param inspectItem 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean save(InspectItem inspectItem) throws Exception;
	
	/** 
	 * @Title: update 
	 * @Description: 修改整个检验项
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param inspectItem 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean update(InspectItem inspectItem) throws Exception;

	/**
	 * @Title: queryById 
	 * @Description: 通过id获取检验项
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:28
	 * @Param id 参数
	 * @Return clinicRecord 返回类型
	 * @Throws exception
	 */ 
	InspectItem queryById(String id) throws Exception;

	/**
	 * @Title: queryPage
	 * @Description: 获取检验项分页信息
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param params 查询字段条件
	 * @Param page 分页条件
	 * @Return Page 返回类型
	 * @Throws exception
	 */
	Page<InspectItem> queryPage(Map<String, Object> params, Page<InspectItem> page) throws Exception;

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
	 * @Description: 物理删除检验项
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:22
	 * @Param id 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean deleteReal(String id) throws Exception;

	/**
	 * @Title: queryPage
	 * @Description: 获取检验项分页信息
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param params 查询字段条件
	 * @Param pageNo 页码
	 * @Param pageSize 每页数量
	 * @Return Page<InspectItem> 返回类型
	 * @Throws exception
	 */
	Page<InspectItem> queryPage(Map<String, Object> params, int pageNo, int pageSize) throws Exception;

	/**
	 * @Title: queryPage
	 * @Description: 获取检验项分页信息，默认获取第一页的数据
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param params 查询字段条件
	 * @Return Page 返回类型
	 * @Throws exception
	 */
	Page<InspectItem> queryPage(Map<String, Object> params) throws Exception;

	/**
	 * @Title: queryAll
	 * @Description: 最多获取100条检验项据
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param params 查询字段条件
	 * @Return List 返回类型
	 * @Throws exception
	 */
	List<InspectItem> queryAll(Map<String, Object> params) throws Exception;

}
