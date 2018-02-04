package com.foundation.dao.entity.medicalHistory;

import java.io.Serializable;
/**
 * 
* @ClassName: AllergyHistoryDetail 
* @Description: 过敏史明细
* @author chengchen 
* @date 2016年11月21日 下午6:31:04 
*
 */
public class AllergyHistoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**编号*/
	private String id;
	/** 用户id*/
	private String userId;
	/** 过敏史id*/
	private String allergyHistoryId;
	/** 过敏源code（字典）*/
	private String allergyCode;
	/** 过敏源名称（其他）*/
	private String allergyName;
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
	public String getAllergyHistoryId() {
		return allergyHistoryId;
	}
	public void setAllergyHistoryId(String allergyHistoryId) {
		this.allergyHistoryId = allergyHistoryId;
	}
	public String getAllergyCode() {
		return allergyCode;
	}
	public void setAllergyCode(String allergyCode) {
		this.allergyCode = allergyCode;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

}
