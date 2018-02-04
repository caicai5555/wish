package com.foundation.service.evaluate.common;

/**
 * 
 * @ClassName: PregnantResultKeyEnum
 * @Description: 孕检系统结果的key定义
 * @author chengchen
 * @date 2016年10月20日 上午10:43:37
 *
 */
public enum PregnantResultKeyEnum {
	ABNORMAL_FLAG("abnormalFlag", "是否异常"), 
	ADVICE_FLAG("adviceFlag", "是否建议"), 
	RESULT_DESC("resultDesc","结果表述"), 
	HARM_DESC("harmDesc", "危害表述"), 
	ADVICE_DESC("adviceDesc", "建议表述"), 
	HARM_LEVEL("harmLevel","危害等级"), 
	FAMILY_HARM_LEVEL("familyHarmLevel","家庭危害等级"), 
	MALE_HARM_LEVEL("maleHarmLevel","男方危害等级"), 
	FEMALE_HARM_LEVEL("femaleHarmLevel","女方危害等级"), 
	CONTROL_LEVEL("controlLevel", "可控等级"), 
	PREGNANT_ADVICE("pregnantAdvice", "妊娠建议"),
	DEFAULT("default","默认");
	
	private String enname;
	private String name;

	public String getEnname() {
		return enname;
	}

	public String getName() {
		return name;
	}

	private PregnantResultKeyEnum(String enname, String name) {
		this.enname = enname;
		this.name = name;
	}
	public static PregnantResultKeyEnum getByName(String name){
		if(null==name){
			return null;
		}
		switch (name) {
		case "是否异常":
			return ABNORMAL_FLAG;
		case "是否建议":
			return ADVICE_FLAG;
		case "结果表述":
			return RESULT_DESC;
		case "危害表述":
			return HARM_DESC;
		case "建议表述":
			return ADVICE_DESC;
		case "危害等级":
			return HARM_LEVEL;
		case "家庭危害等级":
			return FAMILY_HARM_LEVEL;
		case "男方危害等级":
			return MALE_HARM_LEVEL;
		case "女方危害等级":
			return FEMALE_HARM_LEVEL;
		case "可控等级":
			return CONTROL_LEVEL;
		case "妊娠建议":
			return PREGNANT_ADVICE;
		default:
			return null;
		}
	}
}
