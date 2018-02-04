package com.foundation.dao.entity.disease;

import java.io.Serializable;

/**
 * @ClassName: GeneLoci
 * @Description:  疾病-基因-位点信息
 * @author wangsen
 * @date 2016/12/6 18:55
 * @version V1.0
 */
public class GeneLoci implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键**/
    private String id;
    /** 基因名称**/
    private String name;
    /** 英文名称**/
    private String enname;
    /** 位点名称**/
    private String lociName;
    /** 描述信息**/
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname == null ? null : enname.trim();
    }

    public String getLociName() {
        return lociName;
    }

    public void setLociName(String lociName) {
        this.lociName = lociName == null ? null : lociName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}