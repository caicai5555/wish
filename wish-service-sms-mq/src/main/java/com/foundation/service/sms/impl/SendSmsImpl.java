package com.foundation.service.sms.impl;

import com.foundation.common.business.BizConstants;
import com.foundation.common.json.JsonUtils;
import com.foundation.common.utils.StringUtils;
import com.foundation.api.model.SmsMessage;
import com.foundation.service.util.SMSProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by fanqinghui on 2016/9/7.
 */
@Service
public class SendSmsImpl implements MessageListener {

    Logger logger = LoggerFactory.getLogger(SendSmsImpl.class);

    //private static String sign = "庆辉快递";// 签名

    /**
     * 发送短信
     *
     * @param name          用户名
     * @param pwd           密码
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @param sign          签名
     * @param stime         追加发送时间，可为空，为空为及时发送
     * @param extno         扩展码，必须为数字 可为空
     * @return
     * @throws Exception
     */
    public static String post(String url, String name, String pwd, StringBuffer mobileString, String contextString,
                              String sign, String stime, StringBuffer extno) throws Exception {
        StringBuffer param = new StringBuffer();
        param.append("name=" + name);
        param.append("&pwd=" + pwd);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString, "UTF-8"));
        param.append("&stime=" + stime);
        param.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
        param.append("&type=pt");
        param.append("&extno=").append(extno);

        URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }
        return resultBuffer;
    }

    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    @Override
    public void onMessage(Message message) {

        byte[] body = message.getBody();
        String getMsg = new String(message.getBody());
        logger.info("receive data :" + getMsg);
        System.out.println("receive data :" + getMsg);
        try {
            SmsMessage smsMessage = JsonUtils.fromJson(getMsg, SmsMessage.class);

            SMSProvider provider = SMSProvider.getProvider(smsMessage.getProviderId());
            if (provider == null) {
                logger.info("采用默认短信发送服务商");
                provider = SMSProvider.getDefault();
            }
            if (null == smsMessage || null == provider || smsMessage.getMobiles() == null || StringUtils.isBlank(smsMessage.getContent())) {
                logger.info("短信发送不成功：原因（收到的信息不完整）");
            } else {
                logger.info("provider:" + "-content:" + smsMessage.getContent() + "-mobiles:" + Arrays.toString(smsMessage.getMobiles()));
                // 电话号码字符串，中间用英文逗号间隔
                StringBuffer mobileString = new StringBuffer();
                for (String mobile : smsMessage.getMobiles()) {
                    mobileString.append(mobile).append(",");
                }
                // 内容字符串
                //StringBuffer contextString=new StringBuffer("您的订单号为：test，请不要告诉他人，请及时取件");
                // 追加发送时间，可为空，为空为及时发送
                String stime = "";
                // 扩展码，必须为数字 可为空
                StringBuffer extno = new StringBuffer();
                try {
                    String result = post(provider.getUrl(), provider.getName(), provider.getPwd(), mobileString, smsMessage.getContent(), BizConstants.SMSSIGN, stime, extno);
                    logger.info("result:" + result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }

    }
}
