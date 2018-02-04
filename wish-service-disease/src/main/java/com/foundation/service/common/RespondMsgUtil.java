package com.foundation.service.common;


import com.alibaba.dubbo.common.json.JSON;

/**
 * 
 * @author Administrator
 *
 */
public class RespondMsgUtil {
 
    public static String respondMsg(ErrorCode code,Object data)  {
		String phJson = null;
		RespondMsg msg = new RespondMsg(code, data);
		try {
			phJson = JSON.json(msg);
		} catch (Exception e) {
			e.printStackTrace();
			phJson = "{\"state\":1003,\"msg\":\" Exception json\"}";
		}
		return phJson;

    }
    
 
    
}
