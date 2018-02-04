package com.foundation.dao.entity.medicalHistory;

import java.io.Serializable;
/**
 * 
* @ClassName: DrugHistoryDetail 
* @Description: 用药史明细
* @author chengchen 
* @date 2016年11月21日 下午6:28:40 
*
 */
public class DrugHistoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**编号*/
	private String id;
	/** 用户id*/
	private String userId;
	/** 用药史id*/
	private String drugHistoryId;
	/** 药品code（字典）*/
	private String drugCode;
	/** 药品名称（其他）*/
	private String drugName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDrugHistoryId() {
		return drugHistoryId;
	}
	public void setDrugHistoryId(String drugHistoryId) {
		this.drugHistoryId = drugHistoryId;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
}
