/**
 * 
 */
package com.bioeh.sp.hm.wx.biz.impl;

import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.util.PrimaryKeyGenerator;
import com.bioeh.sp.hm.wx.biz.IWXUserBiz;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.bioeh.sp.hm.wx.service.IWXUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: WXUserBizImpl
 * @Description: 用户业务层接口实现类)
 * @version V1.0 
 */
@Service
public class WXUserBizImpl implements IWXUserBiz {

	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXUserBizImpl.class);
	
	/**
	 * 微信用户物理层接口
	 */
	@Autowired
	private IWXUserService wxUserService;

	public long addWXUser(WXUser wxuser) throws Exception {
		long id = 0;
		try {
			// 操作结果
			id = PrimaryKeyGenerator.getLongKey();
			wxuser.setId(id);
			wxUserService.addWXUser(wxuser);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return id;
	}

	public boolean updateWXUser(WXUser wxuser) throws Exception {
		boolean flag = false;
		try {
			flag = wxUserService.updateWXUser(wxuser);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	public boolean checkIsExist(String opendid) throws Exception {
		boolean flag = false;
		try {
			flag = wxUserService.checkIsExist(opendid);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public WXUser getWXUserById(long id) throws Exception {
		WXUser wxUser = null;
		try {
			wxUser = wxUserService.getWXUserById(id);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserById]执行出错.", e);
		}
		return wxUser;
	}

	@Override
	public WXUser getWXUserByOpenId(String openid) throws Exception {
		WXUser wxUser = null;
		try {
			wxUser = wxUserService.getWXUserByOpenId(openid);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserById]执行出错.", e);
		}
		return wxUser;
	}

	@Override
	public List<WXUser> getWXUserList(String openid) throws Exception {
		List<WXUser> siplist = null;
		try {
			siplist = wxUserService.getWXUserList(openid);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return siplist;
	}

	@Override
	public boolean checkIsExistByMobile(String mobile) throws Exception {
		boolean flag = false;
		try {
			flag = wxUserService.checkIsExistByMobile(mobile);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public WXUser getWXUserByMobile(String mobile) throws Exception {
		WXUser wxUser = null;
		try {
			wxUser = wxUserService.getWXUserByMobile(mobile);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserByMobile]执行出错.", e);
		}
		return wxUser;
	}
}
