package com.foundation.service.evaluate.common;
/**
 * 
* @ClassName: StatusEnum 
* @Description: 评估信息的状态
* @author chengchen 
* @date 2016年10月17日 下午5:15:28 
*
 */
public enum StatusEnum {
	UPLOAD_SUCCESS(1, "上传成功"),
	PARSING_SUCCESS(2,"解析成功"),
	RUNNING(3,"运行中"),
	COMPLETE(4,"执行结束")
	;
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private StatusEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static StatusEnum getById(int id) {
		switch (id) {
		case 1:
			return UPLOAD_SUCCESS;
		case 2:
			return PARSING_SUCCESS;
		case 3:
			return RUNNING;
		case 4:
			return COMPLETE;
		default:
			return null;
		}
	}
}
