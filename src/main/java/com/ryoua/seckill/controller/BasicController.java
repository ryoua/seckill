package com.ryoua.seckill.controller;

import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.OrderService;
import com.ryoua.seckill.service.SeckillService;
import com.ryoua.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ryoua
 * @Create: 2019-08-03 10:16
 */
public class BasicController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;
}
