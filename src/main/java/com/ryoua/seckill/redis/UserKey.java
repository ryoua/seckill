package com.ryoua.seckill.redis;


/**
 * @Author: ryoua
 * @Create: 2019-07-27 13:17
 */
public class UserKey extends BasePrefix{

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    private UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");
    public static UserKey getById = new UserKey(0, "id");
}
