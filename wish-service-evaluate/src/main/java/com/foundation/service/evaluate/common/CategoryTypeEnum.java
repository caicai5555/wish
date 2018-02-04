package com.foundation.service.evaluate.common;

/**
 * 
 * @ClassName: CategoryTypeEnum
 * @Description: 评估分类类型
 * @author chengchen
 * @date 2016年10月19日 上午11:03:48
 *
 */
public enum CategoryTypeEnum {
	EVALUATE(1, "评估"), CONCLUSION(2, "结论");
	/** 类型id */
	private int id;
	/** 类型名称 */
	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private CategoryTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
