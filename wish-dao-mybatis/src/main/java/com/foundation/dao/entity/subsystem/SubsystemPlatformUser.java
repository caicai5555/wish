package com.foundation.dao.entity.subsystem;

import com.foundation.dao.util.DataEntity;

/**
 * @ClassName: SubsystemPlatformUser
 * @Description: 外接系统和平台用户的关系表
 * @Author cuiyaohua
 * @Date 2016/12/12
 *
 */
public class SubsystemPlatformUser extends DataEntity<SubsystemPlatformUser> {
    private static final long serialVersionUID = 1L;

    /** 外接系统id */
    private String sid;

    /** 用户id */
    private String uid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
