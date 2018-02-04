package com.foundation.service.evaluate.biz;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateRule;

/**
 * 
* @ClassName: IEvaluateRuleBiz 
* @Description: 评估规则业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:09:38 
*
 */
public interface IEvaluateRuleBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估规则
	* @author chengchen
	* @date 2016年10月13日 下午6:45:06 
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
	* @date 2016年10月13日 下午6:45:18 
	* @param @param rule
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void update(EvaluateRule rule) throws Exception;

	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估规则
	* @author chengchen
	* @date 2016年10月13日 下午6:45:51 
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
	* @date 2016年10月13日 下午6:49:31 
	* @param @param params
	* @param @param pageBounds
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Page   返回类型 
	* @throws
	 */
	public Page<EvaluateRule> queryPage(Map<String, Object> params,Page<EvaluateRule> pageBounds) throws Exception;
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 根据参数获取记录数
	* @author chengchen
	* @date 2016年10月13日 下午7:02:07 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String,Object> params) throws Exception;
	
	/**
	 * 
	* @Title: queryList 
	* @Description: 根据参数获取列表 
	* @author chengchen
	* @date 2016年10月13日 下午7:12:33 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateRule>    返回类型 
	* @throws
	 */
	public List<EvaluateRule> queryList(Map<String, Object> params) throws Exception;
}
