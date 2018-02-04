package com.foundation.dao.entity.app;

import com.foundation.common.bean.BasePo;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.dao.shard.ShardStrategy;
import org.hibernate.validator.constraints.NotBlank;

import java.util.logging.Logger;

/**
 * Created by fqh on 2015/12/8.
 */
public class AppVersion extends BasePo implements ShardStrategy {
    protected String id;
    private Integer type;
    private String version;
    private String url;
    private Integer forceUpdate;

    @NotBlank
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @NotBlank
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTableName() {
        if(StringUtils.isNotBlank(id)){
            long hashcode=Math.abs(id.hashCode());
            return "tb_app_version_"+(hashcode%12);
        }
        Logger.getAnonymousLogger().info("UserGetTableName id is null");
        return "tb_app_version";
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", version='" + version + '\'' +
                ", url='" + url + '\'' +
                ", forceUpdate=" + forceUpdate +
                "} " + super.toString();
    }
}
