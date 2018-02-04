package com.foundation.dao.entity.medicalHistory;

import java.io.Serializable;

/**
 * 
* @ClassName: surgeryHistory 
* @Description: 手术史
* @author chengchen 
* @date 2016年11月21日 下午3:30:50 
*
 */
public class SurgeryHistoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**编号*/
	private String id;
	/** 用户id*/
	private String userId;
	/** 手术史id*/
	private String surgeryHistoryId;
	/** 手术部位code（字典）*/
	private String surgeryCode;
	/** 手术部位名称（其他）*/
	private String surgeryName;
	
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
	public String getSurgeryHistoryId() {
		return surgeryHistoryId;
	}
	public void setSurgeryHistoryId(String surgeryHistoryId) {
		this.surgeryHistoryId = surgeryHistoryId;
	}
	public String getSurgeryCode() {
		return surgeryCode;
	}
	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	
}
