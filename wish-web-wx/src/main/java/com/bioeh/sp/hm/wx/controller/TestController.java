package com.bioeh.sp.hm.wx.controller;

import com.bioeh.sp.hm.wx.common.constant.wxPath;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.common.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by liuzhen on 2016/6/23.
 */
@Controller
@RequestMapping("/testApi")
    public class TestController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @RequestMapping(value = "testresp", method = RequestMethod.GET)
    @ResponseBody
    public JsonData getTestresp() {
        JsonData response = new JsonData();
        response.setData("xxxxx");

        return response;
    }

}


