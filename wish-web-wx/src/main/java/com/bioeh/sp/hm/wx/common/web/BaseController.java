/**
 * 
 */
package com.bioeh.sp.hm.wx.common.web;

import com.bioeh.sp.hm.bsp.constant.ServiceConstants;
import com.bioeh.sp.hm.bsp.util.SystemInitUtil;
import com.bioeh.sp.hm.utils.StringUtil;
import com.bioeh.sp.hm.web.PropertyConfigurer;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseController{

	private final static Log logger = LogFactory.getLog(BaseController.class);

	public static final String PARAM_USERNAME = "username";
	/** 当前请求 */
	private HttpServletRequest request;

	/** 当前响应 */
	private HttpServletResponse response;

	private RedirectAttributes redirectAttributes;

	public HttpSession session;

	static final int default_Page_size = 10;// 默认分页的长度

	private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

	public static final Map<String, Object> model = new ConcurrentHashMap<String, Object>();

	public static final ApiConfig config = new ApiConfig(WechatConstant.WECHAT_APPID, WechatConstant.WECHAT_SECRET);

	/**
	 * 系统初始化
	 */
	static {
		try {
			//SystemInitUtil.init();
		} catch (Exception e) {
			logger.error("系统初始化失败。", e);
		}
	}
	@ModelAttribute
	public void setRerAndRes(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		model.clear();
		threadLocal.set(model);
		this.request = request;
		this.response = response;
		this.redirectAttributes = redirectAttributes;
		this.session = request.getSession();
	}


	/**
	 * @Description: <p> 从当前请求中获得参数值(单个)，封装框架中的beat.getRequest().getParameter(param) </p> 如果为空则返回空串
	 * @param param
	 * @return String
	 * @throws
	 */
	protected String getReqValByParam(String param) {
		String value = getRequest().getParameter(param);
		return (value == null) ? "" : value.trim();
	}

	/**
	 * <p>
	 * 从当前请求中获得参数值(数组)，封装中的getRequest().getParameterValues(param)
	 * </p>
	 * @param param
	 * @return
	 */
	protected String[] getReqValsByParam(String param) {
		return getRequest().getParameterValues(param);
	}

	/**
	 * @Description: 批量获取请求的参数名
	 * @return Enumeration<String>
	 * @throws
	 */
	protected Enumeration<String> getReqNames() {
		return getRequest().getParameterNames();
	}

	/**
	 * <p>增加属性值，封装中的getModel().add(attributeName, attributeValue)方法</p>
	 * @param attributeName 属性名
	 * @param attributeValue 属性值
	 */
	protected void addModel(String attributeName, Object attributeValue) {
		model.put(attributeName, attributeValue);
		// velocity使用Servlet以后会从request，session中获取MODEL数据
		getRequest().setAttribute(attributeName, attributeValue);
	}

	/**
	 * 获得分页每页显示数量
	 * @return
	 */
	protected int getPageSize() {
		int pageSize = StringUtil.parseInteger(getReqValByParam("pageSize").trim());
		pageSize = pageSize > 0 ? pageSize : 10;
		getRequest().setAttribute("pageSize", pageSize);
		return pageSize;
	}

	/**
	 * 获得分页页码
	 * @return
	 */
	protected int getPageNo() {
		int pageNo = StringUtil.parseInteger(getReqValByParam("pageNo").trim());
		pageNo = pageNo > 0 ? pageNo : 1;
		getRequest().setAttribute("pageNo", pageNo);
		return pageNo;
	}

	/**
	 * @Description: 组装模型
	 * @param viewPath
	 * @param response
	 * @return ModelAndView
	 * @throws
	 */
	protected ModelAndView view(String viewPath) {
		response.setDateHeader("If-Modified-Since", System.currentTimeMillis());
		addCtxPathParamModel();
		//addOpenIdParamModel();
		//addCookie();
		return new ModelAndView(viewPath, model);
	}

	/**
	 * @Description: 转发到
	 * @param viewPath
	 * @param response
	 * @return ModelAndView
	 * @throws
	 */
	protected ModelAndView forwardView(String viewPath) {
		return new ModelAndView("forward:" + viewPath, model);
	}

	/**
	 * @Description: 重定向到 或者不带参数带参数重定向,带参数重定向，直接将参数存入model中
	 * @param viewPath
	 * @param response
	 * @return ModelAndView
	 * @throws
	 */
	protected ModelAndView redirectView(String viewPath) {
		if (model.size() > 0) {
			redirectAttributes.addAllAttributes(model);
		}
		return new ModelAndView("redirect:" + viewPath, model);
	}

	/**
	 * @Description:向客户端write
	 * @param message
	 * @param response
	 * @return void
	 * @throws
	 */
	protected void writeJson(Object message) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(message.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("向客户端写错误。", e);
		}
	}

	/**
	 * 获得客户端ip地址
	 * @return
	 */
	protected String getClientIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("X-Real-IP");//"x-forwarded-for"

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.indexOf(",") >= 0) {
			return ip.split(",")[0].trim();
		} else {
			return ip;
		}
	}

	/**
	 * @Description:判断是否为ajax操作
	 * @return boolean
	 * @throws
	 */
	protected boolean isAjax() {
		String ajax = getRequest().getHeader("Request-Type");
		return "ajax".equalsIgnoreCase(ajax);
	}

	/**
	 * @Description:获取当前线程中的请求request
	 * @return HttpServletRequest
	 * @throws
	 */
	public HttpServletRequest getRequest() {
		request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * @Description: 获取当前线程中的请求response
	 * @return HttpServletResponse
	 * @throws
	 */
	public HttpServletResponse getResponse() {
		ServletWebRequest servletWebRequest = new ServletWebRequest(getRequest());
		servletWebRequest.getResponse();
		return response;
	}

	/**
	 * @Description: 获取上下文路径
	 * @return String
	 * @throws
	 */
	public String getCtxPath() {
		String ctxPath = getRequest().getContextPath();
		return ctxPath;
	}

	/**
	 * @Description:添加完整的域名+上下文路径
	 * @throws
	 */
	public void addCtxPathParamModel() {
		model.put("ctxPath", getAppURL() + getCtxPath());
	}
	/**
	 * @Description:添加openId
	 * @throws
	 */
	public void addOpenIdParamModel() {
		model.put("openId", request.getAttribute("openId"));
	}

	/**
	 * @Description: 返回当前应用的域名
	 * @return String
	 * @throws
	 */
	public String getAppURL() {
		return PropertyConfigurer.getProperty("app.url");
	}

    /**
     * 上传文件至文件服务器路径
     * @return
     */
    public String uploadFileUrl(){
    	return PropertyConfigurer.getProperty("uploadfile.service.url");
    }
    
	/**
	 * 返回文件服务器域名
	 * @return
	 */
	public String getFileServerUrl(){
		return PropertyConfigurer.getProperty("file.service.url");
	}
	

	/**
	 * 获取项目发布路径
	 * @return String 项目发布路径
	 * @see [类、类#方法、类#成员]
	 */
	protected String getRealPath() {
		String realPath = request.getSession().getServletContext().getRealPath(ServiceConstants.SpecialSymbol.OBLIQUE_LINE) + ServiceConstants.SpecialSymbol.OBLIQUE_LINE;
		return realPath;
	}
	
	/**
	 * 获取项目跟路径最后以“/结尾”
	 * 如：D:\domains\base_domain\autodeploy\项目名称\
	 * @return
	 */
	protected String getProjectPath() {
		String path = session.getServletContext().getRealPath("/");
		if ("\\".equals(File.separator)) {// windows
			path = path.replace("/", "\\")+ File.separator;
		}
		if ("/".equals(File.separator)) {// linux
			path = path.replace("\\", "/");
		}
		return path;
	}
	
}
