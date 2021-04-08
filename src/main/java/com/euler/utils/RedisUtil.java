package com.euler.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author <a href="mailto:873406454@qq.com">Li Hangfei</a>
 * @date 2021/4/7
 */
@Component
public class RedisUtil {
    @Autowired
    protected StringRedisTemplate redisTemplate;

    /**
     * 写入redis缓存（不设置expire存活时间）
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入redis缓存（设置expire存活时间）
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public boolean set(final String key, String value, Long expire) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 读取redis缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断redis缓存中是否有对应的key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        boolean result = false;
        try {
            result = redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * redis根据key删除对应的value
     *
     * @param key
     * @return
     */
    public boolean remove(final String key) {
        boolean result = false;
        try {
            if (exists(key)) {
                redisTemplate.delete(key);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * redis根据keys批量删除对应的value
     *
     * @param keys
     * @return
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
}
