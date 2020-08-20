package com.donald.wj_back.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Donald
 * @data 20/08/2020 14:58
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * 设置带过期时间的缓存
     * @param key
     * @param value
     * @param time
     */
    public void set(String key,Object value,long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 根据 key 获得缓存
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据 key 删除缓存
     * @param key
     * @return
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     *  根据 keys 集合批量删除缓存
     * @param keys
     * @return
     */
    public Long delete(Set<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 根据正则表达式匹配 keys 获取缓存
     * @param pattern
     * @return
     */
    public Set<String> getKeysByPattern(String pattern) {
        return redisTemplate.keys(pattern);
    }



}
