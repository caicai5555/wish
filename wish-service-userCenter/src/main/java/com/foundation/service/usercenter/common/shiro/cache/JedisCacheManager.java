package com.foundation.service.usercenter.common.shiro.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.foundation.cache.redis.JedisTemplate;
import com.foundation.cache.utils.RedisUtils;
import com.foundation.common.io.SerializeUtil;
import com.foundation.service.usercenter.security.Servlets;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

/**
 * 自定义授权缓存管理类
 *
 * @author fqh
 * @version 2016-7-20
 */

public class JedisCacheManager implements CacheManager {

    private String cacheKeyPrefix = "shiro_cache_";

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new JedisCache<K, V>(cacheKeyPrefix + name);
    }

    public String getCacheKeyPrefix() {
        return cacheKeyPrefix;
    }

    public void setCacheKeyPrefix(String cacheKeyPrefix) {
        this.cacheKeyPrefix = cacheKeyPrefix;
    }

    //采用redisTemplate
    private JedisTemplate template = RedisUtils.getTemplate();

    /**
     * 自定义授权缓存管理类
     *
     * @author fqh
     * @version 2016-7-20
     */

    public class JedisCache<K, V> implements Cache<K, V> {

        private Logger logger = LoggerFactory.getLogger(getClass());

        private String cacheKeyName = null;

        public JedisCache(String cacheKeyName) {
            this.cacheKeyName = cacheKeyName;
        }

        @SuppressWarnings("unchecked")
        @Override
        public V get(K key) throws CacheException {
            if (key == null) {
                return null;
            }

            V v = null;
            HttpServletRequest request = Servlets.getRequest();
            if (request != null) {
                v = (V) request.getAttribute(cacheKeyName);
                if (v != null) {
                    return v;
                }
            }

            V value = null;
            try {
                value = (V) SerializeUtil.toObject(template.hget(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key)));
                logger.debug("get {} {} {}", cacheKeyName, key, request != null ? request.getRequestURI() : "");
            } catch (Exception e) {
                logger.error("get {} {} {}", cacheKeyName, key, request != null ? request.getRequestURI() : "", e);
            }

            if (request != null && value != null) {
                request.setAttribute(cacheKeyName, value);
            }
            return value;
        }

        @Override
        public V put(K key, V value) throws CacheException {
            if (key == null) {
                return null;
            }

            try {
                template.hset(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key), SerializeUtil.toBytes(value));
                logger.debug("put {} {} = {}", cacheKeyName, key, value);
            } catch (Exception e) {
                logger.error("put {} {}", cacheKeyName, key, e);
            }
            return value;
        }

        @SuppressWarnings("unchecked")
        @Override
        public V remove(K key) throws CacheException {
            V value = null;
            try {
                value = (V) SerializeUtil.toObject(template.hget(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key)));
                long result=template.hdel(SerializeUtil.getBytesKey(cacheKeyName), SerializeUtil.getBytesKey(key));
                logger.debug("remove {} {}:"+result, cacheKeyName, key);
            } catch (Exception e) {
                logger.warn("remove {} {}", cacheKeyName, key, e);
            }
            return value;
        }

        @Override
        public void clear() throws CacheException {
            try {
                template.hdel(SerializeUtil.getBytesKey(cacheKeyName), null);
                logger.debug("clear {}", cacheKeyName);
            } catch (Exception e) {
                logger.error("clear {}", cacheKeyName, e);
            }
        }

        @Override
        public int size() {
            int size = 0;
            try {
                size = template.hlen(SerializeUtil.getBytesKey(cacheKeyName)).intValue();
                logger.debug("size {} {} ", cacheKeyName, size);
                return size;
            } catch (Exception e) {
                logger.error("clear {}", cacheKeyName, e);
            }
            return size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Set<K> keys() {
            Set<K> keys = Sets.newHashSet();
            try {
                Set<byte[]> set = template.hkeys(SerializeUtil.getBytesKey(cacheKeyName));
                for (byte[] key : set) {
                    Object obj = (K) SerializeUtil.getObjectKey(key);
                    if (obj != null) {
                        keys.add((K) obj);
                    }
                }
                logger.debug("keys {} {} ", cacheKeyName, keys);
                return keys;
            } catch (Exception e) {
                logger.error("keys {}", cacheKeyName, e);
                return keys;
            }

        }

        @Override
        public Collection<V> values() {
            Collection<V> vals = Collections.emptyList();
            try {
                Collection<byte[]> col = template.hvals(SerializeUtil.getBytesKey(cacheKeyName));
                for (byte[] val : col) {
                    Object obj = SerializeUtil.toObject(val);
                    if (obj != null) {
                        vals.add((V) obj);
                    }
                }
                logger.debug("values {} {} ", cacheKeyName, vals);
                return vals;
            } catch (Exception e) {
                logger.error("values {}", cacheKeyName, e);
            }
            return vals;
        }
    }
}
