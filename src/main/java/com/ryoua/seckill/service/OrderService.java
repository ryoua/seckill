package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.mapper.OrderMapper;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:43
 */
@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;


    public SeckillOrder getSeckillOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderMapper.getSeckillOrderByUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(User user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        orderMapper.insertSeckillOrder(seckillOrder);
        return orderInfo;
    }
}
