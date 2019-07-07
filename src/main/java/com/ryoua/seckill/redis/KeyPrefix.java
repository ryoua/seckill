package com.ryoua.seckill.redis;

/**
 * 缓冲key前缀
 *
 * @Author ryoua Created on 2019-07-07
 */
public interface KeyPrefix {
    /**
     * 有效期
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();
}
