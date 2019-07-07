package com.ryoua.seckill.redis;

/**
 * @Author ryoua Created on 2019-07-07
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;
    private String prefix;

    /**
     * 默认0永不过期
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();// 拿到参数类类名
        return className + ":" + prefix;
    }
}
