package com;

import com.foundation.common.date.DateUtils;
import com.foundation.common.security.MD5Util;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author fqh
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
public class VisitTest {
/*
    String timestamp=request.getParameter("timestamp");//时间戳
    String systemId=request.getParameter("systemId");//systemId：系统授权访问systemId
    String nonce=request.getParameter("nonce");//随机数-非重复的随机数值
    String signature=request.getParameter("signature");//加密签名*/
    @Test
    public void createVisitRule(){
        long timestamp= DateUtils.nowDateToTimestamp();
        String SystemId="yujian";
        String token="token_yungu@2016$@#$@!#$#%#sdsfd0-12312fcfe";
        String nonce="123456";
        String signature=getSinguture(token,nonce,timestamp);
        //421bc107f7e2a7a5a9aa7c0529bfa7e5
        System.out.println(timestamp+"|---|"+signature);
    }


    /**
     * 获取signature
     * @param token
     * @param nonce
     * @param timestamp
     * @return
     */
    public String getSinguture(String token,String nonce,long timestamp){
        String md5=token+timestamp+nonce;
        char[] md5Arr=md5.toCharArray();
        Arrays.sort(md5Arr);
        String signature= MD5Util.MD5(new String(md5Arr));
        return signature;
    }
}
