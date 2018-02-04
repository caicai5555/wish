package com.foundation.controller.app;

import com.foundation.common.BaseController;
import com.foundation.common.bean.ResultModel;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/app/")
public class AppController extends BaseController {

    Logger logger = LoggerFactory.getLogger(AppController.class);

    /**
     * 初始化接口，返回版本等信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "init", method = {RequestMethod.GET})
    @ResponseBody
    public ResultModel init(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ResultModel result = new ResultModel();
        try {
            Map<String,String> map= Maps.newHashMap();
            map.put("test1","value1");
            map.put("test2","value2");
            result.setData(map);
        } catch (Exception e) {
            logger.error("AppController init method error", e);
            result.setTip("error");
        }
        return result;
    }

}