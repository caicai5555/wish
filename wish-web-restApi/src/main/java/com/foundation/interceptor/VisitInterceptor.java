package com.foundation.interceptor;

import com.foundation.cache.redis.JedisTemplate;
import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.Contants;
import com.foundation.common.date.DateUtils;
import com.foundation.common.security.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 接口访问安全拦截
 * Created by fqh on 2015/12/10.
 */
public class VisitInterceptor implements HandlerInterceptor {

    private JedisTemplate template = RedisUtils.getTemplate();
    Logger logger = LoggerFactory.getLogger(VisitInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        try {
            logger.info(request.getRequestURI() + "===================>调用开始" + DateUtils.getNowTime(DateUtils.DATE_LONG_STR));
            logger.info("===================>preHandle");
            String timestamp = request.getParameter("timestamp");//时间戳
            String systemId = request.getParameter("systemId");//systemId：系统授权访问systemId
            String nonce = request.getParameter("nonce");//随机数-非重复的随机数值
            String signature = request.getParameter("signature");//加密签名

            if (StringUtils.isBlank(timestamp) ||
                    StringUtils.isBlank(systemId) ||
                    StringUtils.isBlank(nonce) ||
                    StringUtils.isBlank(signature)) {

                write(response, Contants.MISMATCHINGPARAM);//参数不齐整
                return false;
            }
            if (!Contants.AllowSystem.isContainsSystem(systemId)) {
                write(response, Contants.UNAUTHORIZED);//未授权访问
                return false;
            }
            if (!signature.equals(getSignature(systemId, timestamp, nonce))) {
                write(response, Contants.HAVETAMPER);//不允许访问
                return false;
            }
        } catch (Exception e) {
            logger.error("VisitInterceptor 请求信息出错", e);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("==afterCompletion===" + o);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        logger.info(httpServletRequest.getRequestURI() + "===================>调用结束" + DateUtils.getNowTime(DateUtils.DATE_LONG_STR));
        logger.info("==afterCompletion===");
        if (e != null) {//记录错误日志
            logger.info("============有异常信息================" + handler + "===" + e.getMessage());
            logger.error(handler + "");
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 回显消息
     *
     * @param response
     * @param content
     * @throws Exception
     */
    private void write(HttpServletResponse response, String content) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(content);//参数不齐整
    }


    /**
     * 计算规则
     * 客户端、服务端签名，设定一个密钥,只有服务调用者跟服务提供者知道，
     * 调用者调用服务时，服务提供者把参数进行升序排序，然后再加上时间戳跟密钥，
     * 用md5进行加密，并把加密结果串连同参数一起传输给调用者，调用者根据相同规则也生成密钥串跟提供者的密钥串进行比对，
     * 相同的继续访问，不一致的说明接口参数有可能是被篡改过，对这种请求进行拦截处理。
     */
    private String getSignature(String systemId, String timestamp, String nonce) {
        String token = Contants.AllowSystem.getSystemToken(systemId);
        String md5 = token + timestamp + nonce;
        char[] md5Arr = md5.toCharArray();
        Arrays.sort(md5Arr);
        String md5Str = MD5Util.MD5(new String(md5Arr));
        return md5Str;
    }

}
