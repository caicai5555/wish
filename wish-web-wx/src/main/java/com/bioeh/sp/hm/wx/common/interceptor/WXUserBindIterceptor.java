package com.bioeh.sp.hm.wx.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 查看报告及解读报告 判断用户是否绑定手机号
 */
public class WXUserBindIterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(WXUserBindIterceptor.class);
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		String openId = (String)request.getAttribute("openId");
		try {
			/*String requestURI = request.getRequestURI();
			//只拦截gene下的请求，解析请求地址
			String viewName = requestURI.substring(requestURI.lastIndexOf("/gene/") + 5);*/

			//检查用户是否绑定手机号 为了查询报告及解读报告使用
			if(checkBind(openId)) {
				return true;
			}
			StringBuffer bindPath = new StringBuffer("/WEB-INF/views/gene/reportBind.html?openId=" + openId );
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
	 * 查看用户是否绑定
	 * */
	public boolean checkBind(String openId) throws Exception{
		//TODO
		if(1==1) {
			return true;
		}
		return false;
	}
	
}
