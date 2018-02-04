package com.bioeh.sp.hm.wx.controller;

import com.bioeh.hm.redisClient.template.JedisTemplate;
import com.bioeh.hm.redisClient.util.RedisUtils;
import com.bioeh.sp.hm.bsp.biz.send.business.ISendInfoBiz;
import com.bioeh.sp.hm.bsp.biz.usercenter.business.ISysUserBiz;
import com.bioeh.sp.hm.bsp.biz.usercenter.entity.SysUser;
import com.bioeh.sp.hm.bsp.util.JsonUtil;
import com.bioeh.sp.hm.web.PropertyConfigurer;
import com.bioeh.sp.hm.wx.biz.IWXUserBiz;
import com.bioeh.sp.hm.wx.common.config.Global;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.common.util.WXUtil;
import com.bioeh.sp.hm.wx.common.web.BaseController;
import com.bioeh.sp.hm.wx.entity.CacheCodeModel;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.github.sd4324530.fastweixin.api.TemplateMsgAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.TemplateMsg;
import com.github.sd4324530.fastweixin.api.entity.TemplateParam;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by liuzhen on 2016/7/15.
 */
@Controller
@RequestMapping("/public")
    public class PublicController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PublicController.class);

    /**
     * 用户信息业务层接口
     */
    @Autowired
    private ISysUserBiz sysUserBiz;

    JedisTemplate template = RedisUtils.getTemplate();
    private JsonData jsonData = null;

    @Autowired
    private ISendInfoBiz sendInfoBiz;

    @Autowired
    private IWXUserBiz iwxUserBiz;


    ApiConfig config = new ApiConfig(WechatConstant.WECHAT_APPID, WechatConstant.WECHAT_SECRET);

    /**
     * 发送短信验证码
     * @return
     */
    @RequestMapping(value = "sendMessage", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonData sendMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
            jsonData = new JsonData();
            String mobile = getReqValByParam("mobile");
            if (mobile == null || mobile.length() < 11 || !mobile.matches(WechatConstant.REGEX_MOBILE)) {
                jsonData.setTip("手机号格式不正确");
                return jsonData;
            }
            //根据手机号获取在基因系统的用户
            SysUser user = null;
            Map<String,Object> params  =  Maps.newHashMap();
            params.put("mobile",mobile);
            List<SysUser> sysUserList = sysUserBiz.selectSysUserList(params);

            if (sysUserList != null && !sysUserList.isEmpty()) {
                user = sysUserList.get(0);
            } else {
                //输入手机号在基因系统不存在 直接return
                jsonData.setTip("手机号不存在");
                jsonData.setData("");
                return jsonData;
            }

            Integer cur_time = WXUtil.getCurrentTime();
            CacheCodeModel ccm = null;

            String cacheName = WXUtil.getCacheKey(getRequest(), WechatConstant.WX_CACHE_KEY);

            String CacheCodeJsonStr = template.get(cacheName);

            //生成短信验证码
            String mobileCode = WXUtil.generalMobileVerifyCode(); // 验证码

            if(StringUtils.isNotBlank(CacheCodeJsonStr)){
                JSONObject obj = new JSONObject().fromObject(CacheCodeJsonStr);//将json字符串转换为json对象
                //将json对象转换为java对象
                ccm = (CacheCodeModel)JSONObject.toBean(obj,CacheCodeModel.class);//将建json对象转换为Person对象
                int extime = cur_time - ccm.getTime();
                if (extime < WechatConstant.sendMobileCodeOutTime_Mobile) {
                    jsonData.setTip("请在" + (WechatConstant.sendMobileCodeOutTime_Mobile - extime) + "秒后再重新获取验证码！");
                    return jsonData;
                } else {
                    ccm.setTime(cur_time);
                    ccm.setValue(mobileCode);
                }
            }else{
                ccm = new CacheCodeModel();
                ccm.setKey(cacheName);
                ccm.setTime(cur_time);
                ccm.setValue(mobileCode);
            }

            String msg = "本次验证码是：" + mobileCode + "。若非本人操作请忽略此条信息，给您带来不便，望请见谅。";
            int sendResult = sendInfoBiz.sendSMSNew(mobile, msg);

            template.setex(cacheName + mobile, mobileCode, 1800);
            template.setex(cacheName, JsonUtil.toJson(ccm), 3600);

            if (Global.isTest()) {
                jsonData.setData(mobileCode);
            }
            if (0 == sendResult) {
                log.info("手机号(" + mobile + ")发送短信失败");
                jsonData.setTip("短信发送失败");
            } else {
                //发送成功
                jsonData.setTip("success");
                jsonData.setCode(0);
            }
        }catch (Exception e){
            log.info("获取短信验证码失败",e);
            jsonData.setTip("获取短信验证码失败");
        }
        return jsonData;
    }

    /**
     * 发送报告已出具模板消息
     * report Already issued
     * @return
     */
    @RequestMapping(value = "sendReportAlreadyIssued", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonData sendReportAlreadyIssued(HttpServletRequest request, HttpServletResponse response) {
        try {
            jsonData = new JsonData();
            TemplateMsgAPI templateMsgAPI = new TemplateMsgAPI(config);
            String mobile = getReqValByParam("mobile");

            String aspId = getReqValByParam("aspId");

            String title = getReqValByParam("title");


            if (mobile == null || mobile.length() < 11 || !mobile.matches(WechatConstant.REGEX_MOBILE)) {
                jsonData.setTip("手机号不正确");
                return jsonData;
            }
            //根据手机号获取在基因系统的用户
            SysUser user = null;
            Map<String,Object> params  =  Maps.newHashMap();
            params.put("mobile",mobile);
            List<SysUser> sysUserList = sysUserBiz.selectSysUserList(params);

            if (sysUserList != null && !sysUserList.isEmpty()) {
                user = sysUserList.get(0);
            } else {
                //输入手机号在基因系统不存在 直接return
                jsonData.setTip("手机号不存在");
                return jsonData;
            }

            WXUser wxUser = iwxUserBiz.getWXUserByMobile(mobile);
            //获取用户对应的openId
            if(wxUser != null){

                //配置具体短信内容
                TemplateMsg msg = new TemplateMsg();
                msg.setTemplateId(WechatConstant.SEND_REPORT_ALREADY_ISSUED_ID);
                msg.setTopcolor("#0000EE");
                msg.setTouser(wxUser.getOpenid());
                msg.setUrl(PropertyConfigurer.getProperty("interface.wx.data.url") + "/wxGene/getReport?openId=" + wxUser.getOpenid() );
                Map<String, TemplateParam> data = new HashMap<String, TemplateParam>();

                //健康卡号aspId
                TemplateParam templateAspId = new TemplateParam();
                templateAspId.setValue(aspId);
                templateAspId.setColor("#0000EE");
                data.put("aspId", templateAspId);

                //健康卡号title
                TemplateParam templateTitle = new TemplateParam();
                templateTitle.setValue(title);
                templateTitle.setColor("#0000EE");
                data.put("title", templateTitle);


                msg.setData(data);
                templateMsgAPI.send(msg);
            }
        }catch (Exception e){
            log.info("发送报告已出具模板消息",e);
            jsonData.setTip("发送报告已出具模板消息失败");
        }
        return jsonData;
    }

    /**
     * 发送报告已寄送模板消息
     * report Already Send
     * @return
     */
    @RequestMapping(value = "sendReportAlreadySend", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonData sendReportAlreadySend(HttpServletRequest request, HttpServletResponse response) {
        jsonData = new JsonData();
        try {
            TemplateMsgAPI templateMsgAPI = new TemplateMsgAPI(config);
            String mobile = getReqValByParam("mobile");
            //健康卡号
            String aspId = getReqValByParam("aspId");
            //标题
            String title = getReqValByParam("title");
            //物流公司如圆通
            String expressCompany = getReqValByParam("expressCompany");

            //以下四个是为了拼接参数 查看物流报告信息使用
            //物流单号如560394078013
            String expressNo = getReqValByParam("expressNo");
            //物流公司简称如yt
            String expressComCode = getReqValByParam("expressComCode");

            //expresstitle
            String expresstitle = getReqValByParam("expresstitle");

            //geneSet
            String geneSet = getReqValByParam("geneSet");
            if (mobile == null || mobile.length() < 11 || !mobile.matches(WechatConstant.REGEX_MOBILE)) {
                jsonData.setTip("手机号格式不正确");
                return jsonData;
            }
            //根据手机号获取在基因系统的用户
            SysUser user = null;
            Map<String,Object> params  =  Maps.newHashMap();
            params.put("mobile",mobile);
            List<SysUser> sysUserList = sysUserBiz.selectSysUserList(params);

            if (sysUserList != null && !sysUserList.isEmpty()) {
                user = sysUserList.get(0);
            } else {
                //输入手机号在基因系统不存在 直接return
                jsonData.setTip("手机号不存在");
                return jsonData;
            }

            WXUser wxUser = iwxUserBiz.getWXUserByMobile(mobile);
            //获取用户对应的openId
            if(wxUser != null){


                //配置具体短信内容
                TemplateMsg msg = new TemplateMsg();
                msg.setTemplateId(WechatConstant.SEND_REPORT_ALREADY_SEND_ID);
                msg.setTopcolor("#0000EE");
                msg.setTouser(wxUser.getOpenid());
                msg.setUrl(PropertyConfigurer.getProperty("interface.wx.data.url") + "/wxGene/getReportExpressByexpressNo?expressComCode="+expressComCode+"&expressNo="+expressNo + "&title=" + expresstitle + "&geneSet="+geneSet);
                Map<String, TemplateParam> data = new HashMap<String, TemplateParam>();

                //健康卡号aspId
                TemplateParam templateAspId = new TemplateParam();
                templateAspId.setValue(aspId);
                templateAspId.setColor("#0000EE");
                data.put("aspId", templateAspId);

                //健康卡号title
                TemplateParam templateTitle = new TemplateParam();
                templateTitle.setValue(title);
                templateTitle.setColor("#0000EE");
                data.put("title", templateTitle);

                //物流公司
                TemplateParam templateExpressCompany = new TemplateParam();
                templateExpressCompany.setValue(expressCompany);
                templateExpressCompany.setColor("#0000EE");
                data.put("expressCompany", templateExpressCompany);

                //物流单号
                TemplateParam templateExpressNo = new TemplateParam();
                templateExpressNo.setValue(expressNo);
                templateExpressNo.setColor("#0000EE");
                data.put("expressNo", templateExpressNo);


                msg.setData(data);
                templateMsgAPI.send(msg);
            }
        }catch (Exception e){
            log.info("sendReportAlreadySend",e);
            jsonData.setTip("发送报告已寄送模板消息失败");
        }
        return jsonData;
    }

    /**
     * 发送解读师提交解读确认模板消息
     * custom Service Read
     * @return
     */
    @RequestMapping(value = "sendCustomServiceRead", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonData sendCustomServiceRead(HttpServletRequest request, HttpServletResponse response) {
        jsonData = new JsonData();
        try {
            TemplateMsgAPI templateMsgAPI = new TemplateMsgAPI(config);
            String mobile = getReqValByParam("mobile");
            //解读时间和解读方式
            String unscrambleDate = getReqValByParam("unscrambleDate");
            String unscrambleType = getReqValByParam("unscrambleType");
            //解读医师的名字
            String doctorName = getReqValByParam("doctorName");

            //查看详情使用
            String aspId = getReqValByParam("aspId");
            log.info("mobile="+mobile + "=====unscrambleDate="+unscrambleDate +"===aspId======"+aspId+"===orderReadReportId===="+"==unscrambleType====="+unscrambleType);

            //根据手机号获取在基因系统的用户
            WXUser wxUser = iwxUserBiz.getWXUserByMobile(mobile);
            //获取用户对应的openId
            if(wxUser != null){

                //配置具体短信内容
                TemplateMsg msg = new TemplateMsg();
                msg.setTemplateId(WechatConstant.SEND_CUSTOM_SERVICE_READ_ID);
                msg.setTopcolor("#0000EE");
                msg.setTouser(wxUser.getOpenid());
                msg.setUrl(PropertyConfigurer.getProperty("interface.wx.data.url") + "/explanation/getExplanationSuccess?aspId="+aspId);
                Map<String, TemplateParam> data = new HashMap<String, TemplateParam>();

                //解读时间
                TemplateParam templateUnscrambleDate = new TemplateParam();
               // templateUnscrambleDate.setValue(WXUtil.timeStamp2Date(unscrambleDate,""));
                templateUnscrambleDate.setValue(unscrambleDate);
                templateUnscrambleDate.setColor("#0000EE");
                data.put("unscrambleDate", templateUnscrambleDate);

                //解读方式
                TemplateParam templateUnscrambleType = new TemplateParam();
                templateUnscrambleType.setValue(unscrambleType);
                templateUnscrambleType.setColor("#0000EE");
                data.put("unscrambleType", templateUnscrambleType);

                //解读医师的名字
                TemplateParam templateDoctorName = new TemplateParam();
                templateDoctorName.setValue(doctorName);
                templateDoctorName.setColor("#0000EE");
                data.put("doctorName", templateDoctorName);

                msg.setData(data);
                templateMsgAPI.send(msg);
            }
        }catch (Exception e){
            log.info("发送客服提交解读确认模板消息",e);
            jsonData.setTip("发送客服提交解读确认模板消息");
        }
        return jsonData;
    }

}


