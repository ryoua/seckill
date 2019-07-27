package com.ryoua.seckill.redis;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 13:17
 */
public class UserKey extends BasePrefix{

    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
