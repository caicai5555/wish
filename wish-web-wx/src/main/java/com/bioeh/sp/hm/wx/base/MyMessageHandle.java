package com.bioeh.sp.hm.wx.base;

import com.bioeh.sp.hm.wx.common.constant.WechatMessageType;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;

import java.util.Calendar;

/**
 * Created by liuzhen on 2016/6/24.
 */
public class MyMessageHandle implements MessageHandle {

    @Override
    public BaseMsg handle(BaseReqMsg message) {
        //文本消息回复最长的2048个字节 //如果超过最好使用图文消息或者客服接口回复多条
        String content = "";
        if(WechatMessageType.REQUEST_TEXT_MESSAGE.equals(message.getMsgType())) {
            TextReqMsg textMsg = (TextReqMsg) message;
            if("1".equals(textMsg.getContent())){
                content = "你回复的是" + textMsg.getContent() + "\r\n想要知道空气猫常见问题";
            }else if("2".equals(textMsg.getContent())){
                content = "你回复的是" + textMsg.getContent() + "\r\n检测报告常见问题";
            }else if("3".equals(textMsg.getContent())){
                content = "你回复的是" + textMsg.getContent() + "\r\n最新活动";
            }else{
                content = "我真不知道你要弄啥咧\r\n？";
            }

        }else if(WechatMessageType.REQUEST_IMAGE_MESSAGE.equals(message.getMsgType())){
            content="IMAGE消息";
        }else if(WechatMessageType.REQUEST_VOICE_MESSAGE.equals(message.getMsgType())){
            content="VOICE消息";
        }else if(WechatMessageType.REQUEST_VIDEO_MESSAGE.equals(message.getMsgType())){
            content="VIDEO消息";
        }else if(WechatMessageType.REQUEST_SHORTVIDEO_MESSAGE.equals(message.getMsgType())){
            content="SHORTVIDEO消息";
        }else if(WechatMessageType.REQUEST_LOCATION_MESSAGE.equals(message.getMsgType())){
            content="LOCATION消息";
        }else if(WechatMessageType.REQUEST_LINK_MESSAGE.equals(message.getMsgType())){
            content="LINK消息";
        }
        return new TextMsg(content);
    }

    @Override
    public boolean beforeHandle(BaseReqMsg message) {
        return true;
    }

}