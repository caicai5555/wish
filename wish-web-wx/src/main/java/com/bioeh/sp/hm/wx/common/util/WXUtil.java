package com.bioeh.sp.hm.wx.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bioeh.hm.redisClient.template.JedisTemplate;
import com.bioeh.hm.redisClient.util.RedisUtils;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.entity.CacheCodeModel;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.CollectionUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**   
* @ClassName:WechatUtil.java 
* @Function lz
*/
public class WXUtil {
	private static final Logger log = LoggerFactory.getLogger(WXUtil.class);
	static JedisTemplate template = RedisUtils.getTemplate();

	private static final char[] charArray = new char[] { '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static JSONObject getAccessTokenByCode(String code) {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("appid", WechatConstant.WECHAT_APPID));
    	params.add(new BasicNameValuePair("secret",WechatConstant.WECHAT_SECRET));
    	params.add(new BasicNameValuePair("code", code));
    	params.add(new BasicNameValuePair("grant_type", "authorization_code"));
    	return sendGetRequestAndParse(WechatConstant.GET_CODE_URL , params);
    }

	public static JSONObject sendGetRequestAndParse(String url , List<NameValuePair> params) {
		try {
			String responseText = RequestUtil.sendGETRequest(url, "utf-8" , params);
			return JSONObject.parseObject(responseText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

	/**
	 * 随机产生一个6位数的手机验证码
	 *
	 * @return
	 */
	public static String generalMobileVerifyCode() {
		String activeCode = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			activeCode = activeCode + charArray[random.nextInt(10)];
		}

		return activeCode;
	}
	/**
	 * 获取以毫秒为单位的当前时间。保留10位
	 *
	 * @param obj
	 * @return
	 */
	public static int getCurrentTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 获取当前时间戳
	 * @return
	 */
	public static long getCurrentTimeMilli() {
		return System.currentTimeMillis();
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * @param seconds 精确到毫秒的字符串
	 * @param formatStr 如timeStamp2Date("1477772772727","yyyy-MM-dd HH:mm:ss");
	 * @return
	 */
	public static String timeStamp2Date(String seconds,String format) {
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
			return "";
		}
		if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds)));
	}
	/**
	 * 日期格式字符串转换成时间戳
	 * @param date 字符串日期
	 * @param format 如：yyyy-MM-dd HH:mm:ss
	 * @return如 （"2015"）
	 */
	public static String date2TimeStamp(String date_str,String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缓存key
	 *
	 * @param request
	 * @return
	 */
	public static String getCacheKey(HttpServletRequest request, String prefix) {
		String openId = request.getParameter("openId");
		String mobile = request.getParameter("mobile");
		String cacheKey = prefix + StringUtils.defaultString(openId, "") + StringUtils.defaultString(mobile, "");
		if (StringUtils.isBlank(mobile))
			return DigestUtils.shaHex(cacheKey);
		else {
			return DigestUtils.shaHex(cacheKey + mobile);
		}
	}

	/**
	 * 空气猫数据缓存key
	 *
	 * @param cacheParam
	 * @return
	 */
	public static String getAirCatCacheKey(String cacheParam, String prefix) {
		log.info("cacheParam:"+ cacheParam);
		String cacheKey = prefix + cacheParam;
		return DigestUtils.shaHex(cacheKey);
	}
	/**
	 * 检查验证码是否正确
	 *
	 * @return
	 * @author guwenren
	 */
	public static JsonData checkCode(String cacheName, JsonData jsonData, String verificationNum) {
		String CacheCodeJsonStr = template.get(cacheName);
		log.info(String.format("obj[%s],mobileCode[%s]", CacheCodeJsonStr, verificationNum));
		if (!org.apache.commons.lang.StringUtils.isNotBlank(CacheCodeJsonStr)) {
			jsonData.setTip("验证码不正确");
			return jsonData;
		}
		CacheCodeModel ccm = null;
		net.sf.json.JSONObject obj = new net.sf.json.JSONObject().fromObject(CacheCodeJsonStr);//将json字符串转换为json对象
		//将json对象转换为java对象
		ccm = (CacheCodeModel) net.sf.json.JSONObject.toBean(obj, CacheCodeModel.class);//将建json对象转换为Person对象
		if (!verificationNum.equals(ccm.getValue())) {
			jsonData.setTip("手机号码与验证码不正确");
			return jsonData;
		}
		if ((WXUtil.getCurrentTime() - ccm.getTime()) > WechatConstant.MOBILE_CODE_OVERDUE_TIME) {
			jsonData.setTip("验证码已过期");
			return jsonData;
		}
		jsonData.setTip("success");
		return jsonData;
	}

	/**
	 * getRequestIp:获取请求IP. <br/>
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/**
	 * 当月第一天
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		return str.toString();

	}

	/**
	 * 当月最后一天
	 * @return
	 */
	public static String getLastDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s);
		return str.toString();

	}

	/**
	 * 根据一年第一天 和 对应的月份 获取当月的第一天和最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstday_Lastday_Month(Date date,int num) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num-1);
		Date theDate = calendar.getTime();

		//上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		day_first = str.toString();

		//上个月最后一天
		calendar.add(Calendar.MONTH,1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}

	//设备解绑
	//################1步授权获取  获取deviceid和二维码
	public static void compel_bind(ApiConfig config,String accessToken,String device_id,String openid) {
		String url = "https://api.weixin.qq.com/device/compel_unbind?access_token=#";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openid", openid);
		params.put("device_id", device_id);
		BaseResponse response = executePost(url, JSONUtil.toJson(params), accessToken);
		log.info( "compel_unbind:"+response.toJsonString());
	}

	/**
	 * 通用post请求
	 *
	 * @param url  地址，其中token用#代替
	 * @param json 参数，json格式
	 * @return 请求结果
	 */
	protected static BaseResponse executePost(String url, String json,String accessToken) {
		return executePost(url, json, null,accessToken);
	}
	/**
	 * 通用post请求
	 *
	 * @param url  地址，其中token用#代替
	 * @param json 参数，json格式
	 * @param file 上传的文件
	 * @return 请求结果
	 */
	protected static BaseResponse executePost(String url, String json, File file,String accessToken) {
		BaseResponse response;
		BeanUtil.requireNonNull(url, "url is null");
		List<File> files = null;
		if (null != file) {
			files = CollectionUtil.newArrayList(file);
		}
		//需要传token
		String postUrl = url.replace("#", accessToken);
		response = NetWorkCenter.post(postUrl, json, files);
		return response;
	}

}
