package com.bioeh.sp.hm.wx.common.interceptor;

import com.bioeh.sp.hm.wx.biz.IWXDeviceBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 查看空气猫数据拦截器
 */
public class WXAirCatIterceptor extends HandlerInterceptorAdapter {

	/**
	 * 设备业务层接口
	 */
	@Autowired
	private IWXDeviceBiz wxDeviceBiz;


	private static final Logger log = LoggerFactory.getLogger(WXAirCatIterceptor.class);
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		String openId = (String)request.getAttribute("openId");
		try {
			/*String requestURI = request.getRequestURI();
			//只拦截gene下的请求，解析请求地址
			String viewName = requestURI.substring(requestURI.lastIndexOf("/gene/") + 5);*/

			//检查用户是有空气猫
			if(checkBind(openId)) {
				return true;
			}
			/*StringBuffer bindPath = new StringBuffer("/WEB-INF/views/airCat/airCatBind.html?openId=" + openId );*/
			//没有绑定设备 直接跳转到绑定页面
			StringBuffer bindPath = new StringBuffer("/wxAirCat/toBind");
			request.setAttribute("openId",openId);
			//转到需要跳转的页面
			log.info("bindPath"+bindPath);
			request.getRequestDispatcher(bindPath.toString()).forward(request, response);
		} catch (Exception e) {
			//转发到错误页面
			//response.sendRedirect(request.getContextPath() + "/gene/error");
		}
		return false;
	}

	/*
	 * 查看用户是否绑定设备
	 * */
	public boolean checkBind(String openId) throws Exception{

		/*if(wxDeviceBiz.checkIsBind(openId)) {
			return true;
		}*/
		return false;
	}
	
}
