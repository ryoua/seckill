package com.ryoua.seckill.rabbit;

import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.OrderService;
import com.ryoua.seckill.service.SeckillService;
import com.ryoua.seckill.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: ryoua
 * @Create: 2019-08-03 18:53
 */
@Service
public class MQReceiver {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receive(String message) {
        logger.info("receive message :" + message);
        SeckillMessage seckillMessage = RedisService.stringToBean(message, SeckillMessage.class);
        User user = seckillMessage.getUser();
        Long goodsId = seckillMessage.getGoodsId();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0)
            return;

        // 判断是否秒杀到
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null)
            return;

        seckillService.seckill(user, goods);
    }
}
