package com.foundation.service.evaluate.biz;

import java.util.List;
import java.util.Map;

import com.foundation.dao.entity.evaluate.EvaluateConclusionContent;

/**
 * 
* @ClassName: IEvaluateConclusionContentBiz 
* @Description: 评估结论内容业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:09:38 
*
 */
public interface IEvaluateConclusionContentBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估结论内容
	* @author chengchen
	* @date 2016年10月13日 下午6:45:06 
	* @param @param conclusion
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(EvaluateConclusionContent conclusion) throws Exception;

	/**
	 * 
	* @Title: update 
	* @Description: 更新评估结论内容
	* @author chengchen
	* @date 2016年10月13日 下午6:45:18 
	* @param @param conclusion
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void update(EvaluateConclusionContent conclusion) throws Exception;

	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估结论内容
	* @author chengchen
	* @date 2016年10月13日 下午6:45:51 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateConclusionContent    返回类型 
	* @throws
	 */
	public EvaluateConclusionContent queryById(String id) throws Exception;

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
	* @return List<EvaluateConclusionContent>    返回类型 
	* @throws
	 */
	public List<EvaluateConclusionContent> queryList(Map<String, Object> params) throws Exception;
}
