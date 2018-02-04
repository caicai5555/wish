package com.foundation.dao.util;

import com.google.common.collect.Maps;
import org.apache.ibatis.plugin.Interceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by fanqinghui on 2016/7/20.
 */
public class PageUtils {

    public static Map<String,Object> getReqParamsMaps(HttpServletRequest request){
        Map resultMap= Maps.newConcurrentMap();
        Map parasMap=request.getParameterMap();
        Set keySet=parasMap.keySet();
        Iterator it=keySet.iterator();
        while (it.hasNext()){
            Object key=it.next();
            resultMap.put(key,request.getParameter(key.toString()));
        }
        return resultMap;
    }
}
