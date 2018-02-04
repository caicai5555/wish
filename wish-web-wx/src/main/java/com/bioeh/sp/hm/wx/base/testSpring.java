package com.bioeh.sp.hm.wx.base;

import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zl on 2016/8/6.
 * desc:
 */
@Controller
@RequestMapping("/testwwww")
public class testSpring {
    @RequestMapping(value = "testresp", method = RequestMethod.GET)
    @ResponseBody
    public JsonData getTestresp() {
        JsonData response = new JsonData();
        response.setData("xxxxx");

        return response;
    }
}