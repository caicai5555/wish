package com.foundation.service.medicalHistory.biz;

import java.util.List;
import java.util.Map;

import com.foundation.common.persistence.Page;
import com.foundation.dao.entity.medicalHistory.SurgeryHistory;

/**
 * 
* @ClassName: ISurgeryHistoryBiz 
* @Description:  手术史业务层
* @author chengchen 
* @date 2016年11月23日 上午10:08:57 
*
 */
public interface ISurgeryHistoryBiz {
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
	public boolean save(SurgeryHistory history) throws Exception;
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
	public void update(SurgeryHistory history) throws Exception;
	/**
	 * 
	* @Title: queryById 
	* @Description: 通过id获取实体
	* @author chengchen
	* @date 2016年11月22日 下午3:52:30 
	* @param @param id
	* @param @return
	* @param @throws Exception    设定参数 
	* @return SurgeryHistory    返回类型 
	* @throws
	 */
	public SurgeryHistory queryById(String id) throws Exception;
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
	* @return Page<SurgeryHistory>    返回类型 
	* @throws
	 */
	public Page<SurgeryHistory> queryPage(Map<String, Object> params,Page<SurgeryHistory> pageBounds) throws Exception;
	
	/**
	 * 
	* @Title: queryList 
	* @Description: 获取列表
	* @author chengchen
	* @date 2016年11月22日 下午3:53:52 
	* @param @param params
	* @param @return
	* @param @throws Exception    设定参数 
	* @return List<SurgeryHistory>    返回类型 
	* @throws
	 */
	public List<SurgeryHistory> queryList(Map<String, Object> params) throws Exception;
	
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
	* @Title: delete 
	* @Description: 删除（逻辑删除）
	* @author chengchen
	* @date 2016年11月23日 上午10:57:54 
	* @param @param id
	* @param @throws Exception    设定参数 
	* @return void    返回类型 
	* @throws
	 */
	public void delete(String id) throws Exception;
	
}
