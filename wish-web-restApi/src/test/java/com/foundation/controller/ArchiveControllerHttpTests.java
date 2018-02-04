package com.foundation.controller;

import com.foundation.common.http.HttpClientUtils;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ArchiveControllerHttpTests {

    //String url="http://localhost:8080/archive/429006244";
    String url="http://restApi.mayg.com/archive/429006244";


    /*String timestamp=request.getParameter("timestamp");//时间戳
    String systemId=request.getParameter("systemId");//systemId：系统授权访问systemId
    String nonce=request.getParameter("nonce");//随机数-非重复的随机数值
    String signature=request.getParameter("signature");//加密签名
      String SystemId="yujian";
        String token="token_yungu@2016$@#$@!#$#%#sdsfd0-12312fcfe";
        String nonce="123456";
        String signature=getSinguture(token,nonce,timestamp);
        //421bc107f7e2a7a5a9aa7c0529bfa7e5
    */
    @Test
    public void getArchiveInfo() throws Exception {
        Map<String,String> params= Maps.newHashMap();
        params.put("systemId","yujian");
        params.put("nonce","123456");
        params.put("timestamp","1478749975421");
        params.put("signature","98f4ac9e5e898422a5963240f838b132");
        String result=HttpClientUtils.doPost(url,params);
        System.out.println(result);
    }



}
