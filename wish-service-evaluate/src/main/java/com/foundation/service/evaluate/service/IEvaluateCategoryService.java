package com.foundation.service.evaluate.service;

import java.util.List;
import java.util.Map;

import com.foundation.dao.entity.evaluate.EvaluateCategory;

/**
 * 
* @ClassName: IEvaluateCategoryService 
* @Description: 评估分类服务层
* @author chengchen 
* @date 2016年10月13日 下午6:19:42 
*
 */
public interface IEvaluateCategoryService {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估分类
	* @author chengchen
	* @date 2016年10月13日 下午6:19:53 
	* @param @param category
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(EvaluateCategory category) throws Exception;
	/**
	 * 
	* @Title: update 
	* @Description: 更新评估分类
	* @author chengchen
	* @date 2016年10月13日 下午6:20:04 
	* @param @param category
	* @param @return
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void update(EvaluateCategory category) throws Exception;
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估分类
	* @author chengchen
	* @date 2016年10月13日 下午6:20:15 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateCategory    返回类型 
	* @throws
	 */
	public EvaluateCategory queryById(String id) throws Exception;
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 获取评估分类数量 
	* @author chengchen
	* @date 2016年10月13日 下午6:21:51 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String, Object> params) throws Exception;
	/**
	 * 
	* @Title: queryList 
	* @Description: 获取评估分类列表 
	* @author chengchen
	* @date 2016年10月13日 下午7:10:04 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateCategory>    返回类型 
	* @throws
	 */
	public List<EvaluateCategory> queryList(Map<String, Object> params) throws Exception;
}
