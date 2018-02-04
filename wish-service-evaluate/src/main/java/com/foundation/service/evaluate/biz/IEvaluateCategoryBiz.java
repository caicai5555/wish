package com.foundation.service.evaluate.biz;

import java.util.List;
import java.util.Map;

import com.foundation.dao.entity.evaluate.EvaluateCategory;

/**
 * 
* @ClassName: IEvaluateCategoryBiz 
* @Description: 评估分类业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:09:38 
*
 */
public interface IEvaluateCategoryBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估分类
	* @author chengchen
	* @date 2016年10月13日 下午6:45:06 
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
	* @date 2016年10月13日 下午6:45:18 
	* @param @param category
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void update(EvaluateCategory category) throws Exception;

	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估分类
	* @author chengchen
	* @date 2016年10月13日 下午6:45:51 
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
	* @return List<EvaluateCategory>    返回类型 
	* @throws
	 */
	public List<EvaluateCategory> queryList(Map<String, Object> params) throws Exception;
	/**
	 * 
	* @Title: delete 
	* @Description: 删除评估分类（逻辑删除）
	* @author chengchen
	* @date 2016年10月25日 下午5:34:22 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean delete(String id) throws Exception;
	
	/**
	 * 
	* @Title: queryByEnname 
	* @Description: 通过英文名获取分类 
	* @author chengchen
	* @date 2016年10月29日 下午9:22:28 
	* @param @param enname
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateCategory    返回类型 
	* @throws
	 */
	public EvaluateCategory queryByEnname(String enname) throws Exception;
}
