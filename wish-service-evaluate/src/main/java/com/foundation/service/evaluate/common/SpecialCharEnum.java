package com.foundation.service.evaluate.common;

/**
 * 
* @ClassName: SpecialCharEnum 
* @Description: 特殊占位符,1表示字典，2表示自身
* @author chengchen 
* @date 2016年10月18日 下午5:27:14 
*
 */
public enum SpecialCharEnum {
	DICT("dict_",1), SELF("self_",2);
	private String name;
	private int flag;

	public String getName() {
		return name;
	}
	public int getFlag(){
		return flag;
	}

	private SpecialCharEnum(String name,int flag) {
		this.flag= flag;
		this.name = name;
	}
}
