package com.foundation.dao.entity.medicalHistory;

import java.util.ArrayList;
import java.util.List;

import com.foundation.dao.util.DataEntity;

/**
 * 
* @ClassName: surgeryHistory 
* @Description: 手术史
* @author chengchen 
* @date 2016年11月21日 下午3:30:50 
*
 */
public class SurgeryHistory extends DataEntity<SurgeryHistory>{
	private static final long serialVersionUID = 1L;
	/** 用户id*/
	private String userId;
	/**手术部位Code逗号分隔字符串（VO）*/
	private List<String> surgeryCodes = new ArrayList<>();
	/**手术部位Name逗号分隔字符串（VO）*/
	private String surgeryNameStr;
	/**其他时填写的内容*/
	private String otherContent;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getSurgeryCodes() {
		return surgeryCodes;
	}
	public void setSurgeryCodes(List<String> surgeryCodes) {
		this.surgeryCodes = surgeryCodes;
	}
	public String getSurgeryNameStr() {
		return surgeryNameStr;
	}
	public void setSurgeryNameStr(String surgeryNameStr) {
		this.surgeryNameStr = surgeryNameStr;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

}
