package com.foundation.service.evaluate.serviceInterface.vo;

import java.util.Set;

import com.google.common.collect.Sets;

public class PregnantResultVO {
	/**综合妊娠建议*/
	private int comprehensivePregnantAdvice = 1;
	/**女高风险评分计数*/
	private int femaleHighRisk_5 = 0;
	private int femaleHighRisk_4 = 0;
	private int femaleHighRisk_3 = 0;
	/**女高风险标签*/
	private int femaleHighRiskTag = 0;
	/**男高风险评分计数*/
	private int maleHighRisk_5 = 0;
	private int maleHighRisk_4 = 0;
	private int maleHighRisk_3 = 0;
	/**男高风险标签*/
	private int maleHighRiskTag = 0;
	/**高风险标签*/
	private int highRiskTag = 0;
	/**风险结论*/
	private String riskConclusion = "";
	/**男方、女方、家庭变量标签为正常者*/
	private boolean maleNormalFlag = true;
	private boolean femaleNormalFlag = true;
	private boolean familyNormalFlag = true;
	/**缺失项列表*/
	Set<String> femaleMissingItem = Sets.newHashSet();
	Set<String> maleMissingItem = Sets.newHashSet();
	
	public int getComprehensivePregnantAdvice() {
		return comprehensivePregnantAdvice;
	}
	public void setComprehensivePregnantAdvice(int comprehensivePregnantAdvice) {
		this.comprehensivePregnantAdvice = comprehensivePregnantAdvice;
	}
	public int getFemaleHighRisk_5() {
		return femaleHighRisk_5;
	}
	public void setFemaleHighRisk_5(int femaleHighRisk_5) {
		this.femaleHighRisk_5 = femaleHighRisk_5;
	}
	public int getFemaleHighRisk_4() {
		return femaleHighRisk_4;
	}
	public void setFemaleHighRisk_4(int femaleHighRisk_4) {
		this.femaleHighRisk_4 = femaleHighRisk_4;
	}
	public int getFemaleHighRisk_3() {
		return femaleHighRisk_3;
	}
	public void setFemaleHighRisk_3(int femaleHighRisk_3) {
		this.femaleHighRisk_3 = femaleHighRisk_3;
	}
	public int getFemaleHighRiskTag() {
		return femaleHighRiskTag;
	}
	public void setFemaleHighRiskTag(int femaleHighRiskTag) {
		this.femaleHighRiskTag = femaleHighRiskTag;
	}
	public int getMaleHighRisk_5() {
		return maleHighRisk_5;
	}
	public void setMaleHighRisk_5(int maleHighRisk_5) {
		this.maleHighRisk_5 = maleHighRisk_5;
	}
	public int getMaleHighRisk_4() {
		return maleHighRisk_4;
	}
	public void setMaleHighRisk_4(int maleHighRisk_4) {
		this.maleHighRisk_4 = maleHighRisk_4;
	}
	public int getMaleHighRisk_3() {
		return maleHighRisk_3;
	}
	public void setMaleHighRisk_3(int maleHighRisk_3) {
		this.maleHighRisk_3 = maleHighRisk_3;
	}
	public int getMaleHighRiskTag() {
		return maleHighRiskTag;
	}
	public void setMaleHighRiskTag(int maleHighRiskTag) {
		this.maleHighRiskTag = maleHighRiskTag;
	}
	public int getHighRiskTag() {
		return highRiskTag;
	}
	public void setHighRiskTag(int highRiskTag) {
		this.highRiskTag = highRiskTag;
	}
	public String getRiskConclusion() {
		return riskConclusion;
	}
	public void setRiskConclusion(String riskConclusion) {
		this.riskConclusion = riskConclusion;
	}
	public boolean isMaleNormalFlag() {
		return maleNormalFlag;
	}
	public void setMaleNormalFlag(boolean maleNormalFlag) {
		this.maleNormalFlag = maleNormalFlag;
	}
	public boolean isFemaleNormalFlag() {
		return femaleNormalFlag;
	}
	public void setFemaleNormalFlag(boolean femaleNormalFlag) {
		this.femaleNormalFlag = femaleNormalFlag;
	}
	public boolean isFamilyNormalFlag() {
		return familyNormalFlag;
	}
	public void setFamilyNormalFlag(boolean familyNormalFlag) {
		this.familyNormalFlag = familyNormalFlag;
	}
	public Set<String> getFemaleMissingItem() {
		return femaleMissingItem;
	}
	public void setFemaleMissingItem(Set<String> femaleMissingItem) {
		this.femaleMissingItem = femaleMissingItem;
	}
	public Set<String> getMaleMissingItem() {
		return maleMissingItem;
	}
	public void setMaleMissingItem(Set<String> maleMissingItem) {
		this.maleMissingItem = maleMissingItem;
	}
	
}
