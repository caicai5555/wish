package com.foundation.service.evaluate.service;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateRule;

/**
 * 
* @ClassName: IEvaluateRuleService 
* @Description: 评估规则服务层
* @author chengchen 
* @date 2016年10月13日 下午6:19:42 
*
 */
public interface IEvaluateRuleService {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估规则
	* @author chengchen
	* @date 2016年10月13日 下午6:19:53 
	* @param @param rule
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(EvaluateRule rule) throws Exception;
	/**
	 * 
	* @Title: update 
	* @Description: 更新评估规则
	* @author chengchen
	* @date 2016年10月13日 下午6:20:04 
	* @param @param rule
	* @param @return
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void update(EvaluateRule rule) throws Exception;
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估规则
	* @author chengchen
	* @date 2016年10月13日 下午6:20:15 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateRule    返回类型 
	* @throws
	 */
	public EvaluateRule queryById(String id) throws Exception;
	/**
	 * 
	* @Title: queryPage 
	* @Description: 获取评估规则分页列表
	* @author chengchen
	* @date 2016年10月13日 下午6:21:38 
	* @param @param params
	* @param @param pageBounds
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Page    返回类型 
	* @throws
	 */
	public Page<EvaluateRule> queryPage(Map<String, Object> params,Page<EvaluateRule> pageBounds) throws Exception;
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 获取评估规则数量 
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
	* @Description: 获取评估规则列表 
	* @author chengchen
	* @date 2016年10月13日 下午7:10:04 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateRule>    返回类型 
	* @throws
	 */
	public List<EvaluateRule> queryList(Map<String, Object> params) throws Exception;
}
