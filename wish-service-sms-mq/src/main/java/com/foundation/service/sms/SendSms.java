package com.foundation.service.sms;

/**
 * Created by fanqinghui on 2016/9/7.
 */
public interface SendSms {

    /**
     *  发送短信
     * @param providerId 短信服务商Id
     * @param content 短信内容
     * @param mobile 短息号码
     * @return
     */
    public boolean sendSms(Integer providerId,String content,String... mobile);
}
