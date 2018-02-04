package com.foundation.dao.entity.disease;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DiseaseInfo
 * @Description: 疾病-疾病信息
 * @author wangsen
 * @date 2016/12/2 14:50
 * @version V1.0
 */
public class Disease implements Serializable{
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 父id**/
    private String pid;
    /** 类型疾病组，1是疾病**/
    private Integer type;
    /** 对应疾病分类id**/
    private String diseaseCategoryId;
    /** 疾病名称**/
    private String name;
    /** 英文名称**/
    private String englishName;
    /** 别名**/
    private String alias;
    /** 简介**/
    private String info;
    /** 注意事项和预防(护理)**/
    private String hint;
    /** 就诊科室**/
    private String medicalDepartment;
    /** 传染性**/
    private String infectiousness;
    /** 常见发病部位**/
    private String commonSite;
    /** 常见病因**/
    private String commonCause;
    /** 常见症状**/
    private String symptom;
    /** 检查**/
    private String inspect;
    /** 治疗**/
    private String cure;
    /** 饮食治疗(保健)**/
    private String dietericTreatment;
    /** 专家解读**/
    private String expertInterpretation;
    /** 鉴别诊断**/
    private String antidiastole;
    /** 是否在医保范围:0在医保,1不在医保**/
    private Integer medicalInsurance;
    /** 参考治疗费用**/
    private String treatmentCost;
    /** 治疗率**/
    private String treatmentRate;
    /** 多发人群**/
    private String multiplePopulation;
    /** 并发症(引发其他疾病)**/
    private String neopathy;
    /** 排序**/
    private Integer sort;
    /** 是否启用：1启用，0不启用**/
    private Integer activeFlag;
    /** 删除标记:1删除；0正常**/
    private Integer delFlag;
    /** 新增时间**/
    private Date createDate;
    /** 更新时间**/
    private Date updateDate;
    /** 备注 **/
    private String remark;
    /** 疾病类型id**/
    private String dieaseTypeId;
    /** 疾病id**/
    private String dieaseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDiseaseCategoryId() {
        return diseaseCategoryId;
    }

    public void setDiseaseCategoryId(String diseaseCategoryId) {
        this.diseaseCategoryId = diseaseCategoryId == null ? null : diseaseCategoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? null : hint.trim();
    }

    public String getMedicalDepartment() {
        return medicalDepartment;
    }

    public void setMedicalDepartment(String medicalDepartment) {
        this.medicalDepartment = medicalDepartment == null ? null : medicalDepartment.trim();
    }

    public String getInfectiousness() {
        return infectiousness;
    }

    public void setInfectiousness(String infectiousness) {
        this.infectiousness = infectiousness == null ? null : infectiousness.trim();
    }

    public String getCommonSite() {
        return commonSite;
    }

    public void setCommonSite(String commonSite) {
        this.commonSite = commonSite == null ? null : commonSite.trim();
    }

    public String getCommonCause() {
        return commonCause;
    }

    public void setCommonCause(String commonCause) {
        this.commonCause = commonCause == null ? null : commonCause.trim();
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
    }

    public String getInspect() {
        return inspect;
    }

    public void setInspect(String inspect) {
        this.inspect = inspect == null ? null : inspect.trim();
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure == null ? null : cure.trim();
    }

    public String getDietericTreatment() {
        return dietericTreatment;
    }

    public void setDietericTreatment(String dietericTreatment) {
        this.dietericTreatment = dietericTreatment == null ? null : dietericTreatment.trim();
    }

    public String getExpertInterpretation() {
        return expertInterpretation;
    }

    public void setExpertInterpretation(String expertInterpretation) {
        this.expertInterpretation = expertInterpretation == null ? null : expertInterpretation.trim();
    }

    public String getAntidiastole() {
        return antidiastole;
    }

    public void setAntidiastole(String antidiastole) {
        this.antidiastole = antidiastole == null ? null : antidiastole.trim();
    }

    public Integer getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Integer medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public String getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(String treatmentCost) {
        this.treatmentCost = treatmentCost == null ? null : treatmentCost.trim();
    }

    public String getTreatmentRate() {
        return treatmentRate;
    }

    public void setTreatmentRate(String treatmentRate) {
        this.treatmentRate = treatmentRate == null ? null : treatmentRate.trim();
    }

    public String getMultiplePopulation() {
        return multiplePopulation;
    }

    public void setMultiplePopulation(String multiplePopulation) {
        this.multiplePopulation = multiplePopulation == null ? null : multiplePopulation.trim();
    }

    public String getNeopathy() {
        return neopathy;
    }

    public void setNeopathy(String neopathy) {
        this.neopathy = neopathy == null ? null : neopathy.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDieaseTypeId() {
        return dieaseTypeId;
    }

    public void setDieaseTypeId(String dieaseTypeId) {
        this.dieaseTypeId = dieaseTypeId == null ? null : dieaseTypeId.trim();
    }

    public String getDieaseId() {
        return dieaseId;
    }

    public void setDieaseId(String dieaseId) {
        this.dieaseId = dieaseId == null ? null : dieaseId.trim();
    }
}