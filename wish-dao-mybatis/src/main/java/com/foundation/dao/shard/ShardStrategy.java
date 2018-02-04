package com.foundation.dao.shard;

/**
 * Created by fanqinghui on 2016/9/6.
 */
public interface ShardStrategy {
    /**
     * 得到实际表名
     * @return
     */
    public String getTableName();
}
