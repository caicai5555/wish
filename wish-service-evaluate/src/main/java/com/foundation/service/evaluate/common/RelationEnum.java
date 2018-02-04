package com.foundation.service.evaluate.common;

/**
 * 
* @ClassName: RelationEnum 
* @Description: 评估规则解析时的关系符
* @author chengchen 
* @date 2016年10月17日 下午5:14:30 
*
 */
public enum RelationEnum {
	AND("&&", "且"), OR("||", "或");
	private String code;
	private String name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private RelationEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static RelationEnum getByName(String name){
		if(null==name){
			return AND;
		}
		switch (name) {
		case "且":
			return AND;
		case "或":
			return OR;

		default:
			return AND;
		}
	}
}
