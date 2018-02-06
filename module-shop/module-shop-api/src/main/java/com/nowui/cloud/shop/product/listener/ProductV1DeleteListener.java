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
public class ProductV1DeleteListener {

    private final String queueName = "product_v1_delete";

    @Autowired
    private ProductService productService;

    @Bean
    Queue ProductV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ProductV1DeleteQueueBindingExchange(Queue ProductV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ProductV1DeleteQueue).to(exchange).with(ProductRouter.PRODUCT_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ProductV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ProductV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ProductV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ProductView productView = JSON.parseObject(message, ProductView.class);

                productService.save(productView);
            }

        };
    }

}