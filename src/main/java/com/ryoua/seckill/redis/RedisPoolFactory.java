package com.ryoua.seckill.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 12:51
 */
public class RedisPoolFactory {

    @Autowired
    RedisConfig redisConfig;

    @Bean
    private JedisPool JedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        return new JedisPool(poolConfig,
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getTimeout() * 1000,
                redisConfig.getPassword(),
                0);
    }
}
