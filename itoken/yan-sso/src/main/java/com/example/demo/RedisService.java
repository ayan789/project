package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;




    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }

        /**
         * 写入redis缓存（不设置expire存活时间）
         * @param key
         * @param value
         * @return
         */
        public boolean set(final String key, String value){
            boolean result = false;
            try {
                ValueOperations operations = redisTemplate.opsForValue();
                operations.set(key, value);
                result = true;
            } catch (Exception e) {
                log.error("写入redis缓存失败！错误信息为：" + e.getMessage());
            }
            return result;
        }

        /**
         * 写入redis缓存（设置expire存活时间）
         * @param key
         * @param value
         * @param expire
         * @return
         */
        public boolean set(final String key, String value, Long expire){
            boolean result = false;
            try {
                ValueOperations operations = redisTemplate.opsForValue();
                operations.set(key, value);
                redisTemplate.expire(key, expire, TimeUnit.SECONDS);
                result = true;
            } catch (Exception e) {
                log.error("写入redis缓存（设置expire存活时间）失败！错误信息为：" + e.getMessage());
            }
            return result;
        }


        /**
         * 读取redis缓存
         * @param key
         * @return
         */
        public Object get(final String key){
            Object result = null;
            try {
                ValueOperations operations = redisTemplate.opsForValue();
                result = operations.get(key);
            } catch (Exception e) {
                log.error("读取redis缓存失败！错误信息为：" + e.getMessage());
            }
            return result;
        }

        /**
         * 判断redis缓存中是否有对应的key
         * @param key
         * @return
         */
        public boolean exists(final String key){
            boolean result = false;
            try {
                result = redisTemplate.hasKey(key);
            } catch (Exception e) {
                log.error("判断redis缓存中是否有对应的key失败！错误信息为：" + e.getMessage());
            }
            return result;
        }

        /**
         * redis根据key删除对应的value
         * @param key
         * @return
         */
        public boolean remove(final String key){
            boolean result = false;
            try {
                if(exists(key)){
                    redisTemplate.delete(key);
                }
                result = true;
            } catch (Exception e) {
                log.error("redis根据key删除对应的value失败！错误信息为：" + e.getMessage());
            }
            return result;
        }

        /**
         * redis根据keys批量删除对应的value
         * @param keys
         * @return
         */
        public void remove(final String... keys){
            for(String key : keys){
                remove(key);
            }
        }


}