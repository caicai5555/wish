package com.foundation.service.usercenter.biz;

import com.foundation.dao.entity.sys.Area;

import java.util.List;

/**
 * 区域Service
 * @author fqh
 * @version 2016-08-16
 */
public interface ISysAreaBiz {

	/**
	 * @Title:
	 * @Description: 查询所有的区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 * @param     
	 * @return    
	 * @throws 
	 */
	public List<Area> findAll();
	/**
	 * @Title:
	 * @Description: 保存区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 * @param
	 * @return
	 * @throws
	 */

	public void save(Area area);

	/**
	 * @Title:
	 * @Description: 删除区域
	 * @author chunyangli
	 * @date 2016/10/18 10:19
	 * @param
	 * @return
	 * @throws
	 */
	public void delete(Area area);
	
}
