package com.ryoua.seckill.utils;

import java.util.UUID;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 16:24
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
