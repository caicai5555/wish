package com.bioeh.sp.hm.wx.common.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
//	public static ReqMessage jsonToReq(String jsonStr) {
//		ObjectMapper om = new ObjectMapper();
//		ReqMessage req = null;
//		try {
//			req = om.readValue(jsonStr, ReqMessage.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return req;
//		
//	}
	public static String mapToJson(Map<String,Object> m) {
		ObjectMapper om = new ObjectMapper();

		String json="";
		try {
			json = om.writeValueAsString(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	@SuppressWarnings("unchecked")
	public static Map<String,Object> jsonToMap(String jsonStr){
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> map = null;
		try {
			map = om.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String listToJson(List<Map<String,Object>> list) {
		ObjectMapper om = new ObjectMapper();
		String json = "";
		try {
			json = om.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> jsonToList(String jsonStr){
		ObjectMapper om = new ObjectMapper();
		List<Map<String, Object>> list = null;
		try {
			list = om.readValue(jsonStr, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer || obj instanceof Float
				|| obj instanceof Boolean || obj instanceof Short || obj instanceof Double
				|| obj instanceof Long || obj instanceof BigDecimal || obj instanceof BigInteger
				|| obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}
	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e) {}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
				case '"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '/':
					sb.append("\\/");
					break;
				default:
					if (ch >= '\u0000' && ch <= '\u001F') {
						String ss = Integer.toHexString(ch);
						sb.append("\\u");
						for (int k = 0; k < 4 - ss.length(); k++) {
							sb.append('0');
						}
						sb.append(ss.toUpperCase());
					} else {
						sb.append(ch);
					}
			}
		}
		return sb.toString();
	}



	public static void main(String[] args) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("device", "血压仪");
		map.put("measureTime", new Date());
		map.put("type", "bloodpressure");
		map.put("user", "aaa");
		map.put("highPressure", "130");
		map.put("lowPressure", "80");
		map.put("heartRate", "60");
		String json = mapToJson(map);
		System.out.println(json);
	}
}
