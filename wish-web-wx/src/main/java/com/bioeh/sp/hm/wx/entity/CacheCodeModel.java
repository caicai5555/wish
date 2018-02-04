package com.bioeh.sp.hm.wx.entity;

import java.io.Serializable;

/**
 * 验证码缓存
 */
public class CacheCodeModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object value;
	private Integer time;
	private String key;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
    
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "CacheCodeModel [value=" + value + ", time=" + time + ", key=" + key + "]";
	}
}
