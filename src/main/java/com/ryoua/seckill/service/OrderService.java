package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.mapper.OrderMapper;
import com.ryoua.seckill.redis.OrderKey;
import com.ryoua.seckill.redis.RedisService;
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

    @Autowired
    RedisService redisService;

    public SeckillOrder getSeckillOrderByUserIdGoodsId(Long userId, long goodsId) {
        return redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, SeckillOrder.class);
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
        orderMapper.insert(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());
        orderMapper.insertSeckillOrder(seckillOrder);
        redisService.set(OrderKey.getSeckillOrderByUidGid, "" + user.getId() + "_" + goods.getId(), seckillOrder);
        return orderInfo;
    }

    public OrderInfo getOrderById(Long orderId) {
        return orderMapper.getOrderById(orderId);
    }

    public void deleteOrders() {
        orderMapper.deleteOrders();
        orderMapper.deleteSeckillOrders();
    }
}
