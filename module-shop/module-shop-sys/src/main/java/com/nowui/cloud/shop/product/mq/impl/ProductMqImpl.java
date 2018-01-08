package com.nowui.cloud.shop.product.mq.impl;

import com.nowui.cloud.shop.product.mq.ProductMq;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ZhongYongQiang
 */
@Component
public class ProductMqImpl implements ProductMq {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendFoo2Rabbitmq(String message) {
        amqpTemplate.convertAndSend("exchange", "queue.foo", message);
    }

    @Override
    public void sendBar2Rabbitmq(String message) {
        amqpTemplate.convertAndSend("exchange", "queue.bar", message);
    }

}
