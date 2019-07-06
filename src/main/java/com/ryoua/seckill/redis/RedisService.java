package com.ryoua.seckill.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author ryoua Created on 2019-07-06
 */
@Service
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str=  jedis.get(key);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> Boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str=  beanToString(value);
            if (str == null || str.length() <= 0)
                return false;
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * Bean转为String
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beanToString(T value) {
        if (value == null)
            return null;
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return String.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return String.valueOf(value);
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * String类型转为Bean
     * @param str
     * @param tClass
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String str, Class<T> tClass) {
        if (str == null || str.length() <= 0 || tClass == null)
            return null;
        if (tClass == int.class || tClass == Integer.class)
            return (T) Integer.valueOf(str);
        else if (tClass == long.class || tClass == Long.class)
            return (T) Long.valueOf(str);
        else if (tClass == String.class)
            return (T) str;
        else
            return JSON.toJavaObject(JSON.parseObject(str), tClass);
    }

    /**
     * 返回连接池
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }
}
