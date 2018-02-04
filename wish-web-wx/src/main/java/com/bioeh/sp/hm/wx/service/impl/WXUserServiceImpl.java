/**
 * 
 */
package com.bioeh.sp.hm.wx.service.impl;

import com.bioeh.sp.hm.dal.ICommonDao;
import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.build.operator.Operator;
import com.bioeh.sp.hm.wx.common.constant.DBConstants;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.bioeh.sp.hm.wx.service.IWXUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 微信Service实现
 */
@Service
public class WXUserServiceImpl implements IWXUserService{
	
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXUserServiceImpl.class);
	
	/**
	 * JDBC接口
	 */
	@Autowired
	private ICommonDao commonDao;
	

	
	/**
	 * 新建用户	
	 * @param wxuser	用户实体信息
	 * @return
	 */
	@Override
	public long addWXUser(WXUser wxuser){
		int  resultId = 0;
		try {
			resultId = commonDao.saveEntity(wxuser);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return resultId;
	}
	
	/*
	 * 更新操作
	 */
	@Override
	public boolean updateWXUser( WXUser wxuser) throws Exception {
		boolean b = false;
		//修改的字段和值
		Map<String,Object> map = WechatConstant.returnWXUserValue(wxuser);
		try {

			b = commonDao.updateEntityById(wxuser.getId(), map, WXUser.class);
		} catch (Exception e) {
			logger.error("更新操作错误。", e);
			throw e;
		}
		if (b) {
			logger.info("【/update】执行成功。");
			return b;
		} else {
			logger.info("【/update】修改失败！");
		}
		return b;
	}

	@Override
	public boolean checkIsExist(String opendid) {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.OPEN_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ opendid,0 });
			List<WXUser> wxUserList = commonDao.findEntityList(condition, null, WXUser.class);
			result = (null != wxUserList && wxUserList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
		}

		return result;
	}

	@Override
	public WXUser getWXUserById(long id) {
		WXUser wxUser = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.ID },
					new Operator[]{ Operator.EQ },
					new Object[]{ id });
			List<WXUser> wxDeviceList = commonDao.findEntityList(condition, null, WXUser.class);
			if(null != wxDeviceList && wxDeviceList.size() > 0) {
				wxUser = wxDeviceList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXDeviceById]执行出错.",e);
		}

		return wxUser;
	}

	@Override
	public List<WXUser> getWXUserList(String openid) {
		List<WXUser>  wxDeviceList = null;
		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.OPEN_ID, DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ, Operator.EQ }, new Object[]{ openid,0 });
			wxDeviceList = commonDao.findEntityList(condition, null, WXUser.class);
		} catch (Exception e) {
			logger.error("操作失败", e);
		}
		return wxDeviceList;
	}

	@Override
	public WXUser getWXUserByOpenId(String openid) {
		WXUser wxUser = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.OPEN_ID, DBConstants.Common.STATUS},
					new Operator[]{ Operator.EQ,Operator.EQ },
					new Object[]{ openid,0 });
			List<WXUser> wxUserList = commonDao.findEntityList(condition, null, WXUser.class);
			if(null != wxUserList && wxUserList.size() > 0) {
				wxUser = wxUserList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXDeviceById]执行出错.",e);
		}

		return wxUser;
	}

	@Override
	public boolean checkIsExistByMobile(String mobile) {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MOBILE, DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ mobile,0 });
			List<WXUser> wxUserList = commonDao.findEntityList(condition, null, WXUser.class);
			result = (null != wxUserList && wxUserList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
		}

		return result;
	}

	@Override
	public WXUser getWXUserByMobile(String mobile) {
		WXUser wxUser = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.MOBILE, DBConstants.Common.STATUS},
					new Operator[]{ Operator.EQ,Operator.EQ },
					new Object[]{ mobile,0 });
			List<WXUser> wxUserList = commonDao.findEntityList(condition, null, WXUser.class);
			if(null != wxUserList && wxUserList.size() > 0) {
				wxUser = wxUserList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXUserByMobile]执行出错.",e);
		}

		return wxUser;
	}
}
