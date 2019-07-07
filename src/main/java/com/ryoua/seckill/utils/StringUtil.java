package com.ryoua.seckill.utils;

import java.util.UUID;

/**
 * @Author ryoua Created on 2019-07-07
 */
public class StringUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
