package com.bioeh.sp.hm.wx.common.util;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

import com.bioeh.sp.hm.bsp.util.MD5Util;
import com.bioeh.sp.hm.bsp.util.http.HttpConnectionManager;
import com.bioeh.sp.hm.web.PropertyConfigurer;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class RequestUtil {
	public static final String CHARSET = "UTF-8";

	//调用接口需要加密的key
	public static final String TOKEN_KEY="2016jiankangxinxi";

	public static String sendGETRequest(String url , String charset , List<NameValuePair> params) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(params , charset);
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), charset);
		} 
		return "";
	}

	/**
	 * HTTP Get 获取内容
	 *
	 * @param url     请求的url地址 ?之前的地址
	 * @param params  请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doGet(String url, Map<String, String> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = getHttpClient().execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * HTTP Post 获取内容
	 *
	 * @param url     请求的url地址 ?之前的地址
	 * @param params  请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url, Map<String, Object> params) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<>(params.size());
				//去掉NameValuePair转换，这样就可以传递Map<String,Object>
                /*pairs = new ArrayList<NameValuePair>(params.size());*/
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String value = (String) entry.getValue();
					if (value != null) {
						Logger.getAnonymousLogger().info(entry.getKey()+":"+entry.getValue());
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			CloseableHttpResponse response = getHttpClient().execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * sendPOSTRequest:发送post请求. <br/>
	 * @return
	 * @throws Exception
	 */
	public static String sendPOSTRequest(String url , String charset , String request_body) throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpPost method = new HttpPost(url);
		StringEntity entity = new StringEntity(request_body, charset);
		entity.setContentEncoding(charset);
		entity.setContentType("application/json");
		method.setEntity(entity);
		HttpResponse response = client.execute(method);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), charset);
		} 
		return "";
	}

	/**
	 * 空气猫和集团对接
	 * @param url
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static String sendRequest(String url, Map<String, Object> map)
			throws Exception {
		HttpPost httpPost = new HttpPost(url);
		String json = JsonUtil.mapToJson(map);
		System.out.println(json);
		StringEntity se = new StringEntity(json, "UTF-8");
		httpPost.setEntity(se);
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		// httpPost.addHeader(HTTP.CONTENT_ENCODING, "gzip");
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));
		HttpResponse response = new DefaultHttpClient().execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			String result = EntityUtils.toString(response.getEntity(), Charset.forName("utf-8"));
			Map<String,Object> retmap = JsonUtil.jsonToMap(result);
			int retcode = (Integer)retmap.get("code");
			if (retcode == 0) {
				String data = (String)retmap.get("data");
				System.out.print(data);
				return data;
			}
		} else {
			System.out.print("HTTP error code:" + code);
		}
		return null;
	}

	public static CloseableHttpClient getHttpClient(){
		//httpClient.getHttpConnectionManager().getParams().setTcpNoDelay(true);
		//return httpClient;
		return HttpConnectionManager.getHttpClient();
	}

	//请求报告接口start
	/**
	 * getDataFromGene
	 * 返回data
	 * serviceUrl 请求接口后缀
	 */
	public static String getDataFromGene(Map<String,Object> paramMap,String serviceUrl) throws Exception{
		//获取报告系统数据url
		String url = PropertyConfigurer.getProperty("interface.report.data.url") + serviceUrl;
		Map<String,Object> map = createReqMessage(paramMap);
		String data = RequestUtil.doPost(url,map);
		return data;
	}

	/**
	 * 拼接访问报告系统token
	 * @param dataMap
	 * @return
	 */
	protected static Map<String, Object> createReqMessage(Map<String,Object> paramMap) {
		Map<String,Object> map =  Maps.newHashMap();
		map.put("sysTime", Long.toString(System.currentTimeMillis()));
		String md5Str = "";
		String data = "";
		String md5Data = "";
		md5Data = returnData(paramMap);
		data = md5Data + map.get("sysTime");
		md5Str= MD5Util.GetMD5Code(data + TOKEN_KEY);
		map.put("md5Str", md5Str);
		map.put("type","W");
		if (paramMap != null) {
			paramMap.put("token", JsonUtil.mapToJson(map));
		}
		return paramMap;
	}

	public static String returnData(Map<String, Object> mapsSort){
		Map<String,Object> mapkey =  Maps.newHashMap();
		//将map进行排序
		mapkey = sortMapByKey(mapsSort);

		String md5Data = "";
		Iterator it = mapkey.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry =(Map.Entry) it.next();
			md5Data=md5Data+entry.getValue();
		}
		return md5Data;
	}

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());

		sortMap.putAll(map);

		return sortMap;
	}
	//请求报告接口end
}
