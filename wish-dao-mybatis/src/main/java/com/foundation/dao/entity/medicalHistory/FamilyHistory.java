package com.foundation.dao.entity.medicalHistory;

import com.foundation.dao.util.DataEntity;

/**
 * 
* @ClassName: familyHistory 
* @Description: 家族病史
* @author chengchen 
* @date 2016年11月21日 下午3:26:38 
*
 */
public class FamilyHistory extends DataEntity<FamilyHistory>{
	private static final long serialVersionUID = 1L;
	/** 用户id*/
	private String userId;
	/** 亲属关系Code（字典）*/
	private String relativeCode;
	/** 患病年龄*/
	private int diseaseAge;
	/** 所患疾病Code（字典）*/
	private String diseaseCode;
	/** 疾病名称（其他）*/
	private String diseaseName;
	/** 是否遗传（0：是；1：否；2：不确定）*/
	private int geneticFlag;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public int getDiseaseAge() {
		return diseaseAge;
	}
	public void setDiseaseAge(int diseaseAge) {
		this.diseaseAge = diseaseAge;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public int getGeneticFlag() {
		return geneticFlag;
	}
	public void setGeneticFlag(int geneticFlag) {
		this.geneticFlag = geneticFlag;
	}
	
}
