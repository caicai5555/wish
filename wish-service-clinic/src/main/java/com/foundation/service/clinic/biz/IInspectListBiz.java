package com.foundation.service.clinic.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.entity.clinic.InspectList;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: IInspectListBiz
 * @Description: 检验单的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 * 
 */
public interface IInspectListBiz {

	/** 
	 * @Title: save 
	 * @Description: 保存检验单
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:26
	 * @Param inspectList 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean save(InspectList inspectList) throws Exception;

	/**
	 * @Title: save
	 * @Description: 保存检验单
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:26
	 * @Param inspectList 参数
	 * @Param itemIds 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean save(InspectList inspectList, String itemIds) throws Exception;

	/** 
	 * @Title: update 
	 * @Description: 修改整个检验单
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param inspectList 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */ 
	boolean update(InspectList inspectList) throws Exception;

	/**
	 * @Title: queryById 
	 * @Description: 通过id获取检验单
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:28
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

	/**
	 * @Title: queryItem
	 * @Description: 获取检验单拥有的检验项
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param inspectId 查询字段条件
	 * @Return List 返回类型
	 * @Throws exception
	 */
	List<InspectItem> queryItem(String inspectId)throws Exception;

	/**
	 * @Title: update
	 * @Description: 修改整个检验单
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param inspectList 参数
	 * @Param itemIdsNew 参数
	 * @Param itemIdsDel 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean update(InspectList inspectList, String itemIdsNew, String itemIdsDel) throws Exception;
}
