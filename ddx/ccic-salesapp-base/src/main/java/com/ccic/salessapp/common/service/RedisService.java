
package com.ccic.salessapp.common.service;

import com.ccic.salessapp.common.core.CoreContants;
import com.ccic.salessapp.common.request.UserToken;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Slf4j
@Service
public class RedisService {
    private static final Gson gson = new Gson();
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    Environment environment;

    public <T> void put(final String key, final T t) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        ops.set(gson.toJson(t));
    }

    public <T> void put(final String key, final T t, long expireInSeconds) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        ops.set(gson.toJson(t));
        if (expireInSeconds > 0)
            ops.expire(expireInSeconds, TimeUnit.SECONDS);
    }

    public <T> T get(final String key, Class<T> clazz) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        return gson.fromJson(ops.get(), clazz);
    }

    public Set<String> listKeys() {
        return redisTemplate.keys("*");
    }

    public <T> void putCacheMap(final String cacheKey, final Map<String, T> map) {
        BoundHashOperations<String, String, String> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        bound.putAll(Maps.transformValues(map, v -> {
            return gson.toJson(v);
        }));
    }

    public <T> Map<String, T> getCacheMap(String cacheKey, Class<T> clazz) {
        BoundHashOperations<String, String, String> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        Map<String, String> serializedData = bound.entries();
        return Maps.transformValues(serializedData, v -> {
            return gson.fromJson(v, clazz);
        });
    }

    public <T> T getDataFromCacheMap(String cacheKey, String key, Class<T> clazz) {
        BoundHashOperations<String, String, String> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        String v = bound.get(key);
        if (StringUtils.isNotBlank(v)) {
            return gson.fromJson(v, clazz);
        }
        return null;
    }

    public <T> void putDataToCacheMap(String cacheKey, String key, T value) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        bound.put(key, gson.toJson(value));
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param cacheKey 键
     * @param key 项
     * @param value 值
     * @return true 成功 false失败
     */
    public <T> void hset(String cacheKey,String key,T value) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        bound.putIfAbsent(key, gson.toJson(value));
    }

    public Set<Object> keys(String cacheKey) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        return bound.keys();
    }
    public void deleteDataFromCacheMap(String cacheKey, String key) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        bound.delete(key);
    }

    public void deleteDataFromCacheMap(String appName,String cacheKey, String key) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(appName,cacheKey));
        bound.delete(key);
    }

    public boolean cacheMapHasKey(String cacheKey, String key) {
        BoundHashOperations<String, Object, Object> bound = redisTemplate.boundHashOps(transKey(cacheKey));
        return bound.hasKey(key);
    }



    public Long increment(final String key, long delta) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        return ops.increment(delta);
    }

    public Long decrement(final String key, long delta) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        return ops.decrement(delta);
    }

    public <T> void putToList(String key, T data) {
        BoundListOperations ops = redisTemplate.boundListOps(transKey(key));
        ops.leftPush(gson.toJson(data));
    }

    public <T> void putTrimList(String key, T data,int size) {
        Assert.isTrue(size>1,"size must be gt 1");
        BoundListOperations ops = redisTemplate.boundListOps(key);
        ops.leftPush(gson.toJson(data));
        ops.trim(0,size-1);
    }

    public <T> List<T> listData(String key, long start, long end,Class<T> clazz) {
        BoundListOperations ops = redisTemplate.boundListOps(transKey(key));
        List<String> data=ops.range(start, end);
        return CollectionUtils.isEmpty(data)?Lists.newArrayList():data.stream().map(a->gson.fromJson(a,clazz)).collect(Collectors.toList());
    }


    private String transKey(String key){
        String appName=environment.getProperty("spring.application.name","");
        return StringUtils.isNotBlank(appName)?appName+":"+key:key;
    }
    private String transKey(String appName,String key){
        return StringUtils.isNotBlank(appName)?appName+":"+key:key;
    }
    public void deleteKey(String key) {
        redisTemplate.delete(transKey(key));
    }


    public void putAccessId(final String key, final String t,long expireInSeconds) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(CoreContants.ACCESS_KEY+key);
        ops.set(t);
        ops.expire(expireInSeconds, TimeUnit.SECONDS);
    }

    public String getAccessId(final String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(CoreContants.ACCESS_KEY+key);
        return ops.get();
    }
    
    public String getValue(String key) {
        return redisTemplate.boundValueOps(transKey(key)).get();
    }
    
    public String getStringValue(final String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        return ops.get();
    }
    
    public void setStringValue(final String key, final String t,long expireInSeconds) {
    	BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(transKey(key));
        ops.set(t);
        ops.expire(expireInSeconds, TimeUnit.SECONDS);
    }


    public void putAccessToken(final String key, final UserToken t, long expireInSeconds) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(CoreContants.ACCESS_TOKEN+key);
        ops.set(new Gson().toJson(t));
        ops.expire(expireInSeconds, TimeUnit.SECONDS);
    }

    public UserToken getAccessToken(final String key) {
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(CoreContants.ACCESS_TOKEN+key);
        String result=ops.get();
        return StringUtils.isNotBlank(result) ?gson.fromJson(result, UserToken.class):null;
    }
    public UserToken getAccessTokenByUserCode(final String key) {
    	BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(CoreContants.USER_KEY+key);
    	String result=ops.get();
    	return StringUtils.isNotBlank(result) ?gson.fromJson(result, UserToken.class):null;
    }

    public void deleteToken(String key) {
        redisTemplate.delete(CoreContants.ACCESS_TOKEN+key);
    }
}
