package com.foundation.dao.entity.archive;

import com.foundation.dao.util.DataEntity;

import java.util.Date;

public class FamilyArchive extends DataEntity<FamilyArchive> {


    public FamilyArchive() {
        this.createDate=new Date();
    }

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 提交系统档案id
     */
    private String pregnancyArchiveId;

    /**
     * 妻子身份证
     */
    private String wifeCertNum;

    /**
     * 妻子姓名
     */
    private String wifeName;

    /**
     * 丈夫身份证
     */
    private String husbandCertNum;

    /**
     * 丈夫身份证
     */
    private String husbandName;

    /**
     * 建档服务机构代码
     */
    private String serviceZoneCode;

    /**
     * 建档区域
     */
    private String serviceZone;

    /**
     * 知情同意书签署情况
     */
    private String informedConsentSigned;

    /**
     * 建档省份编码
     */
    private String provinceCode;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWifeCertNum() {
        return wifeCertNum;
    }

    public void setWifeCertNum(String wifeCertNum) {
        this.wifeCertNum = wifeCertNum == null ? null : wifeCertNum.trim();
    }

    public String getWifeName() {
        return wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName == null ? null : wifeName.trim();
    }

    public String getHusbandCertNum() {
        return husbandCertNum;
    }

    public void setHusbandCertNum(String husbandCertNum) {
        this.husbandCertNum = husbandCertNum == null ? null : husbandCertNum.trim();
    }

    public String getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName == null ? null : husbandName.trim();
    }

    public String getServiceZoneCode() {
        return serviceZoneCode;
    }

    public void setServiceZoneCode(String serviceZoneCode) {
        this.serviceZoneCode = serviceZoneCode == null ? null : serviceZoneCode.trim();
    }

    public String getServiceZone() {
        return serviceZone;
    }

    public void setServiceZone(String serviceZone) {
        this.serviceZone = serviceZone == null ? null : serviceZone.trim();
    }

    public String getInformedConsentSigned() {
        return informedConsentSigned;
    }

    public void setInformedConsentSigned(String informedConsentSigned) {
        this.informedConsentSigned = informedConsentSigned;
    }

    public String getPregnancyArchiveId() {
        return pregnancyArchiveId;
    }

    public void setPregnancyArchiveId(String pregnancyArchiveId) {
        this.pregnancyArchiveId = pregnancyArchiveId;
    }


    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}