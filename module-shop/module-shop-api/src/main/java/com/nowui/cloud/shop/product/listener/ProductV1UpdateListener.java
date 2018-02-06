package com.nowui.cloud.shop.product.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.router.ProductRouter;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 商品新增消息队列
 *
 * @author ZhongYongQiang
 *
 * 2018-02-03
 */
//@Configuration
public class ProductV1UpdateListener {

    private final String queueName = "product_v1_update";

    @Autowired
    private ProductService productService;

    @Bean
    Queue ProductV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ProductV1UpdateQueueBindingExchange(Queue ProductV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ProductV1UpdateQueue).to(exchange).with(ProductRouter.PRODUCT_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ProductV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ProductV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ProductV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ProductView productView = JSON.parseObject(message, ProductView.class);

                productService.update(productView);
            }

        };
    }

}
