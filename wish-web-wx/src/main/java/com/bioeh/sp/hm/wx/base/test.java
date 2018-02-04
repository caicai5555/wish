package com.bioeh.sp.hm.wx.base;

import com.bioeh.sp.hm.wx.common.constant.WechatConstant;
import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.GetMenuResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhen on 2016/6/23.
 */
public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String appid = "wx5c4fa87a21a15282";
        String secret = "b43522ad9b3d188764fd1bc10f0c5d96";
        ApiConfig config = new ApiConfig(appid, secret);
        TestConfigChangeHandle configChangeHandle = new TestConfigChangeHandle();
        config.addHandle(configChangeHandle);
       createMenu(config);
//        getUserList(config);
//        uploadMedia(config);
//        downloadMedia(config);
//        getUserInfo(config);
    //    getMenu(config);
    }
    public static void getMenu(ApiConfig config) {
        MenuAPI api = new MenuAPI(config);
        GetMenuResponse response = api.getMenu();
        System.out.println( response.toString());
    }

    /**
     * 创建菜单
     *
     * @param config API配置
     */
    private  static  void createMenu(ApiConfig config) throws UnsupportedEncodingException {
        MenuAPI menuAPI = new MenuAPI(config);

        //先删除之前的菜单
        menuAPI.deleteMenu();
        Menu request = new Menu();
        //准备第一个菜单的一级主菜单
        MenuButton button1 = new MenuButton();
        button1.setType(MenuType.CLICK);
        button1.setKey("JYJC");
        button1.setName("基因检测");
        //准备第一个菜单的子菜单
        MenuButton sub1_1 = new MenuButton();
        sub1_1.setKey("CKBG");
        sub1_1.setName("查看报告");
        sub1_1.setType(MenuType.VIEW);

        StringBuffer sb = new StringBuffer();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        sb.append(WechatConstant.WECHAT_APPID);
        String url = "http://bioehwx.com/wx/wxGene/getReport";
        sb.append("&redirect_uri=");
        sb.append(URLEncoder.encode(url,"UTF-8"));
        sb.append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

        System.out.println("查看报告url================"+sb.toString());
        sub1_1.setUrl(sb.toString());

        MenuButton sub1_2 = new MenuButton();
        sub1_2.setKey("YYJD");
        sub1_2.setName("预约解读");
        sub1_2.setType(MenuType.VIEW);

        StringBuffer sb2 = new StringBuffer();
        sb2.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        sb2.append(WechatConstant.WECHAT_APPID);
        String url2 = "http://bioehwx.com/wx/explanation/openExplanation";
        sb2.append("&redirect_uri=");
        sb2.append(URLEncoder.encode(url2,"UTF-8"));
        sb2.append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

        sub1_2.setUrl(sb2.toString());

        MenuButton sub1_3 = new MenuButton();
        sub1_3.setKey("YYJD");
        sub1_3.setName("调查问卷");
        sub1_3.setType(MenuType.CLICK);

        MenuButton sub1_4 = new MenuButton();
        sub1_4.setKey("CJWT");
        sub1_4.setName("常见问题");
        sub1_4.setType(MenuType.CLICK);


        List<MenuButton> button1_list = new ArrayList<MenuButton>();
        button1_list.add(sub1_1);
        button1_list.add(sub1_2);
        button1_list.add(sub1_3);
        button1_list.add(sub1_4);
        //将子菜单放入主菜单里
        button1.setSubButton(button1_list);

        List<MenuButton> mainList = new ArrayList<MenuButton>();

        mainList.add(button1);

        //准备第二个菜单的一级主菜单
        MenuButton button2 = new MenuButton();
        button2.setType(MenuType.CLICK);
        button2.setKey("ZNSB");
        button2.setName("智能设备");
        //第二个菜单的子菜单
        MenuButton sub2_1 = new MenuButton();
        sub2_1.setKey("KQM");
        sub2_1.setName("空气猫");
        sub2_1.setType(MenuType.VIEW);

        StringBuffer sb2_1 = new StringBuffer();
        sb2_1.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
        sb2_1.append(WechatConstant.WECHAT_APPID);
        String url2_1 = "http://bioehwx.com/wx/wxAirCat/getAirCatData";
        sb2_1.append("&redirect_uri=");
        sb2_1.append(URLEncoder.encode(url2_1,"UTF-8"));
        sb2_1.append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

        sub2_1.setUrl(sb2_1.toString());
        MenuButton sub2_2 = new MenuButton();
        sub2_2.setKey("SBTH");
        sub2_2.setName("设备退换");
        sub2_2.setType(MenuType.CLICK);


        MenuButton sub2_3 = new MenuButton();
        sub2_3.setKey("CJWT");
        sub2_3.setName("常见问题");
        sub2_3.setType(MenuType.CLICK);


        List<MenuButton> button2_list = new ArrayList<MenuButton>();
        button2_list.add(sub2_1);
        button2_list.add(sub2_2);
        button2_list.add(sub2_3);
        //将子菜单放入主菜单里
        button2.setSubButton(button2_list);

        mainList.add(button2);

        //准备第三个菜单一级主菜单
        MenuButton button3 = new MenuButton();
        button3.setType(MenuType.CLICK);
        button3.setKey("LXKF");
        button3.setName("联系客服");
       /* //准备第三个子菜单
        MenuButton sub3_1 = new MenuButton();
        sub3_1.setKey("JRHD");
        sub3_1.setName("节日活动");
        sub3_1.setType(MenuType.VIEW);
        sub3_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafb7b8f9457b5d50&redirect_uri=http://121.40.140.41/erhuluanzi/app/testGet&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        MenuButton sub3_2 = new MenuButton();
        sub3_2.setKey("KSCP");
        sub3_2.setName("快速测评");
        sub3_2.setType(MenuType.CLICK);


        List<MenuButton> button3_list = new ArrayList<MenuButton>();
        button3_list.add(sub3_1);
        button3_list.add(sub3_2);
        //将子菜单放入主菜单里
        button3.setSubButton(button3_list);*/

        //将第三个按钮加入
        mainList.add(button3);
        //将主菜单加入请求对象
        request.setButton(mainList);
        System.out.println(request.toJsonString());
        //创建菜单
        ResultType resultType = menuAPI.createMenu(request);
        System.out.println(resultType.toString());
    }
}
