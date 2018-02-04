package com.bioeh.sp.hm.wx.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.bioeh.hm.redisClient.template.JedisTemplate;
import com.bioeh.hm.redisClient.util.RedisUtils;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.common.util.WXUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *根据code获取openid
 * @author lz
 */
public class GetOpenIdInterceptor extends HandlerInterceptorAdapter {
	JedisTemplate template = RedisUtils.getTemplate();
	/**
	 * 拦截code获取openid
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		Map map=request.getParameterMap();
		Set keSet=map.entrySet();
		for(Iterator itr=keSet.iterator();itr.hasNext();){
			Map.Entry me=(Map.Entry)itr.next();
			Object ok=me.getKey(); //获取参数名
			Object ov=me.getValue(); //获取参数值
			String[] value=new String[1];
			if(ov instanceof String[]){
				value=(String[])ov;
			}else{
				value[0]=ov.toString();
			}
			for(int k=0;k <value.length;k++){
				System.out.println(ok+"="+value[k]);
			}
		}

		/*String openId = null;
		String code = request.getParameter("code");
		//如果code不为空，说明是从微信菜单进来的
		if(StringUtils.isNotBlank(code)){
			JSONObject obj = WXUtil.getAccessTokenByCode(code);
			openId = obj.getString("openid");
		} else {
			openId = request.getParameter("openId");
		}
		request.setAttribute("openId", openId);*/
		String openId = null;
		String code = request.getParameter("code");

		if(StringUtils.isNotBlank(code)){
			openId = template.get(request.getParameter("code"));
		}
		if(!StringUtils.isNotBlank(openId)){
			//如果code不为空，说明是从微信菜单进来的
			if(StringUtils.isNotBlank(code)){
				JSONObject obj = WXUtil.getAccessTokenByCode(code);
				openId = obj.getString("openid");
				if(StringUtils.isNotBlank(openId)){
					template.setex(WechatConstant.WX_CACHE_KEY_CODE + code,openId , 1800);
				}
			} else {
				openId = request.getParameter("openId");
			}


		}
		request.setAttribute("openId", openId);

		//8.24 解决ios 微信 返回按钮重新加载数据的bug
		/*List<String> params = new ArrayList<String>();
		if(StringUtils.isNotBlank(openId)){
			params.add("openId="+openId);
		}
		StringBuffer paramsStr = new StringBuffer(request.getRequestURL() + "?");
		for(int i=0,max=params.size();i<max;i++){
			if(i == max-1){
				paramsStr.append(params.get(i));
			} else {
				paramsStr.append(params.get(i) + "&");
			}
		}
		String url = paramsStr.toString();
		request.getRequestDispatcher(url).forward(request,response);*/
		//redis

		return true;
	}
}

