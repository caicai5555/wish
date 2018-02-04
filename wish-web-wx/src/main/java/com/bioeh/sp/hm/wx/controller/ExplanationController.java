package com.bioeh.sp.hm.wx.controller;

import com.bioeh.sp.hm.bsp.biz.usercenter.business.ISysUserBiz;
import com.bioeh.sp.hm.wx.biz.IWXUserBiz;
import com.bioeh.sp.hm.wx.common.constant.wxPath;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.common.util.RequestUtil;
import com.bioeh.sp.hm.wx.common.util.WXUtil;
import com.bioeh.sp.hm.wx.common.web.BaseController;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.google.common.collect.Maps;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Created by liuzhen on 2016/8/19.
 * 预约解读controller
 */
@Controller
@RequestMapping("/explanation")
public class ExplanationController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ExplanationController.class);

    @Autowired
    private IWXUserBiz iwxUserBiz;

    /**
     * 用户信息业务层接口
     */
    @Autowired
    private ISysUserBiz sysUserBiz;

    /**
     * 打开预约解读
     * @return
     */
    @RequestMapping(value = "openExplanation", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView openExplanation(HttpServletRequest request, HttpServletResponse response) {
        try {
            String openId = (String) request.getAttribute("openId");
            WXUser wxUser = iwxUserBiz.getWXUserByOpenId(openId);
            //判断是否绑定基因系统手机号
            if(wxUser!=null && StringUtils.isNotBlank(wxUser.getMobile())){
                String result = "";

                Map<String,Object> params = Maps.newHashMap();
                params.put("mobile",wxUser.getMobile());
                String serviceUrl = "/cmserver/rest/gene/wechat_oa/getSampleInfoListByMobile";
                String reportData = RequestUtil.getDataFromGene(params, serviceUrl);

                if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){

                    JSONObject jsonObject = JSONObject.fromObject(reportData);
                    if(jsonObject.get("data")  instanceof JSONNull){
                        //如果没有值 则定义一个null数组 让页面显示空白
                        result = "[]";
                    }else{
                        JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                        JSONArray jsonArray =  jsonObject1.getJSONArray("sampleList");
                        result = jsonArray.toString();
                    }
                }

                model.put("page",result);
                model.put("mobile",wxUser.getMobile());
                return view(wxPath.WX_EXPLANATION_OPEN_VIEW);
            }
        }catch (Exception e){
            log.info("打开预约解读",e);
        }
        return view(wxPath.WX_REPORT_BIND_OPEN_VIEW);
    }
    /**
     * 提交预约解读
     * @return
     */
    @RequestMapping(value = "submitExplanation", method = RequestMethod.POST)
    @ResponseBody
    public JsonData submitExplanation(HttpServletRequest request, HttpServletResponse response) {
        JsonData jsonData = new JsonData();
        try {
            //预约姓名
            String name = getReqValByParam("name");
            String openId =getReqValByParam("openId");
            //预约手机号
            String mobile = getReqValByParam("mobile");
            //预约时间
            String unscrambleDate = getReqValByParam("unscrambleDate");
            //预约人关系
            String relationship = getReqValByParam("relationship");

            //预约解读的aspId
            String aspId = getReqValByParam("aspId");

            //String[] geneSet=getReqValByParam("geneSet").split(","); //根据名字获得checkbox的值，注意是getParameters，后面要加上s，因为是一个数组
            log.info("name=="+name + "===openId==" + openId + "====mobile==="+mobile+"=====unscrambleDate===="+unscrambleDate+"====relationship=====+"+relationship+"===aspId==="+aspId);

            if(!StringUtils.isNotBlank(aspId)){
                jsonData.setTip("检测套餐为null");
                return jsonData;
            }
            //新建预约解读信息
            //接口参数必须传入 说明aspid 检测编号;username;预约人;phone_number 手机号;expect_date 期望解读时间（时间戳）token 检验令牌
            //;service_way 服务方式 false;sex 性别 false；date_of_birth；出生日期（时间戳）false；special_requirements 备注；
            //reportListener 解读方式;reportListenerName 报告听取人姓名;reportListenerRelation ;与报告听取人关系
            //reportListenerMobile 报告听取人电话

            Map<String,Object> params = Maps.newHashMap();
            params.put("aspid",aspId);
            params.put("username",name);
            params.put("phone_number",mobile);
            params.put("expect_date", WXUtil.date2TimeStamp(unscrambleDate,"yyyy-MM-dd HH:mm:ss"));
            //添加预约来源 目前order_read_report tips来源 kf(客服手动预约) null（代表APP预约） wx（微信预约） 三种
            params.put("tips","wx");

            String serviceUrl = "/cmserver/rest/gene/wechat_oa/newReportReadApply";
            String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

            String result = "";
            if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                JSONObject jsonObject = JSONObject.fromObject(reportData);
                if(null!=jsonObject && !jsonObject.isNullObject()){
                    jsonData.setTip((String) jsonObject.get("message"));
                }
            }

        }catch (Exception e){
            log.info("提交预约解读",e);
            jsonData.setTip("预约解读失败");
        }
        return jsonData;
    }

    /**
     *获取预约解读成功的信息
     * @return
     */
    @RequestMapping(value = "getExplanationSuccess", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getExplanationSuccess(HttpServletRequest request, HttpServletResponse response) {
        try {
            //基因检测编码
            String aspId = getReqValByParam("aspId");

            if(StringUtils.isNotBlank(aspId)){
                Map<String,Object> params = Maps.newHashMap();
                params.put("aspid",aspId);

                String serviceUrl = "/cmserver/rest/gene/wechat_oa/getReportReadDetail";
                String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

                String result = "";
                if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                    JSONObject jsonObject = JSONObject.fromObject(reportData);
                    if(null!=jsonObject && !jsonObject.isNullObject()){
                        if(jsonObject.get("data").toString().equals("null")){
                            result = "[]";
                        }else{
                            result =  jsonObject.get("data").toString();
                        }
                    }else{
                        result = "[]";
                    }
                }
                model.put("page",result);
            }

        }catch (Exception e){
            log.info("查看预约解读", e);
        }
        return view(wxPath.WX_EXPLANATION_SUCCESS_OPEN_VIEW);
    }

}


