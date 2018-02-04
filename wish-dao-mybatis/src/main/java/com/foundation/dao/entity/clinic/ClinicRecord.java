package com.foundation.dao.entity.clinic;

import com.foundation.dao.util.DataEntity;
import java.util.Date;
/**
 * @ClassName: ClinicRecord
 * @Description: 临床记录实体
 * @Author cuiyaohua
 * @Date 2016/10/13
 *
 */
public class ClinicRecord extends DataEntity<ClinicRecord> {
    private static final long serialVersionUID = 1L;
    /**
     *检验日期
     */
    private Date inspectDate;
    /**
     *检验单名称
     */
    private String inspectName;
    /**
     *类型名称
     */
    private String typeName;
    /**
     *医院
     */
    private String hospital;
    /**
     *地点
     */
    private String address;
    /**
     *医生
     */
    private String doctor;
    /**
     *科室
     */
    private String department;
    /**
     *描述
     */
    private String description;
    /**
     *状态，0未填写，1已填写，2已删除
     */
    private Integer status;
    /**
     *是否追加，0不是，1是
     */
    private Integer isAdd;
    /**
     *结论
     */
    private String conclusion;
    /**
     *管理师
     */
    private String manager;
    /**
     *类型id
     */
    private String typeId;
    /**
     *管理师id
     */
    private String managerId;
    /**
     *医生id
     */
    private String doctorId;
    /**
     *会员id
     */
    private String custId;
    /**
     *订单id
     */
    private String orderId;
    /**
     *服务类别id
     */
    private String serviceClassId;
    /**
     *订单详情id
     */
    private String orderItemId;
    /**
     *检验单id
     */
    private String inspectId;
    /**
     * 检验单
     */
    private InspectList inspectList;

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) {
        this.inspectName = inspectName == null ? null : inspectName.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor == null ? null : doctor.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Integer isAdd) {
        this.isAdd = isAdd;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion == null ? null : conclusion.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceClassId() {
        return serviceClassId;
    }

    public void setServiceClassId(String serviceClassId) {
        this.serviceClassId = serviceClassId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getInspectId() {
        return inspectId;
    }

    public void setInspectId(String inspectId) {
        this.inspectId = inspectId;
    }

    public InspectList getInspectList() {
        return inspectList;
    }

    public void setInspectList(InspectList inspectList) {
        this.inspectList = inspectList;
    }
}