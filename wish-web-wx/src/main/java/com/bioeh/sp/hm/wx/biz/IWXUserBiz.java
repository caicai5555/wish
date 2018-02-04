package com.bioeh.sp.hm.wx.biz;


import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.wx.entity.WXUser;

import java.util.List;


/** 
 * @ClassName: IWXUserBiz
 * @Description: 微信用户业务层接口)
 * @version V1.0
 */
public interface IWXUserBiz {
	/**
	 * 添加微信用户角色
	 * @param wxuser
	 * @return
	 */
	public long addWXUser(WXUser wxuser) throws Exception;
	
	/**
	 * 添加微信用户角色
	 * @param wxuser
	 * @return
	 */
	public boolean updateWXUser(WXUser wxuser) throws Exception;

	/**
	 * 根据openId判断用户是否存在
	 * @param opendid
	 * @return
	 */
	public boolean checkIsExist(String opendid) throws Exception;
	/**
	 * 判断用户id查询单独的用户信息
	 * @param id
	 * @return
	 */
	public WXUser getWXUserById(long id) throws Exception;

	/**
	 * 判断用户id查询单独的用户信息
	 * @param openid
	 * @return
	 */
	public WXUser getWXUserByOpenId(String openid) throws Exception;

	/**
	 * 通过openid获取所有的设备
	 * @param opendid
	 * @return
	 */
	public List<WXUser> getWXUserList(String opendid) throws Exception;

	/**
	 * 根据mobile判断是否存在 （主要是为了用户绑定时候判断 8.17 定的 一个手机号只能绑定一个微信号）
	 * @param mobile
	 * @return
	 */
	public boolean checkIsExistByMobile(String mobile) throws Exception;

	/**
	 * 一个手机号只能绑定一个微信号
	 * @param mobile
	 * @return
	 */
	public WXUser getWXUserByMobile(String mobile) throws Exception;
}
