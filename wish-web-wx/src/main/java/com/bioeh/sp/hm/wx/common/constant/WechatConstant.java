package com.bioeh.sp.hm.wx.common.constant;

import com.bioeh.sp.hm.wx.entity.WXDevice;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.bioeh.sp.hm.wx.entity.WXUserDeviceRelation;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liuzhen
 *
 */
public class WechatConstant {
	//**********************************上线可能需要修改的地方start*********************************

	//测试微信appid
	public static final String WECHAT_APPID = "wx5c4fa87a21a15282";

	//测试微信secret
	public static final String WECHAT_SECRET = "b43522ad9b3d188764fd1bc10f0c5d96";

	//发送模板消息 对应模板id

	//发送报告已出具模板消息 模板id
	public static final String SEND_REPORT_ALREADY_ISSUED_ID = "miLgOv32K-k58woBvw01XMgC28UJD21QmXKfWE8zXmw";
	//发送报告已寄送模板消息 模板id
	public static final String SEND_REPORT_ALREADY_SEND_ID = "3cp6Qz4EESA2eLNv2jnO30D05cmmCZNoI4iq16ZDrMU";
	//发送解读师提交解读确认模板消息 模板id
	public static final String SEND_CUSTOM_SERVICE_READ_ID = "n3RjnpTM2ceJl3NLXzSqVClNCSrffinrWJxtztTgE9I";
	//**********************************上线可能需要修改的地方end*********************************

	public static final String GET_CODE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";//二次认证授权

	public static Integer sendMobileCodeOutTime_Mobile = 60;// 发送手机验证码间隔

	/**
	 * 正则表达式：验证手机号
	 */
	public static final String REGEX_MOBILE = "^((\\+?86)|(\\(\\+86\\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$";
	/**
	 * 短信验证码正则
	 */
	public static final String VERIFICATION_NUM = "^((\\+?86)|(\\(\\+86\\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|17[02356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$";

	// 微信通用缓存key
	public static final String WX_CACHE_KEY = "wx_cache_key";

	//缓存key 缓存微信code 获取openid
	public static final String WX_CACHE_KEY_CODE = "wx_cache_key_code";

	//微信获取小时数据缓存key
	public static final String WX_CACHE_AIRCAT_DATA= "wx_cache_aircat_data";

	// 验证码过期时间
	public static final Integer MOBILE_CODE_OVERDUE_TIME = 1800; // 30分钟


	/**
	 * 设备修改字段
	 *
	 * @param wxDevice
	 * @return
	 */
	public static Map<String, Object> returnWXDeviceValue(WXDevice wxDevice) {
		Map<String, Object> map = new HashMap<String, Object>();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		//map.put("create_date", df.format(new Date()));// new Date()为获取当前系统时间
		if (StringUtils.isNotBlank(wxDevice.getMac())) {
			map.put("mac", wxDevice.getMac());
		}
		if (wxDevice.getStatus() > 0) {
			map.put("status", wxDevice.getStatus());
		}
		if (wxDevice.getIsPublic() >= 0) {
			map.put("is_public", wxDevice.getIsPublic());
		}
		return map;
	}
	/**
	 * 用户和设备关系修改字段
	 *
	 * @param wxUserDeviceRelation
	 * @return
	 */
	public static Map<String, Object> returnWXUserDeviceValue(WXUserDeviceRelation wxUserDeviceRelation) {
		Map<String, Object> map = new HashMap<String, Object>();
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		//map.put("create_date", df.format(new Date()));// new Date()为获取当前系统时间
		if (StringUtils.isNotBlank(wxUserDeviceRelation.getMac())) {
			map.put("mac", wxUserDeviceRelation.getMac());
		}
		if (StringUtils.isNotBlank(wxUserDeviceRelation.getPosition())) {
			map.put("position", wxUserDeviceRelation.getPosition());
		}
		if (wxUserDeviceRelation.getStatus() > 0) {
			map.put("status", wxUserDeviceRelation.getStatus());
		}
		return map;
	}
	/**
	 * 用户修改字段
	 *
	 * @param wxUser
	 * @return
	 */
	public static Map<String, Object> returnWXUserValue(WXUser wxUser) {


		Map<String, Object> map = new HashMap<String, Object>();

		//有可能出现更新用户手机号为null 如解绑用户 和uid
		map.put("mobile", wxUser.getMobile());
		map.put("uid", wxUser.getUid());
		if(wxUser.getStatus() > 0){
			map.put("status", wxUser.getStatus());
		}
		if(StringUtils.isNotBlank(wxUser.getOpenid())){
			map.put("openid", wxUser.getOpenid());
		}
		return map;
	}
}
