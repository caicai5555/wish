package com.bioeh.sp.hm.wx.common.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户点击按钮做公共处理
 */
public class WXCommonIterceptor extends HandlerInterceptorAdapter {
	

	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String openId = (String)request.getAttribute("openId");
		try {
			String requestURI = request.getRequestURI();
			//只拦截index下的请求，解析请求地址
			String viewName = requestURI.substring(requestURI.lastIndexOf("/index/") + 7);
			//如果进入投资列表页面无需登录
			if("invested".equals(viewName)){
				return true;
			}

			//转到需要跳转的页面
			response.sendRedirect("");
		} catch (Exception e) {
			//转发到错误页面
			response.sendRedirect(request.getContextPath() + "/index/error?errorcode=0");
		}
		return false;
	}
	
}
