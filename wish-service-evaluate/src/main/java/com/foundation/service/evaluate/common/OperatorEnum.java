package com.foundation.service.evaluate.common;

/**
 * 
* @ClassName: OperatorEnum 
* @Description: 评估规则解析时的操作符
* @author chengchen 
* @date 2016年10月17日 下午5:14:18 
*
 */
public enum OperatorEnum {
	/** 等于 */
	EQ("==", "等于"),
	/** 不能于 */
	NOT_EQ("!=", "不等于"),
	/** 大于 */
	GT(">", "大于"),
	/** 大于等于 */
	GE(">=", "大于等于"),
	/** 小于 */
	LT("<", "小于"),
	/** 小于等于 */
	LE("<=", "小于等于");

	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private OperatorEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static OperatorEnum getByName(String name) {
		if (null == name) {
			return EQ;
		}
		switch (name) {
		case "等于":
			return EQ;
		case "不等于":
			return NOT_EQ;
		case "大于":
			return GT;
		case "大于等于":
			return GE;
		case "小于":
			return LT;
		case "小于等于":
			return LE;
		default:
			return EQ;
		}
	}
}
