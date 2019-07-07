package com.ryoua.seckill.redis;

/**
 * @Author ryoua Created on 2019-07-07
 */
public class UserKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600*24 *2;// 默认两天

    /**
     * 防止被外面实例化，设置永不过期
     * @param prefix
     */
    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    /**
     * 需要缓存的字段
     */
    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");
}
