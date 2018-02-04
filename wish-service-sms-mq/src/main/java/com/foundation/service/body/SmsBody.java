package com.foundation.service.body;

import java.io.Serializable;

/**
 * Created by fanqinghui on 2016/9/8.
 */
public class SmsBody implements Serializable {

    private Integer providerId;
    private String content;
    private String mobiles;

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    @Override
    public String toString() {
        return "SmsBody{" +
                "providerId=" + providerId +
                ", content='" + content + '\'' +
                ", mobiles='" + mobiles + '\'' +
                '}';
    }
}
