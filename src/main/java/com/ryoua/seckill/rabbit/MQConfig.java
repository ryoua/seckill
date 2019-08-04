package com.ryoua.seckill.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: ryoua
 * @Create: 2019-08-03 18:53
 */
@Configuration
public class MQConfig {

    public static final String SECKILL_QUEUE = "seckill.queue";

    @Bean
    public Queue queue() {
        return new Queue(SECKILL_QUEUE, true);
    }

}
