package com.foundation.service.clinic.biz;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.clinic.InspectListOrg;

import java.util.Map;

/**
 * @ClassName: IInspectListOrgBiz
 * @Description: 检验单与授权机构关系的业务层
 * @Author cuiyaohua
 * @Date 2016/10/17
 *
 */
public interface IInspectListOrgBiz {

	/**
	 * @Title: save
	 * @Description: 保存检验单与授权机构关系
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:26
	 * @Param inspectListOrg 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean save(InspectListOrg inspectListOrg) throws Exception;

	/**
	 * @Title: update
	 * @Description: 修改整个检验单与授权机构关系
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:27
	 * @Param inspectListOrg 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean update(InspectListOrg inspectListOrg) throws Exception;

	/**
	 * @Title: queryById
	 * @Description: 通过id获取检验单与授权机构关系
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:28
	 * @Param id 参数
	 * @Return clinicRecord 返回类型
	 * @Throws exception
	 */
	InspectListOrg queryById(String id) throws Exception;

	/**
	 * @Title: queryPage
	 * @Description: 获取检验单与授权机构关系分页信息
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:19
	 * @Param params 查询字段条件
	 * @Param page 分页条件
	 * @Return Page<InspectListOrg> 返回类型
	 * @Throws exception
	 */
	Page<InspectListOrg> queryPage(Map<String, Object> params, Page<InspectListOrg> page) throws Exception;

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
	 * @Description: 物理删除检验单与授权机构关系
	 * @Author cuiyaohua
	 * @Date 2016-10-17 17:22
	 * @Param id 参数
	 * @Return boolean 返回类型
	 * @Throws exception
	 */
	boolean deleteReal(String id) throws Exception;

}
