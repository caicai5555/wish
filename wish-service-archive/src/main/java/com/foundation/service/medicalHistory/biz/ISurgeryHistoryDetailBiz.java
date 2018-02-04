package com.foundation.service.medicalHistory.biz;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistoryDetail;

/**
 * 
* @ClassName: ISurgeryHistoryDetailBiz 
* @Description: 手术史明细服务层
* @author chengchen 
* @date 2016年11月22日 下午3:47:58 
*
 */
public interface ISurgeryHistoryDetailBiz {
	/**
	 * 
	* @Title: save 
	* @Description: 保存
	* @author chengchen
	* @date 2016年11月22日 下午3:49:21 
	* @param @param history
	* @param @return
	* @param @throws Exception    设定参数 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean save(SurgeryHistoryDetail historyDetail) throws Exception;
	/**
	 * 
	* @Title: update 
	* @Description: 更新
	* @author chengchen
	* @date 2016年11月22日 下午3:49:56 
	* @param @param history
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void update(SurgeryHistoryDetail historyDetail) throws Exception;
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取实体
	* @author chengchen
	* @date 2016年11月22日 下午3:52:30 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return AllergyHistory    返回类型 
	* @throws
	 */
	public SurgeryHistoryDetail queryById(String id) throws Exception;
	/**
	 * 
	* @Title: queryPage 
	* @Description: 获取分页列表
	* @author chengchen
	* @date 2016年11月22日 下午3:52:56 
	* @param @param params
	* @param @param pageBounds
	* @param @return
	* @param @throws Exception    设定参数 
	* @return Page<AllergyHistory>    返回类型 
	* @throws
	 */
	public Page<SurgeryHistoryDetail> queryPage(Map<String, Object> params,Page<SurgeryHistoryDetail> pageBounds) throws Exception;
	
	/**
	 * 
	* @Title: queryList 
	* @Description: 获取列表
	* @author chengchen
	* @date 2016年11月22日 下午3:53:52 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<AllergyHistory>    返回类型 
	* @throws
	 */
	public List<SurgeryHistoryDetail> queryList(String historyId) throws Exception;
	
	/**
	 * 
	* @Title: getCount 
	* @Description: 根据参数获取记录数
	* @author chengchen
	* @date 2016年11月22日 下午3:54:40 
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
	* @Description: 删除（物理删除）慎用！！！
	* @author chengchen
	* @date 2016年11月22日 下午3:55:02 
	* @param @param id
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteReal(String id) throws Exception;
	
	/**
	 * 
	* @Title: deleteByHistoryId 
	* @Description:根据参数删除（物理删除）慎用！！
	* @author chengchen
	* @date 2016年11月23日 上午11:38:44 
	* @param @param historyId
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteByHistoryId(String historyId) throws Exception;
	
}
