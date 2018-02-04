package com.bioeh.sp.hm.wx.controller;

import com.bioeh.sp.hm.wx.base.TestConfigChangeHandle;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 微信硬件控制类
 * lz
 */

@Controller
@RequestMapping("/weixinHardWare")
    public class WeixinHardWareController {

    private static final Logger log = LoggerFactory.getLogger(WeixinHardWareController.class);


    @RequestMapping(value = "getTicket", method = RequestMethod.GET)
    @ResponseBody
    public String getTicket() {
        ApiConfig config = new ApiConfig(WechatConstant.WECHAT_APPID, WechatConstant.WECHAT_SECRET,true);
        //ApiConfig config = new ApiConfig(appid, secret);
        TestConfigChangeHandle configChangeHandle = new TestConfigChangeHandle();
        config.addHandle(configChangeHandle);
        String ticket = "";
        if(StrUtil.isNotBlank(config.getJsApiTicket())){
            ticket = config.getJsApiTicket();
            log.debug("getTicket:",config.getJsApiTicket());
        }

        return new StringBuffer("{\"ticket\":\"").append(ticket).append("\"}").toString();
    }



}


