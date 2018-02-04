package com.bioeh.sp.hm.wx.common.persistence;


/**
 * 接口响应结果
 *
 * 
 */
public class JsonData {
	private int code;
	private String tip = "";
	private Object data = null;

	public String getTip() {
		if (tip == "") {
			tip = "success";
		}
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
