/**
 * 
 */
package com.bioeh.sp.hm.wx.service.impl;

import com.bioeh.sp.hm.dal.ICommonDao;
import com.bioeh.sp.hm.wx.common.constant.DBConstants;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.entity.WXUserDeviceRelation;
import com.bioeh.sp.hm.wx.service.IWXUserDeviceRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bioeh.sp.hm.dal.build.operator.Condition;
import com.bioeh.sp.hm.dal.build.operator.Operator;
import java.util.List;
import java.util.Map;


/**
 * TOOD微信用户与设备关系
 */
@Service
public class WXUserDeviceRelationServiceImpl implements IWXUserDeviceRelationService{
	
	/**
	 * 日志
	 */
	private static Log logger = LogFactory.getLog(WXUserDeviceRelationServiceImpl.class);
	
	/**
	 * JDBC接口
	 */
	@Autowired
	private ICommonDao commonDao;


	@Override
	public long addWXUserDeviceRelation(WXUserDeviceRelation wxUserDeviceRelation) throws Exception {
		int  resultId = 0;
		try {
			resultId = commonDao.saveEntity(wxUserDeviceRelation);
		} catch (Exception e) {
			logger.error("操作失败" + e);
		}
		return resultId;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByOpenId(String openId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.OPEN_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ openId,0 });
			List<WXUserDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxUserDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXUserDeviceRelationServiceImpl.getWXDeviceRelationByMac]执行出错.",e);
		}

		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByMac(String mac) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MAC , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ mac,0 });
			List<WXUserDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxUserDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXUserDeviceRelationServiceImpl.getWXDeviceRelationByMac]执行出错.",e);
		}

		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByUserId(Integer userId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.USER_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ userId,0 });
			List<WXUserDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxUserDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXUserDeviceRelationServiceImpl.getWXUserDeviceRelationByUserId]执行出错.",e);
		}

		return wxUserDeviceRelation;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceRelationByWxDeviceId(Integer wxDeviceId) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.WX_DEVICE_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ wxDeviceId,0 });
			List<WXUserDeviceRelation> wxDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			if(null != wxDeviceRelationList && wxDeviceRelationList.size() > 0) {
				wxUserDeviceRelation = wxDeviceRelationList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXUserDeviceRelationServiceImpl.getWXUserDeviceRelationByWxDeviceId]执行出错.",e);
		}

		return wxUserDeviceRelation;
	}

	@Override
	public boolean updateWXUserRelationDevice(WXUserDeviceRelation wxUserDeviceRelation) throws Exception {
		boolean falg = false;
		//修改的字段和值
		Map<String,Object> map = WechatConstant.returnWXUserDeviceValue(wxUserDeviceRelation);
		try {
			falg = commonDao.updateEntityById(wxUserDeviceRelation.getId(), map, WXUserDeviceRelation.class);
		} catch (Exception e) {
			logger.error("updateWXDevicer操作失败" + e);
		}
		return falg;
	}

	@Override
	public boolean checkIsBind(String opendid) throws Exception {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.OPEN_ID , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ opendid,0 });
			List<WXUserDeviceRelation> wxUserDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			result = (null != wxUserDeviceRelationList && wxUserDeviceRelationList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
			throw e;
		}

		return result;
	}

	@Override
	public List<WXUserDeviceRelation> getWXUserDeviceList(String opendid) throws Exception {
		List<WXUserDeviceRelation>  wxUserDeviceRelationList = null;
		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.OPEN_ID, DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ, Operator.EQ }, new Object[]{ opendid,0 });
			wxUserDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
		} catch (Exception e) {
			logger.error("操作失败", e);
			throw e;
		}
		return wxUserDeviceRelationList;
	}

	@Override
	public boolean checkIsExist(String mac) throws Exception {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MAC , DBConstants.Common.STATUS}, new Operator[]{ Operator.EQ,Operator.EQ }, new Object[]{ mac,0 });
			List<WXUserDeviceRelation> wxUserDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			result = (null != wxUserDeviceRelationList && wxUserDeviceRelationList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
			throw e;
		}

		return result;
	}

	@Override
	public WXUserDeviceRelation getWXUserDeviceById(long id) throws Exception {
		WXUserDeviceRelation wxUserDeviceRelation = null;

		try {
			Condition condition = new Condition(
					new String[]{DBConstants.Common.ID },
					new Operator[]{ Operator.EQ },
					new Object[]{ id });
			List<WXUserDeviceRelation> wxDeviceList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			if(null != wxDeviceList && wxDeviceList.size() > 0) {
				wxUserDeviceRelation = wxDeviceList.get(0);
			}
		} catch(Exception e) {
			logger.error("[WXDeviceServiceImpl.getWXUserDeviceById]执行出错.",e);
		}

		return wxUserDeviceRelation;
	}

	@Override
	public boolean checkIsExist(String mac, String opendid) {
		boolean result = false;

		try {
			Condition condition = new Condition(new String[]{DBConstants.Common.MAC , DBConstants.Common.STATUS, DBConstants.Common.OPEN_ID}, new Operator[]{ Operator.EQ,Operator.EQ ,Operator.EQ}, new Object[]{ mac,0,opendid });
			List<WXUserDeviceRelation> wxUserDeviceRelationList = commonDao.findEntityList(condition, null, WXUserDeviceRelation.class);
			result = (null != wxUserDeviceRelationList && wxUserDeviceRelationList.size() > 0);
		} catch (Exception e) {
			logger.error("操作失败", e);
		}

		return result;
	}
}
