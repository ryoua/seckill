package com.ryoua.seckill.redis;

/**
 * @Author ryoua Created on 2019-07-07
 */
public class UserKey extends BasePrefix {
    /**
     * 防止被外面实例化，设置永不过期
     * @param prefix
     */
    private UserKey(String prefix) {
        super(prefix);
    }

    /**
     * 需要缓冲的字段
     */
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
