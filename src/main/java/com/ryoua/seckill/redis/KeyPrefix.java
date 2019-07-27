package com.ryoua.seckill.redis;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 13:12
 */
public interface KeyPrefix {
    int expireSeconds();

    String getPrefix();
}
