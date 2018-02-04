package com.foundation.console.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: MethodTimeAspect 
* @Description: 使用Aspect统计方法调用的时间
* @author chengchen 
* @date 2016年11月2日 上午8:47:40 
*
 */
@Aspect  
@Component  
public class MethodTimeAspect {  
	Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *   
    * @Title: logServiceMethodAccess 
    * @Description: 统计方法调用的时间 
    * @author chengchen
    * @date 2016年11月2日 上午8:51:56 
    * @param @param joinPoint
    * @param @return
    * @param @throws Throwable    设定参数 
    * @return Object    返回类型 
    * @throws
     */
    @Around("execution(* com.foundation.service.evaluate.biz..*Biz.*(..))")  
    public Object methodTimeAccess(ProceedingJoinPoint joinPoint) throws Throwable {  
        long start = System.currentTimeMillis();  
        Object object = joinPoint.proceed();  
        long end = System.currentTimeMillis();  
        long t = end - start;  
        if(t>=1000){  
            String tmp = joinPoint.getSignature().toString();  
            logger.info(String.format("class:%s,invoke_time:%s",tmp,t));  
        }  
        return object;  
    }  
}  
