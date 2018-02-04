/**
 * 
 */
package com.bioeh.sp.hm.wx.service;

import com.bioeh.sp.hm.wx.entity.WXUser;

import java.util.List;

/**
 * @ClassName: IWXUserService
 * @Description: TOOD(微信用户 接口)
 * @author lz
 * @date
 * @version V1.0 
 */
public interface IWXUserService {

	/**
	 * 新建微信用户
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public long addWXUser(WXUser wxuser) throws Exception;
	
	/**
	 * 修改用户
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public boolean updateWXUser(WXUser wxuser) throws Exception;

	/**
	 * 检查用户是否存在
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public boolean checkIsExist(String opendid);
	/**
	 * 根据主键查询微信用户
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public WXUser getWXUserById(long id);
	/**
	 * 根据openid查询
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public List<WXUser> getWXUserList(String openid);
	/**
	 * 根据openid查询微信用户
	 * @param WXUser	用户实体信息
	 * @return
	 */
	public WXUser getWXUserByOpenId(String openid);
	/**
	 * 根据mobile判断是否存在 （主要是为了用户绑定时候判断 8.17 定的 一个手机号只能绑定一个微信号）
	 * @param mobile
	 * @return
	 */
	public boolean checkIsExistByMobile(String mobile);

	/**
	 * 一个手机对应一个手机号
	 * @param mobile
	 * @return
	 */
	public WXUser getWXUserByMobile(String mobile);
}
