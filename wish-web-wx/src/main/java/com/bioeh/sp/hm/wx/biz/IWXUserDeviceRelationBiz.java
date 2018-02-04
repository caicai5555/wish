package com.bioeh.sp.hm.wx.biz;


import com.bioeh.sp.hm.wx.entity.WXUserDeviceRelation;

import java.util.List;


/** 
 * @ClassName: IBioDeviceBiz
 * @Description:  TOOD微信device_id与设备mac关系
 * @version V1.0 
 */
public interface IWXUserDeviceRelationBiz {
	/**
	 * 新建微信device_id与设备mac关系
	 * @param wxUserDeviceRelation	设
	 * @return
	 */
	public long addWXUserDeviceRelation(WXUserDeviceRelation wxUserDeviceRelation) throws Exception;

	/**
	 * 根据openId查询设备和设备关系表
	 * @param openId
	 * @return
	 */
	public WXUserDeviceRelation getWXUserDeviceRelationByOpenId(String openId) throws Exception;

	/**
	 * 根据mac查询设备和设备关系表
	 * @param mac
	 * @return
	 */
	public WXUserDeviceRelation getWXUserDeviceRelationByMac(String mac) throws Exception;

	/**
	 * 根据mac查询设备和设备关系表
	 * @param userId
	 * @return
	 */
	public WXUserDeviceRelation getWXUserDeviceRelationByUserId(Integer userId) throws Exception;

	/**
	 * 根据微信设备id查询设备和设备关系表
	 * @param wxDeviceId
	 * @return
	 */
	public WXUserDeviceRelation getWXUserDeviceRelationByWxDeviceId(Integer wxDeviceId) throws Exception;

	/**
	 * 修改用户设备信息表
	 * @param wxUserDeviceRelation
	 * @return
	 */
	public boolean updateWXUserRelationDevice(WXUserDeviceRelation wxUserDeviceRelation) throws Exception;

	/**
	 * 判断用户是否绑定设备
	 * @param opendid
	 * @return
	 */
	public boolean checkIsBind(String opendid) throws Exception;


	/**
	 * 通过openid获取所有的设备
	 * @param opendid
	 * @return
	 */
	public List<WXUserDeviceRelation> getWXUserDeviceList(String opendid) throws Exception;

	/**
	 * 判断传过来的mac地址判断是否已经存在
	 * @param mac
	 * @return
	 */
	public boolean checkIsExist(String mac) throws Exception;

	/**
	 * 判断id查询单独的设备关系
	 * @param id
	 * @return
	 */
	public WXUserDeviceRelation getWXUserDeviceById(long id) throws Exception;
	/**
	 * 根据mac和用户openid判断 主要是防止用户已经手动绑定设备 又扫码绑定情况
	 * @param mac
	 * @return
	 */
	public boolean checkIsExist(String mac, String opendid);


}
