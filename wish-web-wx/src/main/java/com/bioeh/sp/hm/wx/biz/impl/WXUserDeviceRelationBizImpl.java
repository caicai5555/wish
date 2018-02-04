/**
 * 
 */
package com.bioeh.sp.hm.wx.biz.impl;

import com.bioeh.sp.hm.dal.util.PrimaryKeyGenerator;
import com.bioeh.sp.hm.wx.biz.IWXDeviceRelationBiz;
import com.bioeh.sp.hm.wx.biz.IWXUserDeviceRelationBiz;
import com.bioeh.sp.hm.wx.entity.WXDeviceRelation;
import com.bioeh.sp.hm.wx.entity.WXUserDeviceRelation;
import com.bioeh.sp.hm.wx.service.IWXDeviceRelationService;
import com.bioeh.sp.hm.wx.service.IWXUserDeviceRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @ClassName: BioDeviceBizImpl
 * @Description TOOD微信用户和与设备关系
 * @version V1.0
 */
@Service
public class WXUserDeviceRelationBizImpl implements IWXUserDeviceRelationBiz {

	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXUserDeviceRelationBizImpl.class);
	
	/**
	 * 设备物理层接口
	 */
	@Autowired
	private IWXUserDeviceRelationService iwxUserDeviceRelationService;


	@Override
	public long addWXUserDeviceRelation(WXUserDeviceRelation wxUserDeviceRelation) throws Exception {
		long id = 0;
		try {
			// 操作结果
			id = PrimaryKeyGenerator.getLongKey();
			wxUserDeviceRelation.setId(id);
			iwxUserDeviceRelationService.addWXUserDeviceRelation(wxUserDeviceRelation);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return id;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByOpenId(String openId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;
		try {
			wxUserDeviceRelation = iwxUserDeviceRelationService.getWXUserDeviceRelationByOpenId(openId);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserDeviceRelationByOpenId]执行出错.", e);
		}
		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByMac(String mac) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;
		try {
			wxUserDeviceRelation = iwxUserDeviceRelationService.getWXUserDeviceRelationByMac(mac);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserDeviceRelationByMac]执行出错.", e);
		}
		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByUserId(Integer userId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;
		try {
			wxUserDeviceRelation = iwxUserDeviceRelationService.getWXUserDeviceRelationByUserId(userId);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserDeviceRelationByUserId]执行出错.", e);
		}
		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByWxDeviceId(Integer wxDeviceId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;
		try {
			wxUserDeviceRelation = iwxUserDeviceRelationService.getWXUserDeviceRelationByWxDeviceId(wxDeviceId);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.getWXUserDeviceRelationByWxDeviceId]执行出错.", e);
		}
		return wxUserDeviceRelation;
	}

	@Override
	public boolean updateWXUserRelationDevice(WXUserDeviceRelation wxUserDeviceRelation) throws Exception {
		boolean flag = false;
		try {
			flag = iwxUserDeviceRelationService.updateWXUserRelationDevice(wxUserDeviceRelation);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public boolean checkIsBind(String opendid) throws Exception {
		boolean flag = false;
		try {
			flag = iwxUserDeviceRelationService.checkIsBind(opendid);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public List<WXUserDeviceRelation> getWXUserDeviceList(String opendid) throws Exception {
		List<WXUserDeviceRelation> wxUserDeviceRelationList = null;
		try {
			wxUserDeviceRelationList = iwxUserDeviceRelationService.getWXUserDeviceList(opendid);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return wxUserDeviceRelationList;
	}

	@Override
	public boolean checkIsExist(String mac) throws Exception {
		boolean flag = false;
		try {
			flag = iwxUserDeviceRelationService.checkIsExist(mac);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceById(long id) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;
		try {
			wxUserDeviceRelation = iwxUserDeviceRelationService.getWXUserDeviceById(id);
		} catch (Exception e) {
			logger.error("[WXUserBizImpl.88getWXUserById]执行出错.", e);
		}
		return wxUserDeviceRelation;
	}

	@Override
	public boolean checkIsExist(String mac, String opendid) {
		boolean flag = false;
		try {
			flag = iwxUserDeviceRelationService.checkIsExist(mac,opendid);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return flag;
	}
}
