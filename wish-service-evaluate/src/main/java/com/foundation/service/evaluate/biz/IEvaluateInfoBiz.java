package com.foundation.service.evaluate.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateInfo;

/**
 * 
* @ClassName: IEvaluateInfoBiz 
* @Description: 评估信息业务层 
* @author chengchen 
* @date 2016年10月12日 上午11:09:38 
*
 */
public interface IEvaluateInfoBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估信息
	* @author chengchen
	* @date 2016年10月12日 上午11:14:02 
	* @param @param info
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(EvaluateInfo info) throws Exception;
	/**
	 * 
	* @Title: update 
	* @Description: 更新评估信息 
	* @author chengchen
	* @date 2016年10月12日 上午11:14:13 
	* @param @param info
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void update(EvaluateInfo info) throws Exception;
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取评估信息 
	* @author chengchen
	* @date 2016年10月12日 上午11:14:22 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return EvaluateInfo    返回类型 
	* @throws
	 */
	public EvaluateInfo queryById(String id) throws Exception;
	
	/**
	 * 
	* @Title: queryPage 
	* @Description: 获取评估信息分页列表
	* @author chengchen
	* @date 2016年10月12日 上午11:14:44 
	* @param @param params
	* @param @param pageBounds
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Page    返回类型 
	* @throws
	 */
	public Page<EvaluateInfo> queryPage(Map<String, Object> params,Page<EvaluateInfo> pageBounds) throws Exception;
	
	/**
	 * 
	* @Title: queryList 
	* @Description: 获取评估信息列表
	* @author chengchen
	* @date 2016年10月29日 下午9:39:44 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateInfo>    返回类型 
	* @throws
	 */
	public List<EvaluateInfo> queryList(Map<String, Object> params) throws Exception;
	/**
	 * 
	* @Title: queryList 
	* @Description: 通过分类获取评估信息列表
	* @author chengchen
	* @date 2016年10月29日 下午9:40:08 
	* @param @param evaluateInfoId
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateInfo>    返回类型 
	* @throws
	 */
	public List<EvaluateInfo> queryList(String evaluateCategoryId) throws Exception;
	/**
	 * 
	* @Title: getCount 
	* @Description: 根据参数获取记录数
	* @author chengchen
	* @date 2016年10月12日 上午11:14:34 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String,Object> params) throws Exception;
	
	/**
	 * 
	* @Title: start 
	* @Description: 评估在执行任务时调用，会改变实体中的status 
	* @author chengchen
	* @date 2016年10月18日 上午9:51:55 
	* @param @param id
	* @param @param userId
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void start(String id) throws Exception;
	
	/**
	 * 
	* @Title: complete 
	* @Description: 评估在执行任务完调用，会改变实体中的status，times
	* @author chengchen
	* @date 2016年10月18日 上午9:54:57 
	* @param @param id
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void complete(String id) throws Exception;
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除评估信息（逻辑删除） 
	* @author chengchen
	* @date 2016年10月18日 上午9:55:17 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 
	* @Title: deleteReal 
	* @Description: 删除评估信息（物理删除）慎用！！！
	* @author chengchen
	* @date 2016年10月18日 上午9:55:52 
	* @param @param id
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteReal(String id) throws Exception;
	
	/**
	 * 
	* @Title: parsingXML 
	* @Description:  解析评估信息，包括：回写评估信息中的中英名，模式；创建评估规则记录（多条）；创建评估参数（多条）  
	* @author chengchen
	* @date 2016年10月18日 上午10:14:17 
	* @param @param info
	* @param @param inputStream
	* @param @return
	* @param @throws Exception    设定参数 
	* @return JSONObject    返回类型 
	* @throws
	 */
	public JSONObject parsingXML(EvaluateInfo info,InputStream inputStream) throws Exception;
	
	
	
}
