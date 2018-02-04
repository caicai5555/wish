/**
 * 
 */
package com.bioeh.sp.hm.wx.service;

import com.bioeh.sp.hm.wx.entity.WXDeviceRelation;



/** 
 * @ClassName: IWXDeviceRelationService
 * @Description: TOOD微信device_id与设备mac关系
 * @author lz
 * @date
 * @version V1.0 
 */
public interface IWXDeviceRelationService {

	/**
	 * 新建微信device_id与设备mac关系
	 * @param wxDeviceRelation	设
	 * @return
	 */
	public long addWXDeviceRelation(WXDeviceRelation wxDeviceRelation) throws Exception;

	/**
	 * 根据mac查询设备关系表
	 * @param mac
	 * @return
	 */
	public WXDeviceRelation getWXDeviceRelationByMac(String mac) throws Exception;

	/**
	 * 根据微信设备id查询设备关系表
	 * @param device_id
	 * @return
	 */
	public WXDeviceRelation getWXDeviceRelationByDeviceId(String device_id) throws Exception;

}
