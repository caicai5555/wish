package com.foundation.service.file.utils;

import com.foundation.cache.utils.PropertiesUtils;
import com.foundation.common.utils.StringUtils;
import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.cleverframe.fastdfs.client.DefaultStorageClient;
import org.cleverframe.fastdfs.client.DefaultTrackerClient;
import org.cleverframe.fastdfs.client.StorageClient;
import org.cleverframe.fastdfs.client.TrackerClient;
import org.cleverframe.fastdfs.conn.DefaultCommandExecutor;
import org.cleverframe.fastdfs.pool.ConnectionPool;
import org.cleverframe.fastdfs.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 简单的fastdfs客户端模板单利工具类
 * Created by fqh on 2016/11/25
 */
public class FastDfsUtils {

    static Logger logger= LoggerFactory.getLogger(FastDfsUtils.class);

    private FastDfsUtils(){}

    private static ConnectionPool connectionPool;
    private static StorageClient storageClient;

    static {
        try {
            String trackers =PropertiesUtils.getValue("fdfs.tracker.server");
            if(StringUtils.isBlank(trackers)){
                throw new RuntimeException("请配置fdfs.tracker.server配置信息");
            }
            Set<String> trackerSet = Sets.newHashSet();
            for(String tracker:trackers.split(",")){
                trackerSet.add(tracker);
            }
            logger.info("FastDfs's trackers's num is:"+trackerSet.size());
            PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(500, 500);
            GenericKeyedObjectPoolConfig conf = new GenericKeyedObjectPoolConfig();
            conf.setMaxTotal(200);
            conf.setMaxTotalPerKey(200);
            conf.setMaxIdlePerKey(100);
            connectionPool = new ConnectionPool(pooledConnectionFactory, conf);
            DefaultCommandExecutor commandExecutor = new DefaultCommandExecutor(trackerSet, connectionPool);
            TrackerClient trackerClient = new DefaultTrackerClient(commandExecutor);
            storageClient = new DefaultStorageClient(commandExecutor, trackerClient);
        }catch (Exception e){
            throw new RuntimeException("请检查配置文件fastdfs环境以及配置信息");
        }
    }

    /**
     * 获取storageClient连接信息
     * @return
     */
    public static StorageClient getStorageClient(){
        return storageClient;
    }
}
