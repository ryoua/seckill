package com.ryoua.seckill.redis;

/**
 * @Author: ryoua
 * @Create: 2019-08-04 13:56
 */
public class SeckillKey extends BasePrefix {
    private SeckillKey(String prefix) {
        super(prefix);
    }
    public static SeckillKey isGoodsOver = new SeckillKey("go");
}