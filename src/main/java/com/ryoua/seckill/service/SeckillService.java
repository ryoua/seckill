package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:45
 */
@Service
public class SeckillService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo seckill(User user, GoodsVo goods) {
        // 减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);
        return orderService.createOrder(user, goods);
    }
}
