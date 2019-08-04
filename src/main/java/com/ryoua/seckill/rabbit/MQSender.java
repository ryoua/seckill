package com.ryoua.seckill.rabbit;

import com.ryoua.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ryoua.seckill.rabbit.MQConfig.SECKILL_QUEUE;

/**
 * @Author: ryoua
 * @Create: 2019-08-03 18:52
 */
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object message) {
        String msg = RedisService.beanToString(message);
        logger.info("send message: " + msg);
        amqpTemplate.convertAndSend(SECKILL_QUEUE, msg);
    }

    public void sendSeckillMessage(SeckillMessage message) {
        String msg = RedisService.beanToString(message);
        logger.info("send message: " + msg);
        amqpTemplate.convertAndSend(SECKILL_QUEUE, msg);
    }
}
