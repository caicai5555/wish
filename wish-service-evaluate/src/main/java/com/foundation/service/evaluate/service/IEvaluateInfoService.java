package com.foundation.service.evaluate.service;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.evaluate.EvaluateInfo;

/**
 * 
* @ClassName: IEvaluateInfoService 
* @Description: 评估信息服务层
* @author chengchen 
* @date 2016年10月12日 上午10:50:01 
*
 */
public interface IEvaluateInfoService {
	/**
	 * 
	* @Title: save 
	* @Description: 保存评估信息
	* @author chengchen
	* @date 2016年10月12日 上午10:50:14 
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
	* @date 2016年10月12日 上午10:50:40 
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
	* @date 2016年10月12日 上午10:50:50 
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
	* @date 2016年10月12日 上午10:51:46 
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
	* @date 2016年10月29日 下午9:32:06 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<EvaluateInfo>    返回类型 
	* @throws
	 */
	public List<EvaluateInfo> queryList(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 根据参数获取记录数
	* @author chengchen
	* @date 2016年10月12日 上午10:52:12 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return int    返回类型 
	* @throws
	 */
	public int getCount(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	* @Title: deleteReal 
	* @Description: 删除评估信息（物理删除）慎用！！！
	* @author chengchen
	* @date 2016年10月12日 上午10:52:24 
	* @param @param pkId
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteReal(String id) throws Exception;
	
	/**
	 * 
	* @Title: updateTimes 
	* @Description: 更新测试
	* @author chengchen
	* @date 2016年10月18日 上午10:11:01 
	* @param @param id
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void updateTimes(String id) throws Exception;
	
}
