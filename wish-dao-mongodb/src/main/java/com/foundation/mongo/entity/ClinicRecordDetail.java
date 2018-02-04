package com.foundation.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Title ClinicRecord
 * @Package com.foundation.mongo.entity
 * @Description 临床记录用于存放mongo的实体
 * @Author cuiyaohua
 * @date 2016-10-26 23:35
 */
@Document
public class ClinicRecordDetail implements Serializable{
    private static final long serialVersionUID = 1L;
    /** _id  即 recordId (in mysql)*/
    @Id
    private String id;
    /**
     *检验单id
     */
    private String inspectId;
    /**
     *检验单名称
     */
    private String inspectName;
    /**
     *英文名
     */
    private String englishName;
    /**
     *检验方法
     */
    private String inspectMethod;
    /**
     *所含检验项
     */
    private List<InspectItemDetail> inspectItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInspectId() {
        return inspectId;
    }

    public void setInspectId(String inspectId) {
        this.inspectId = inspectId;
    }

    public String getInspectName() {
        return inspectName;
    }

    public void setInspectName(String inspectName) {
        this.inspectName = inspectName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getInspectMethod() {
        return inspectMethod;
    }

    public void setInspectMethod(String inspectMethod) {
        this.inspectMethod = inspectMethod;
    }

    public List<InspectItemDetail> getInspectItems() {
        return inspectItems;
    }

    public void setInspectItems(List<InspectItemDetail> inspectItems) {
        this.inspectItems = inspectItems;
    }
}
