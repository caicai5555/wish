package com.bioeh.sp.hm.wx.biz;


import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.wx.entity.WXDevice;

import java.util.List;

/** 
 * @ClassName: IBioDeviceBiz
 * @Description: 设备业务层接口)
 * @version V1.0 
 */
public interface IWXDeviceBiz {
	/**
	 * 添加设备信息
	 * @param device
	 * @return
	 */
	public long addDevice(WXDevice device) throws Exception;
	
	/**
	 * 修改设备信息
	 * @param device
	 * @return
	 */
	public boolean updateDevice(WXDevice device) throws Exception;


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
	 * 根据id主建查询设备信息
	 * @param id
	 * @return
	 */
	public WXDevice getWXDeviceById(Long id) throws Exception;

}
