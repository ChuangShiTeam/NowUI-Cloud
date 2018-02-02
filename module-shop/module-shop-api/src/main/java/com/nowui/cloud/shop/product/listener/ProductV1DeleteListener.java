package com.nowui.cloud.shop.product.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitConfig;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.router.ProductRouter;
import com.nowui.cloud.shop.product.view.ProductView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 产品删除消息绑定
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Configuration
@AutoConfigureAfter(RabbitConfig.class)
public class ProductV1DeleteListener {

    private final String queueName = "product_v1_delete";

    @Autowired
    protected ProductRepository productRepository;

    @Bean
    Queue productV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding productV1DeleteQueueBindingExchange(Queue productV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(productV1DeleteQueue).to(exchange).with(ProductRouter.PRODUCT_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer productV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(productV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener productV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ProductView productView = JSON.parseObject(message, ProductView.class);

                productRepository.update(productView);
            }

        };
    }

}
