package com.foundation.service.evaluate.biz;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateParam;

/**
 * 
* @ClassName: IEvaluateParamBiz 
* @Description: 评估参数业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:09:38 
*
 */
public interface IEvaluateParamBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估参数
	* @author chengchen
	* @date 2016年10月13日 下午6:45:06 
	* @param @param param
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(EvaluateParam param) throws Exception;

	/**
	 * 
	* @Title: update 
	* @Description: 更新评估参数
	* @author chengchen
	* @date 2016年10月13日 下午6:45:18 
	* @param @param param
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void update(EvaluateParam param) throws Exception;

	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估参数
	* @author chengchen
	* @date 2016年10月13日 下午6:45:51 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateParam    返回类型 
	* @throws
	 */
	public EvaluateParam queryById(String id) throws Exception;

	/**
	 * 
	* @Title: queryPage 
	* @Description: 获取评估参数分页列表 
	* @author chengchen
	* @date 2016年10月13日 下午6:49:31 
	* @param @param params
	* @param @param pageBounds
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Page    返回类型 
	* @throws
	 */
	public Page<EvaluateParam> queryPage(Map<String, Object> params,Page<EvaluateParam> pageBounds) throws Exception;
	
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
	* @return List<EvaluateParam>    返回类型 
	* @throws
	 */
	public List<EvaluateParam> queryList(Map<String, Object> params) throws Exception;
}
