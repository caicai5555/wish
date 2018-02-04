package com.foundation.service.common;

/**
 * 症状分类（后期会用字典数据替换）
 * <P>File name : SymptomCategory.java </P>
 * <P>Author : chengchen </P> 
 * <P>Date : 2016年9月5日 </P>
 */
public enum SymptomCategory {
	TYPICAL(1,"典型症状"),ATYPICAL(2,"非典型症状"),OTHER(3,"其他症状");
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	private SymptomCategory(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public static SymptomCategory getById(int id){
		switch (id) {
		case 1:
			return TYPICAL;
		case 2:
			return ATYPICAL;
		case 3:
			return OTHER;
		default:
			return null;
		}
	}

	public static SymptomCategory getById(String id){
		switch (id) {
		case "1":
			return TYPICAL;
		case "2":
			return ATYPICAL;
		case "3":
			return OTHER;
		default:
			return null;
		}
	}


	@Override
	public String toString() {
		return this.getName();
	}
}
