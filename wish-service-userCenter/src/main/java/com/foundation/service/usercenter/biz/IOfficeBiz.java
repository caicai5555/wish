package com.foundation.service.usercenter.biz;

import com.foundation.dao.entity.sys.Office;

import java.util.List;

/**
 * 机构Service
 * @author fqh
 * @version 2016-08-16
 */

public interface IOfficeBiz {

	/**
	 * @Title:
	 * @Description: 查询所有的机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 * @param
	 * @return
	 * @throws
	 */
	public List<Office> findAll();
	/**
	 * @Title:
	 * @Description: 查询机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 * @param
	 * @return
	 * @throws
	 */

	public List<Office> findList(Boolean isAll);
	/**
	 * @Title:
	 * @Description: 根据机构id查询机构
	 * @author chunyangli
	 * @date 2016/10/18 10:16
	 * @param     
	 * @return    
	 * @throws 
	 */
	
	public List<Office> findList(Office office);
	/**
	 * @Title:
	 * @Description: 保存机构
	 * @author chunyangli
	 * @date 2016/10/18 10:17
	 * @param     
	 * @return    
	 * @throws 
	 */
	
	public void save(Office office);
	/**
	 * @Title:
	 * @Description: 保存机构
	 * @author chunyangli
	 * @date 2016/10/18 10:17
	 * @param
	 * @return
	 * @throws
	 */
	
	public void delete(Office office);
	
}
