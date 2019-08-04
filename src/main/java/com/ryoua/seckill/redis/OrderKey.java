package com.ryoua.seckill.redis;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 13:20
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getSeckillOrderByUidGid = new OrderKey("moug");
}
