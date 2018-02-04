package com.foundation.dao.entity.subsystem;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: Subsystem
 * @Description: 平台的外接系统
 * @Author cuiyaohua
 * @Date 2016/12/12
 *
 */
public class Subsystem extends DataEntity<Subsystem> {
    private static final long serialVersionUID = 1L;

    /** 名字 */
    private String name;

    /** 编码代号 */
    private String code;

    /** 描述 */
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
