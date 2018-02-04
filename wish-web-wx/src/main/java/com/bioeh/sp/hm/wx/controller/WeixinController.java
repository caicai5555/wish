package com.bioeh.sp.hm.wx.controller;

import com.bioeh.sp.hm.wx.base.MyEventHandle;
import com.bioeh.sp.hm.wx.base.MyMessageHandle;
import com.bioeh.sp.hm.wx.common.constant.WechatMessageType;
import com.bioeh.sp.hm.wx.common.web.BaseController;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.Article;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuzhen on 2016/6/23.
 */
@Controller
@RequestMapping("/weixin")
    public class WeixinController extends WeixinControllerSupport {

    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
    private static final String TOKEN = "liuzhen";
    //设置TOKEN，用于绑定微信服务器
    @Override
    protected String getToken() {
        return TOKEN;
    }
    //使用安全模式时设置：APPID
    @Override
    protected String getAppId() {
        return null;
    }
    //使用安全模式时设置：密钥
    @Override
    protected String getAESKey() {
        return null;
    }
   /* //重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        //文本消息回复最长的2048个字节 //如果超过最好使用图文消息或者客服接口回复多条
        String content = "";
        if(WechatMessageType.REQUEST_TEXT_MESSAGE.equals(msg.getMsgType())) {
            //文本类型消息
            if("图文".equals(msg.getContent())){
                System.out.println("图文");
                Article article = new Article();
                article.setDescription("百度");
                article.setPicUrl("www.baidu.com");
                article.setUrl("www.baidu.com");
                article.setTitle("百度一下");

                List<Article> Articlelist = new ArrayList<Article>();
                Articlelist.add(article);

                return new NewsMsg(Articlelist);
            }
            content="TEXT消息<a href=\"http://www.baidu.com\">百度一下</a>";
        }else if(WechatMessageType.REQUEST_IMAGE_MESSAGE.equals(msg.getMsgType())){
            content="IMAGE消息";
        }else if(WechatMessageType.REQUEST_VOICE_MESSAGE.equals(msg.getMsgType())){
            content="VOICE消息";
        }else if(WechatMessageType.REQUEST_VIDEO_MESSAGE.equals(msg.getMsgType())){
            content="VIDEO消息";
        }else if(WechatMessageType.REQUEST_SHORTVIDEO_MESSAGE.equals(msg.getMsgType())){
            content="SHORTVIDEO消息";
        }else if(WechatMessageType.REQUEST_LOCATION_MESSAGE.equals(msg.getMsgType())){
            content="LOCATION消息";
        }else if(WechatMessageType.REQUEST_LINK_MESSAGE.equals(msg.getMsgType())){
            content="LINK消息";
        }
        return new TextMsg(content);
    }*/
    /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
     *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
     *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected List<MessageHandle> initMessageHandles() {
        log.info("initMessageHandles");
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        handles.add(new MyMessageHandle());
        return handles;
    }
    //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
    @SuppressWarnings("rawtypes")
    @Override
    protected List<EventHandle> initEventHandles() {
        log.info("initEventHandles");
        List<EventHandle> handles = new ArrayList<EventHandle>();
        handles.add(new MyEventHandle());
        return handles;
    }
    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        return new TextMsg("感谢关注博奥生物。。。。。。!");
    }
    @Override
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        event.getFromUserName();
        System.out.println("取消关注"+event.getFromUserName() + "==="+event.getToUserName()+"+++++++++"+event.getMsgType());
        return new TextMsg("感谢关注博奥生物。。。。。。!");
    }

    @Override
    protected BaseMsg handleDefaultEvent(BaseEvent event) {

        System.out.println("其他事件"+event.getFromUserName() + "==="+event.getToUserName()+"+++++++++"+event.getMsgType());
        return super.handleDefaultEvent(event);
    }

}


