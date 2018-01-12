package com.nowui.cloud.shop.product.mq.impl;

import com.nowui.cloud.shop.product.mq.ProductMq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
@Component
public class ProductMqImpl implements ProductMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendFoo2Rabbitmq(String message) {
        rabbitTemplate.convertAndSend("exchange", "test", message);
    }

    @Override
    public void sendBar2Rabbitmq(String message) {
        rabbitTemplate.convertAndSend("exchange", "queue.*", message);
    }

}
