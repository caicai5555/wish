package com.bioeh.sp.hm.wx.controller;

import com.bioeh.hm.redisClient.template.JedisTemplate;
import com.bioeh.hm.redisClient.util.RedisUtils;
import com.bioeh.sp.hm.bsp.util.http.HttpClientUtils;
import com.bioeh.sp.hm.dal.ICommonDao;
import com.bioeh.sp.hm.web.PropertyConfigurer;
import com.bioeh.sp.hm.wx.biz.IWXDeviceBiz;
import com.bioeh.sp.hm.wx.biz.IWXDeviceRelationBiz;
import com.bioeh.sp.hm.wx.biz.IWXUserBiz;
import com.bioeh.sp.hm.wx.biz.IWXUserDeviceRelationBiz;
import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.bioeh.sp.hm.wx.common.constant.wxPath;
import com.bioeh.sp.hm.wx.common.persistence.JsonData;
import com.bioeh.sp.hm.wx.common.util.JsonUtil;
import com.bioeh.sp.hm.wx.common.util.RequestUtil;
import com.bioeh.sp.hm.wx.common.util.WXUtil;
import com.bioeh.sp.hm.wx.common.web.BaseController;
import com.bioeh.sp.hm.wx.entity.WXDevice;
import com.bioeh.sp.hm.wx.entity.WXDeviceRelation;
import com.bioeh.sp.hm.wx.entity.WXUser;
import com.bioeh.sp.hm.wx.entity.WXUserDeviceRelation;
import com.bioeh.sp.hm.wx.service.IWXUserDeviceRelationService;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.google.common.collect.Maps;
import net.sf.json.JSONArray;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 空气猫控制类
 */
@Controller
@RequestMapping("/wxAirCat")
    public class WXAirCatController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(WXAirCatController.class);
    JedisTemplate template = RedisUtils.getTemplate();
    //果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1,四核比较正常，所以设置线程池为5
    final ExecutorService poolExecutor = Executors.newFixedThreadPool(5);

    @Autowired
    private IWXDeviceBiz iwxDeviceBiz;

    @Autowired
    private IWXDeviceRelationBiz iwxDeviceRelationBiz;

    @Autowired
    private IWXUserDeviceRelationBiz iwxUserDeviceRelationBiz;

    @Autowired
    private IWXUserBiz iwxUserBiz;

    private JsonData jsonData = null;

    /**
     * 查看用户空气猫数据
     * @return
     */
    @RequestMapping(value = "getAirCatData", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAirCatData(HttpServletRequest request, HttpServletResponse response) {
        String openId = (String) request.getAttribute("openId");
        //下面主要解决ios手机 返回按钮 刷新页面 丢失openId情况start
        if(!StringUtils.isNotBlank(openId)){
            openId = template.get(WechatConstant.WX_CACHE_KEY_CODE + request.getParameter("code"));
            log.info("葱reids中取的opoendi"+openId);
        }
        try {
            //检查用户是有空气猫
            if (checkBind(openId)) {
                //获取用户的mac地址
                List<WXUserDeviceRelation> wxUserDeviceRelationList = iwxUserDeviceRelationBiz.getWXUserDeviceList(openId);
                List<String> airCatData = new ArrayList<String>();
                for(WXUserDeviceRelation wxUserDeviceRelation : wxUserDeviceRelationList){
                    //发送post请求获取空气猫数据 并传送到页面
                    //json格式如 {"ch2":0.05999999865889549,"pollutionLevel":74.0,"tvoc":1.1699999570846558,"power":8,"mac":"5c:cf:7f:86:0a:45","pm25":49.599998474121094,"humity":50.900001525878906,"testTime":1470432857649,"temperature":28.5,"ctime":1470432857649,"id":"57a506590cf22a3aa70e5504"}
                    String data = getAirCatDataFromClouds(wxUserDeviceRelation.getMac());
                    Map<String,Object> params  =  Maps.newHashMap();
                    if(StringUtils.isNotBlank(data) && !data.equals("null")){
                        JSONObject jsonObject = JSONObject.fromObject(data);

                        //monitor/aircat/status
                        //发送post请求获取空气猫状态
                        //json格式如 {"data":"offline"} 或者 {"data":"online"}
                        String airCatStatus = getAirCatStatusFromClouds(wxUserDeviceRelation.getMac());

                        params.put("pm25",jsonObject.get("pm25").toString());//pm2.5 PM2.5（μg/m3）
                        params.put("humity",jsonObject.get("humity").toString());//humity 湿度（%RH）
                        params.put("temperature",jsonObject.get("temperature").toString());//temperature 温度(℃)
                        params.put("tvoc",jsonObject.get("tvoc").toString());//tvoc TVOC(总挥发性有机物)
                        params.put("ch2",jsonObject.get("ch2").toString());//甲醛含量
                        params.put("power",jsonObject.get("power".toString()));//空气猫电量
                        params.put("pollutionLevel",jsonObject.get("pollutionLevel").toString());//pollutionLevel 污染指数
                        params.put("mac",jsonObject.get("mac").toString());//mac 设备的MAC地址
                        params.put("position",wxUserDeviceRelation.getPosition().toString());//position 设备的位置
                        params.put("airCatStatus",airCatStatus.toString());//airCatStatus 设备的状态 online:在线；offline:离线
                        airCatData.add(JsonUtil.mapToJson(params));
                    }else{
                        //查不到空气猫数据的
                        log.info("查询不到数据mac地址:"+wxUserDeviceRelation.getMac());
                        params.put("pm25", "0.0");//pm2.5 PM2.5（μg/m3）
                        params.put("humity","0.0");//humity 湿度（%RH）
                        params.put("temperature","0.0");//temperature 温度(℃)
                        params.put("tvoc","0.0");//tvoc TVOC(总挥发性有机物)
                        params.put("ch2","0.0");//甲醛含量
                        params.put("power","0.0");//空气猫电量
                        params.put("pollutionLevel","0.0");//pollutionLevel 污染指数
                        params.put("mac",wxUserDeviceRelation.getMac().toString());//mac 设备的MAC地址
                        params.put("position",wxUserDeviceRelation.getPosition().toString());//position 设备的位置
                        params.put("airCatStatus","offline");//airCatStatus 设备的状态 online:在线；offline:离线
                        airCatData.add(JsonUtil.mapToJson(params));
                    }
                }

                model.put("page",airCatData);
                model.put("openID",openId);
                return view(wxPath.WX_AIR_CAT_OPEN_VIEW);
            }
        }catch (Exception e) {
            //转发到错误页面
            log.info("getAirCatData error" + "查看用户空气猫数据异常");
        }
        //没有绑定设备 直接跳转到绑定页面
        return view(wxPath.WX_AIR_CAT_BIND_OPEN_VIEW);
    }

    /**
     * 查看用户空气猫数据
     * @return
     */
    @RequestMapping(value = "toBind", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelAndView toBind(HttpServletRequest request, HttpServletResponse response) {
        String openId = request.getParameter("openId");
        log.info("toBind"+openId);
        model.put("openId",openId);
        return view(wxPath.WX_AIR_CAT_BIND_OPEN_VIEW);
    }

    /**
     * 添加空气猫设备
     * @return
     */
    @RequestMapping(value = "addDevice", method = RequestMethod.POST)
    @ResponseBody
    public JsonData addDevice(HttpServletRequest request, HttpServletResponse response) {
        try {
            jsonData = new JsonData();
            // 获取前台传过来的mac地址
            String mac = getReqValByParam("mac");
            //设备位置信息
            String position = getReqValByParam("position");
            //用户的openId
            String openId = getReqValByParam("openId");

            //判断微信用户是否存在
            if(!iwxUserBiz.checkIsExist(openId)){
                WXUser wxUser = new WXUser();
                wxUser.setCreateDate(new Date());
                wxUser.setOpenid(openId);
                wxUser.setStatus(0);

                iwxUserBiz.addWXUser(wxUser);
            }

            //判断设备信息是否存在
            if(!iwxDeviceBiz.checkIsExist(mac)){
                // 保存mac 及位置信息
                WXDevice wxDevice = new WXDevice();
                wxDevice.setMac(mac);
                wxDevice.setStatus(0);
                //暂时类型设置为空气猫P6
                wxDevice.setType(1);
                wxDevice.setCreateDate(new Date());
                wxDevice.setIsPublic(0);
                iwxDeviceBiz.addDevice(wxDevice);
            }
            if(!iwxUserDeviceRelationBiz.checkIsExist(mac,openId)) {
                log.info("用户第一次绑定设备================"+mac +"=========" + openId);

                WXUser wxUser =  iwxUserBiz.getWXUserByOpenId(openId);

                WXDevice wxDevice  = iwxDeviceBiz.getWXDeviceByMac(mac);
                //判断该设备是否允许其他设备绑定 0代表允许  1代表不允许
                if(wxDevice!=null&&wxDevice.getIsPublic()==1){
                    jsonData.setTip("该设备不允许其他微信绑定，请联系设备拥有者");
                    return jsonData;
                }


                WXUserDeviceRelation wxUserDeviceRelation = new WXUserDeviceRelation();
                wxUserDeviceRelation.setOpenId(openId);
                wxUserDeviceRelation.setPosition(position);
                wxUserDeviceRelation.setMac(mac);
                wxUserDeviceRelation.setStatus(0);
                wxUserDeviceRelation.setCreateDate(new Date());

                //9.5确认 不管手动还是扫码 第一次绑定的为主账号 拥有是否可以让别的微信号绑定权限
                if(!iwxUserDeviceRelationBiz.checkIsExist(mac)){
                    log.info("该设备绑定用户为主账户==============="+mac +"=========" + openId);
                    //说明是首次绑定 设置为主账号
                    wxUserDeviceRelation.setPrimaryAccount(0);
                }else{
                    wxUserDeviceRelation.setPrimaryAccount(1);
                }


                wxUserDeviceRelation.setWxDeviceId(wxDevice!=null?wxDevice.getId():null);
                wxUserDeviceRelation.setUserId(wxUser != null ? wxUser.getId():null);

                iwxUserDeviceRelationBiz.addWXUserDeviceRelation(wxUserDeviceRelation);

            }else{
                log.info("用户绑定设备信息已经存在================"+mac +"=========" + openId);
                jsonData.setTip("您已经绑定该设备");
                return jsonData;
            }
        } catch (Exception e) {
            jsonData.setTip("绑定设备失败");
            log.error("操作失败", e);
            return jsonData;
        }

        return jsonData;
    }

    /**
     *获取用户所有的空气猫设备
     * @return
     */
    @RequestMapping(value = "listWXDevice", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ModelAndView listWXDevice(HttpServletRequest request, HttpServletResponse response) {
        try {
            //用户的openId
            String openId = getReqValByParam("openId");

            List<WXUserDeviceRelation> wxUserDeviceRelationList = iwxUserDeviceRelationBiz.getWXUserDeviceList(openId);
            List<String> airCatData = new ArrayList<String>();

            for(WXUserDeviceRelation wxUserDeviceRelation : wxUserDeviceRelationList){
                //发送post请求获取空气猫数据 并传送到页面
                //json格式如 {"ch2":0.05999999865889549,"pollutionLevel":74.0,"tvoc":1.1699999570846558,"power":8,"mac":"5c:cf:7f:86:0a:45","pm25":49.599998474121094,"humity":50.900001525878906,"testTime":1470432857649,"temperature":28.5,"ctime":1470432857649,"id":"57a506590cf22a3aa70e5504"}
                Map<String,Object> params  =  Maps.newHashMap();

                //monitor/aircat/status
                //发送post请求获取空气猫状态
                //json格式如 {"data":"offline"} 或者 {"data":"online"}
                String airCatStatus = getAirCatStatusFromClouds(wxUserDeviceRelation.getMac());
                params.put("airCatStatus",JSONObject.fromObject(airCatStatus));//airCatStatus 空气猫状态
                params.put("position",wxUserDeviceRelation.getPosition());//airCatStatus 空气猫状态
                params.put("id",wxUserDeviceRelation.getId());//空气猫主键
                params.put("openId",wxUserDeviceRelation.getOpenId());
                params.put("mac",wxUserDeviceRelation.getMac());

                airCatData.add(JsonUtil.mapToJson(params));
            }
            model.put("page",airCatData);
            model.put("openId",openId);
        } catch (Exception e) {
            log.error("操作失败", e);
        }
        return view(wxPath.WX_AIR_CAT_LIST_OPEN_VIEW);
    }
    /**
     *打开修改空气猫设备页面
     * @return
     */
    @RequestMapping(value = "openUpdateWXDevice", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView openUpdateWXDevice(HttpServletRequest request, HttpServletResponse response) {
        try {
            //用户空气猫设备主键
            String id = getReqValByParam("id");

            WXDevice wxDevice = null;

            WXUserDeviceRelation wxUserDeviceRelation = iwxUserDeviceRelationBiz.getWXUserDeviceById(Long.parseLong(id));
            if(wxUserDeviceRelation!=null){
                wxDevice  = iwxDeviceBiz.getWXDeviceByMac(wxUserDeviceRelation.getMac());
            }
            //将设备信息和用户绑定的设备信息都查出来  一个是为了显示设备信息 判断是否是主账号
            model.put("wxDevice",wxDevice);
            model.put("page",JsonUtil.object2json(wxUserDeviceRelation));
            model.put("openId",getReqValByParam("openId"));
            model.put("wxUserDeviceRelation",wxUserDeviceRelation);
        } catch (Exception e) {
            log.error("操作失败", e);
        }
        return view(wxPath.WX_UPDATE_AIR_CAT_OPEN_VIEW);
    }

    /**
     *修改空气猫设备页面
     * @return
     */
    @RequestMapping(value = "updateWXDevice", method = RequestMethod.POST)
    @ResponseBody
    public JsonData updateWXDevice(HttpServletRequest request, HttpServletResponse response) {
        try {
            jsonData = new JsonData();
            // 获取前台传过来的mac地址
            String mac = getReqValByParam("mac");
            //设备位置信息
            String position = getReqValByParam("position");
            //用户的openId
            String openId = getReqValByParam("openId");

            String isPublic = getReqValByParam("isPublic");

            //用户空气猫设备主键
            String deviceId = getReqValByParam("deviceId");
            //用户绑定设备表id
            String id = getReqValByParam("id");

            WXDevice wxDevice = iwxDeviceBiz.getWXDeviceById(Long.parseLong(deviceId));
            wxDevice.setIsPublic("on".equals(isPublic)?0:1);
            wxDevice.setMac(mac);
            iwxDeviceBiz.updateDevice(wxDevice);

            WXUserDeviceRelation wxUserDeviceRelation = iwxUserDeviceRelationBiz.getWXUserDeviceById(Long.parseLong(id));

            wxUserDeviceRelation.setMac(mac);
            wxUserDeviceRelation.setPosition(position);
            iwxUserDeviceRelationBiz.updateWXUserRelationDevice(wxUserDeviceRelation);

            model.put("openId",openId);
        } catch (Exception e) {
            jsonData.setTip("更新设备失败");
            log.error("操作失败", e);
            return jsonData;
        }
        return jsonData;
    }
    /**
     *删除空气猫设备页面
     * @return
     */
    @RequestMapping(value = "deleteWXUserDeviceRelation", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteWXUserDeviceRelation(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取前台传过来的设备主键id地址
            String id   = getReqValByParam("id");

            //用户的openId
            final String openId = getReqValByParam("openId");
           final WXUserDeviceRelation wxUserDeviceRelation = iwxUserDeviceRelationBiz.getWXUserDeviceById(Long.parseLong(id));

            wxUserDeviceRelation.setStatus(1);//设备状态0代表可以使用 1代表不可使用
            iwxUserDeviceRelationBiz.updateWXUserRelationDevice(wxUserDeviceRelation);

            model.put("openId",openId);

            //删除mac地址 告诉微信 强制解绑  否则会出现删除之后 扫码绑定不成功情况

            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    WXDeviceRelation wxDeviceRelation = null;
                    try {
                        wxDeviceRelation = iwxDeviceRelationBiz.getWXDeviceRelationByMac(wxUserDeviceRelation.getMac());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(wxDeviceRelation!=null){
                        WXUtil.compel_bind(config, config.getAccessToken(), wxDeviceRelation.getDeviceId(), openId);
                    }
                }
            });

        } catch (Exception e) {
            log.error("操作失败", e);
        }
        return redirectView(wxPath.GET_AIRCATVIEW);
    }

    /****************************历史数据start***********************************************/
    /**
     *获取空气猫历史数据数据 加载首页默认加载第一个mac地址对应的 每日数据
     * @return
     */
    @RequestMapping(value = "getAirCatDayData", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getAirCatDayData(HttpServletRequest request, HttpServletResponse response) {
        String openId = getReqValByParam("openId");
        if(!StringUtils.isNotBlank(openId)){
            openId = template.get(WechatConstant.WX_CACHE_KEY_CODE + request.getParameter("code"));
            log.info("葱reids中取的opoendi"+openId);
        }
        try {
            //获取用户的mac地址
            List<WXUserDeviceRelation> wxUserDeviceRelationList= iwxUserDeviceRelationBiz.getWXUserDeviceList(openId);

            if(wxUserDeviceRelationList!=null && !wxUserDeviceRelationList.isEmpty()){
                WXUserDeviceRelation wxUserDeviceRelation = wxUserDeviceRelationList.get(0);

                String data = getAirCatHistoryDataFromClouds("month",wxUserDeviceRelation.getMac(),WXUtil.getFirstDay(),WXUtil.getLastDay());
                model.put("page",data);
                model.put("openId",openId);
                model.put("wxUserDeviceRelationList",JsonUtil.object2json(wxUserDeviceRelationList));
                model.put("mac",wxUserDeviceRelation.getMac());
                return view(wxPath.WX_HISTORY_AIR_CAT_OPEN_VIEW);
            }else{
                log.info("openId"+openId + "无可用的空气猫");
                return view(wxPath.WX_AIR_CAT_BIND_OPEN_VIEW);
            }


        }catch (Exception e) {
            //转发到错误页面
            log.info("getAirCatHourData error" + "查看用户空气猫数据异常" + e);
            e.printStackTrace();
        }
        //没有绑定设备 直接跳转到绑定页面
        model.put("openId",openId);
        return view(wxPath.WX_AIR_CAT_BIND_OPEN_VIEW);
    }

    /**历史数据
     *获取空气猫对应的数据
     * 参数类型type和mac地址
     *
     * @return
     */
    @RequestMapping(value = "getHistoryDataByTypeAndMac", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JsonData getHistoryDataByTypeAndMac(HttpServletRequest request, HttpServletResponse response) {
        jsonData = new JsonData();
        String type = getReqValByParam("type");
        String mac = getReqValByParam("mac");
        String beginDate = getReqValByParam("beginDate");
        String endDate = getReqValByParam("endDate");
        try {
            String data = getAirCatHistoryDataFromClouds(type, mac,beginDate,endDate);
            jsonData.setData(data);
        }catch (Exception e) {
            jsonData.setTip("获取历史数据异常");
            log.info("geHistoryDataByTypeAndMac error" + "获取空气猫历史数据对应的数据"+e);
        }
        //没有绑定设备 直接跳转到绑定页面
        return jsonData;
    }

    /****************************历史数据ewnd***********************************************/


     /*
	 * 查看用户是否绑定设备
	 *
	 */
    public boolean checkBind(String openId) throws Exception{

        if(iwxUserDeviceRelationBiz.checkIsBind(openId)) {
            return true;
        }
        return false;
    }

    /**
     * 根据设备mac地址 获取空气猫数据
     * @param mac
     * @return
     * @throws Exception
     */
    private String getAirCatDataFromClouds(String mac) throws Exception{
        //获取集团提供的空气猫数据url
        String serviceUrl = PropertyConfigurer.getProperty("interface.airCat.data.url") + "/restservice/monitor/aircat/latest";
        Map<String,Object> params = Maps.newHashMap();
        params.put("mac", mac);
        Map<String,Object> map = createReqMessage(params);
        String data = RequestUtil.sendRequest(serviceUrl,map);
        return data;
    }

    /**
     *根据mac地址获取空气猫状态
     * @param mac
     * @return
     */
    private String getAirCatStatusFromClouds(String mac) throws Exception{
        //获取集团提供的空气猫数据url
        String serviceUrl = PropertyConfigurer.getProperty("interface.airCat.data.url") + "/restservice/monitor/aircat/status";
        Map<String,Object> params = Maps.newHashMap();
        params.put("mac", mac);
        Map<String,Object> map = createReqMessage(params);
        String data = RequestUtil.sendRequest(serviceUrl,map);
        return data;
    }

    /**
     * 根据空气猫mac和type获取对应的数据
     * @param mac
     * @return
     * @throws Exception
     */
    private String getAirCatHistoryDataFromClouds(String type, String mac,String beginDate ,String endDate) throws Exception{
        //放入请求参数
        Map<String,Object> request_params = Maps.newHashMap();
        request_params.put("mac", mac);

            /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            params.put("endDate", df.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            params.put("beginDate", df.format(calendar.getTime()));*/
        request_params.put("beginDate",beginDate);
        request_params.put("endDate",endDate);
        String serviceUrl = "";
        //获取空气猫数据url host
        String common_url =  PropertyConfigurer.getProperty("interface.airCat.data.url");
        Map<String,Object> map = null;
        if("month".equals(type)){
            //获取集团提供的空气猫数据url 根据设备mac地址 获取空气猫hour数据
            serviceUrl = common_url + "/restservice/monitor/aircat/day";
            map = createReqMessage(request_params);
        }else if("hour".equals(type)){
            //获取集团提供的空气猫数据url
            serviceUrl = common_url + "/restservice/monitor/aircat/hour";
            map = createReqMessage(request_params);
        }
        //从缓存中看是否已经存在数据


        String cacheParam =  StringUtils.defaultString(mac, "") + StringUtils.defaultString(beginDate, "") + StringUtils.defaultString(endDate, "");
        String airCatDataCacheKey =  WXUtil.getAirCatCacheKey(cacheParam, WechatConstant.WX_CACHE_AIRCAT_DATA);
        String CacheCodeJsonStr = template.get(airCatDataCacheKey);

        if(StringUtils.isNotBlank(CacheCodeJsonStr)){
            log.info("空气猫缓存数据key：" + airCatDataCacheKey + "在reiis中已经存在数据直接返回" + CacheCodeJsonStr);
            return CacheCodeJsonStr;
        }else{
            log.info("空气猫缓存数据key：" + airCatDataCacheKey + "不存在去数据start" );
            String data = RequestUtil.sendRequest(serviceUrl, map);

            //获取data 做对应的处理
            String result = manageResult(data,type);
            //将获取数据放入缓存1小时
            template.setex(airCatDataCacheKey, result, 3600);
            log.info("空气猫缓存数据key：" + airCatDataCacheKey + "不存在去数据end" );
            return result;
        }

    }

    public String manageResult(String data,String type){
        //ch2List 甲醛 tvocList 污染指数 pm25List PM2.5 pollutionLevel 化学污染物 temperature 温度 humidity 湿度
        List<Map<String,Object>> ch2List  =  new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> tvocList  = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> pm25List  = new ArrayList<Map<String,Object>>();

        List<Map<String,Object>> pollutionLevelList  =  new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> temperatureList  = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> humityList  = new ArrayList<Map<String,Object>>();

        Map<String,Object> params_result = Maps.newHashMap();

        Map<String,Object> params = Maps.newHashMap();
        if(StringUtils.isNotBlank(data) && !data.equals("null")){
            JSONArray jsonArray = JSONArray.fromObject(data);

            for(int i=0;i<jsonArray.size();i++){
                String ss  = jsonArray.getString(i);
                JSONObject jsonObject = JSONObject.fromObject(ss);
                for (Iterator iter = jsonObject.keys(); iter.hasNext();) { //先遍历整个
                    String key = (String)iter.next();
                    Map<String,Object> params_map =  Maps.newHashMap();

                    //将数值对应四舍五入取2位
                    String jsonObjectString = jsonObject.getString(key);
                    Double d2 = Double.valueOf(jsonObjectString);
                    DecimalFormat df = new DecimalFormat("######0.00");

                    if("month".equals(type)){
                        params_map.put(WXUtil.timeStamp2Date(jsonObject.getString("testTime"), "yyyy-MM-dd"), df.format(d2));
                    }else if("hour".equals(type)){
                        params_map.put(WXUtil.timeStamp2Date(jsonObject.getString("testTime"), "HH:mm"), df.format(d2));
                    }

                    if("ch2".equals(key)){
                        ch2List.add(params_map);
                    }else if("tvoc".equals(key)){
                        tvocList.add(params_map);
                    }else if("pm25".equals(key)){
                        pm25List.add(params_map);
                    }else if("humity".equals(key)){
                        humityList.add(params_map);
                    }else if("temperature".equals(key)){
                        temperatureList.add(params_map);
                    }else if("pollutionLevel".equals(key)){
                        pollutionLevelList.add(params_map);
                    }
                }
            }

            params.put("ch2", ch2List.toArray(new Map[ch2List.size()]));
            params.put("tvoc", tvocList);
            params.put("pm25", pm25List);
            params.put("humity", humityList);
            params.put("temperature", temperatureList);
            params.put("pollutionLevel", pollutionLevelList);
             /*   params.put("position",wxUserDeviceRelation.getPosition());
                params.put("mac", wxUserDeviceRelation.getMac());
*/
        }else{

        }
        params_result.put("statusCode",200);
        params_result.put("dataType",type);
        params_result.put("data",JSONObject.fromObject(params));

        return JsonUtil.mapToJson(params_result);
    }

    /**
     * 拼接空气猫需要的json数据
     * @param dataMap
     * @return
     */
    protected static Map<String, Object> createReqMessage(Map<String,Object> dataMap) {
        Map<String,Object> map =  Maps.newHashMap();
        //调用空气猫数据固定的key 由集团提供 写死
        map.put("apikey", "4d7beeb9-6608-493e-ac0f-7277c1e8d89c");
        if (dataMap != null) {
            map.put("data", JsonUtil.mapToJson(dataMap));
        }
        return map;
    }


}


