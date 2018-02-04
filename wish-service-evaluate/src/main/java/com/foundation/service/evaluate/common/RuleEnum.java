package com.foundation.service.evaluate.common;

/**
 * 
* @ClassName: RuleEnum 
* @Description: 评估规则解析时的规则模式
* @author chengchen 
* @date 2016年10月17日 下午5:14:58 
*
 */
public enum RuleEnum {
	CONCLUSION(1, "conclusion", "结论"), SUM(2, "sum", "求和");

	private int id;
	private String enName;
	private String name;

	public int getId() {
		return id;
	}

	public String getEnName() {
		return enName;
	}

	public String getName() {
		return name;
	}

	private RuleEnum(int id, String enName, String name) {
		this.id = id;
		this.enName = enName;
		this.name = name;
	}

	public static RuleEnum getByName(String name) {
		if(null==name){
			return CONCLUSION;
		}
		switch (name) {
		case "结论":
			return CONCLUSION;
		case "求和":
			return SUM;
		default:
			return CONCLUSION;
		}
	}
	
	public static RuleEnum getById(int id){
		switch (id) {
		case 1:
			return CONCLUSION;
		case 2:
			return SUM;
		default:
			return null;
		}
	}
}
