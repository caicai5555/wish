package com.foundation.dao.entity.medicalHistory;

import java.util.Date;

import com.foundation.dao.util.DataEntity;

/**
 * 
* @ClassName: diseaseHistory 
* @Description: 疾病史
* @author chengchen 
* @date 2016年11月21日 下午3:24:18 
*
 */
public class DiseaseHistory extends DataEntity<DiseaseHistory>{
	private static final long serialVersionUID = 1L;
	/** 用户id*/
	private String userId;
	/** 就诊时间 */
	private Date visitTime;
	/** 就诊医院*/
	private String hospital;
	/** 疾病名称*/
	private String diseaseName;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
}
