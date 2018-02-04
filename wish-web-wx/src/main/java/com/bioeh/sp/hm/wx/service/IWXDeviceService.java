/**
 * 
 */
package com.bioeh.sp.hm.wx.service;

import com.bioeh.sp.hm.wx.entity.WXDevice;



/** 
 * @ClassName: IWXUserService
 * @Description: TOOD(设备 接口)
 * @author lz
 * @date
 * @version V1.0 
 */
public interface IWXDeviceService {

	/**
	 * 新建微信用户
	 * @param device	设备实体信息
	 * @return
	 */
	public long addWXDevice(WXDevice device) throws Exception;
	
	/**
	 * 修改用户
	 * @param device	设备实体信息
	 * @return
	 */
	public boolean updateWXDevicer(WXDevice device) throws Exception;

	/**
	 * 判断传过来的mac地址判断是否已经存在
	 * @param mac
	 * @return
	 */
	public boolean checkIsExist(String mac) throws Exception;

	/**
	 * 根据mac地址查询设备信息
	 * @param mac
	 * @return
	 */
	public WXDevice getWXDeviceByMac(String mac) throws Exception;
	/**
	 * 根据id逐渐查询设备信息
	 * @param id
	 * @return
	 */
	public WXDevice getWXDeviceById(Long id) throws Exception;

}
