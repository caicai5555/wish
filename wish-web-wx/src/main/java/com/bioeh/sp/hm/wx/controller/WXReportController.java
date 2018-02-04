package com.bioeh.sp.hm.wx.controller;

import com.bioeh.hm.redisClient.template.JedisTemplate;
import com.bioeh.hm.redisClient.util.RedisUtils;
import com.bioeh.sp.hm.bsp.biz.usercenter.business.ISysUserBiz;
import com.bioeh.sp.hm.bsp.biz.usercenter.entity.SysUser;
import com.bioeh.sp.hm.bsp.util.MD5Util;
import com.bioeh.sp.hm.web.PropertyConfigurer;
import com.bioeh.sp.hm.wx.biz.IWXUserBiz;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.common.constant.wxPath;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.common.util.JsonUtil;
import com.bioeh.sp.hm.wx.common.util.MapKeyComparator;
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
import java.util.*;


/**
 * Created by liuzhen on 2016/6/23.
 * 报告controller
 */
@Controller
@RequestMapping("/wxGene")
public class WXReportController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(WXReportController.class);
    JedisTemplate template = RedisUtils.getTemplate();

    @Autowired
    private IWXUserBiz iwxUserBiz;

    /**
     * 用户信息业务层接口
     */
    @Autowired
    private ISysUserBiz sysUserBiz;





    /**
     * 查看报告
     * @return
     */
    @RequestMapping(value = "getReport", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            String openId = (String) request.getAttribute("openId");

            //下面主要解决ios手机 返回按钮 刷新页面 丢失openId情况start
            if(!StringUtils.isNotBlank(openId)){
                openId = template.get(WechatConstant.WX_CACHE_KEY_CODE + request.getParameter("code"));
            }
            //ios手机 返回按钮 刷新页面 丢失openId情况end

            WXUser wxUser = iwxUserBiz.getWXUserByOpenId(openId);
            if(wxUser!=null && StringUtils.isNotBlank(wxUser.getMobile())){
                String result = "";

                Map<String,Object> params = Maps.newHashMap();
                params.put("mobile",wxUser.getMobile());
                String serviceUrl = "/cmserver/rest/gene/wechat_oa/getSampleInfoListByMobile";
                String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

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
                return view(wxPath.WX_GET_REPORT_OPEN_VIEW);
            }
        }catch (Exception e){
            log.info("查看报告异常",e);
        }
        return view(wxPath.WX_REPORT_BIND_OPEN_VIEW);
    }
    /**
     * 根据检测编号获取报告列表
     * @return
     *  */
    @RequestMapping(value = "getReportList", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getReportList(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取前台传过来的aspid
            String aspid = getReqValByParam("aspid");
            String result = "";
            Map<String,Object> params = Maps.newHashMap();
            params.put("aspid",aspid);
            String serviceUrl = "/cmserver/rest/gene/wechat_oa/getReportListByAspId";
            String reportData = RequestUtil.getDataFromGene(params,serviceUrl);
            if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                JSONObject jsonObject = JSONObject.fromObject(reportData);
                if(jsonObject.get("data")  instanceof JSONNull){
                    //如果没有值 则定义一个null数组 让页面显示空白
                    result = "[]";
                }else{
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                    result =  jsonObject1.getJSONArray("reportList").toString();
                }
            }
            model.put("page",result);
        }catch (Exception e){
            log.info("根据检测编号获取报告列表",e);
        }
        return view(wxPath.WX_GET_REPORT_LIST_OPEN_VIEW);
    }
    /**
     * 根据检测编号获取样品流程（检查报告详情）
     * @return
     */
    @RequestMapping(value = "getSampleFlowChartByAspid", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getSampleFlowChartByAspid(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取前台传过来的aspid
            String aspid = getReqValByParam("aspid");
            Map<String,Object> params = Maps.newHashMap();
            params.put("aspid",aspid);
            String serviceUrl = "/cmserver/rest/gene/wechat_oa/getSampleFlowChart";
            String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

            String result = "";
            if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                JSONObject jsonObject = JSONObject.fromObject(reportData);
                if(null!=jsonObject && !jsonObject.isNullObject()){
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                    if(null!=jsonObject1 && !jsonObject1.isNullObject()){
                        result = jsonObject1.toString();
                    }else{
                        //如果没有值 则定义一个null数组 让页面显示空白
                        result = "[]";
                    }
                }
            }
            model.put("aspid",aspid);
            model.put("page",result);
        }catch (Exception e){
            log.info("获取样品流程",e);
        }
        return view(wxPath.WX_REPORT_DETAIL_OPEN_VIEW);
    }

    /**
     *根据检测编号和报告名查看报告
     * @return
     *   请求参数:
     * aspid=CS2015122801
     * reportName=CS2015122801.pdf
     *   token={"sysTime":"1470790746958","md5Str":"8a31d47c4d699c852125c16afa76f95f","type":"W"}
     */
    @RequestMapping(value = "viewReportByAspid", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewReportByAspid(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取前台传过来的aspid
            String aspid = getReqValByParam("aspid");
            String reportName = getReqValByParam("reportName");

            Map<String,Object> params = Maps.newHashMap();
            params.put("aspid",aspid);
            params.put("reportName",reportName);
            String serviceUrl = "/cmserver/rest/gene/wechat_oa/viewReport";
            String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

            String result = "";
            if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                JSONObject jsonObject = JSONObject.fromObject(reportData);
                if(null!=jsonObject && !jsonObject.isNullObject()){
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                    if(null!=jsonObject1 && !jsonObject1.isNullObject()){
                        result = jsonObject1.toString();
                    }else{
                        //如果没有值 则定义一个null数组 让页面显示空白
                        result = "[]";
                    }
                }
            }
            model.put("page",result);
        }catch (Exception e){
            log.info("获取样品流程",e);
        }
        return view(wxPath.WX_REPORT_DETAIL_OPEN_VIEW);
    }

    /**
     * 获取报告物流详情
     * @return
     *   expressComCode=yt
     *   expressNo=881443775034378914
     *   title=健康尊享卡号：CS2015122801
     *   geneSet=泰安慈铭基因套餐2000
     *   token={"sysTime":"1470792471030","md5Str":"6ee60868b61922520886b94682a6282f","type":"W"}
     */
    @RequestMapping(value = "getReportExpressByexpressNo", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getReportExpressByexpressNo(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取前台传过来的aspid
            String expressComCode = getReqValByParam("expressComCode");
            String expressNo = getReqValByParam("expressNo");
            String title = getReqValByParam("title");
            String geneSet = getReqValByParam("geneSet");
            Map<String,Object> params = Maps.newHashMap();
            params.put("expressComCode",expressComCode);
            params.put("expressNo",expressNo);
            params.put("title",title);
            params.put("geneSet",geneSet);
            String serviceUrl = "/cmserver/rest/gene/wechat_oa/getReportExpressInfo";
            String reportData = RequestUtil.getDataFromGene(params,serviceUrl);

            String result = "";
            if(StringUtils.isNotBlank(reportData) && !reportData.equals("null")){
                JSONObject jsonObject = JSONObject.fromObject(reportData);
                if(null!=jsonObject && !jsonObject.isNullObject()){
                    JSONObject jsonObject1 = (JSONObject) jsonObject.get("data");
                    if(null!=jsonObject1 && !jsonObject1.isNullObject()){
                        result = jsonObject1.toString();
                    }else{
                        //如果没有值 则定义一个null数组 让页面显示空白
                        result = "[]";
                    }
                }
            }
            model.put("page",result);
        }catch (Exception e){
            log.info("获取样品流程",e);
        }
        return view(wxPath.WX_LOGISTIC_PROGRESS_OPEN_VIEW);
    }



    /**
     * 绑定用户和报告信息
     *
     * @return
     */
    @RequestMapping(value = "bindReportUser", method = {RequestMethod.POST})
    public void bindReportUser() throws Exception {
        JsonData jsonData = new JsonData();
        //用户的openId
        String openId = getReqValByParam("openId");
        try {

            String mobile = getReqValByParam("mobile");
            if (mobile == null || mobile.length() < 11 || !mobile.matches(WechatConstant.REGEX_MOBILE)) {
                jsonData.setTip("手机号格式不正确");
                writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
                return;
            }

            //根据mobile判断是否存在 （主要是为了用户绑定时候判断 8.17 定的 一个手机号只能绑定一个微信号 如果需要绑定 需要先解绑）
            if(iwxUserBiz.checkIsExistByMobile(mobile)){
                jsonData.setTip("手机号已经绑定，请先解绑后再试");
                writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
                return;
            }

            String verificationNum = getReqValByParam("verificationNum");

            String cacheName = WXUtil.getCacheKey(getRequest(),WechatConstant.WX_CACHE_KEY);



            jsonData = WXUtil.checkCode(cacheName, jsonData, verificationNum);
            //如果验证失败 tip中包含有错误信息
            if(!"".equals(jsonData.getTip()) && !"success".equals(jsonData.getTip())){
                writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
                return;
            }

            SysUser user = null;
            Map<String,Object> params  =  Maps.newHashMap();
            params.put("mobile",mobile);
            //根据手机号获取在基因系统的用户
            List<SysUser> sysUserList = sysUserBiz.selectSysUserList(params);

            if (sysUserList != null && !sysUserList.isEmpty()) {
                user = sysUserList.get(0);
            } else {
                jsonData.setTip("手机号不存在");
                writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
                return;
            }


            //如果用户不存在微信用户 直接插入一条用户信息数据 已经在我们系统存在用户 则只需要更新手机号

            if(iwxUserBiz.checkIsExist(openId)) {
                WXUser wxUser = iwxUserBiz.getWXUserByOpenId(openId);
                wxUser.setMobile(mobile);
                wxUser.setUid((int)user.getPkId());
                iwxUserBiz.updateWXUser(wxUser);
            }else{
                WXUser wxUser = new WXUser();
                wxUser.setMobile(mobile);
                wxUser.setUid((int)user.getPkId());
                wxUser.setStatus(0);
                wxUser.setCreateDate(new Date());
                wxUser.setOpenid(openId);
                iwxUserBiz.addWXUser(wxUser);
            }
        } catch (Exception e) {
            log.error("绑定用户和报告信息", e);
        }
        model.put("openId", openId);
        writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
        return;
    }

    /**
     * 检查验证码是否正确
     *
     * @return
     * @author guwenren
     *//*
    public boolean checkCode(String cacheName, JsonData jsonData, String verificationNum) {

        String CacheCodeJsonStr = template.get(cacheName);
        log.info(String.format("obj[%s],mobileCode[%s]", CacheCodeJsonStr, verificationNum));
        if (!StringUtils.isNotBlank(CacheCodeJsonStr)) {
            jsonData.setTip("验证码不正确");
            writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
            return false;
        }
        CacheCodeModel ccm = null;
        JSONObject obj = new JSONObject().fromObject(CacheCodeJsonStr);//将json字符串转换为json对象
        //将json对象转换为java对象
        ccm = (CacheCodeModel)JSONObject.toBean(obj,CacheCodeModel.class);//将建json对象转换为Person对象
        if (!verificationNum.equals(ccm.getValue())) {
            jsonData.setTip("手机号码与验证码不正确");
            writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
            return false;
        }
        if ((WXUtil.getCurrentTime() - ccm.getTime()) > WechatConstant.MOBILE_CODE_OVERDUE_TIME) {
            jsonData.setTip("验证码已过期");
            writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
            return false;
        }
        return true;
    }*/

    /**
     * 跳转解绑页面
     *
     * @return
     */
    @RequestMapping(value = "toUnBind", method = {RequestMethod.GET})
    public ModelAndView toUnBind() throws Exception {
        //用户的openId
        String openId = getReqValByParam("openId");
        try {
            WXUser wxUser = iwxUserBiz.getWXUserByOpenId(openId);
            if(null!=wxUser){
                model.put("mobile",wxUser.getMobile());
            }
        } catch (Exception e) {
            log.error("跳转解绑页面", e);
        }
        model.put("openId",openId);
        return view(wxPath.WX_REPORT__UN_BIND_OPEN_VIEW);
    }
    /**
     * 解绑用户关系
     *
     * @return
     */
    @RequestMapping(value = "unBind", method = {RequestMethod.POST})
    public void unBind() throws Exception {
        JsonData jsonData = new JsonData();
        try {
            //用户的openId
            String openId = getReqValByParam("openId");

            String verificationNum = getReqValByParam("verificationNum");

            String cacheName = WXUtil.getCacheKey(getRequest(),WechatConstant.WX_CACHE_KEY);



            jsonData = WXUtil.checkCode(cacheName, jsonData, verificationNum);
            //如果验证失败 tip中包含有错误信息
            if(!"".equals(jsonData.getTip()) && !"success".equals(jsonData.getTip())){
                writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
                return;
            }

            WXUser wxUser = iwxUserBiz.getWXUserByOpenId(openId);
            if(wxUser!=null){
                wxUser.setMobile("");
                wxUser.setUid(0);
                iwxUserBiz.updateWXUser(wxUser);
            }
        } catch (Exception e) {
            log.error("绑定用户和报告信息", e);
            jsonData.setTip("解绑失败，请稍后再试");
            writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
        }
        writeJson(com.bioeh.sp.hm.bsp.util.JsonUtil.toJsonString(jsonData));
    }

}


