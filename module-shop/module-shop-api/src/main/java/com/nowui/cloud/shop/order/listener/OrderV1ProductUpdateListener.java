package com.nowui.cloud.shop.order.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitConfig;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.shop.product.listener.ProductV1UpdateListener;
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
 * 订单的产品更新消息绑定
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Configuration
@AutoConfigureAfter(RabbitConfig.class)
public class OrderV1ProductUpdateListener {

    private final String queueName = "order_product_v1_update";

    @Autowired
    protected ProductRepository productRepository;

    @Bean
    Queue orderProductV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding orderProductV1UpdateQueueBindingExchange(Queue orderProductV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(orderProductV1UpdateQueue).to(exchange).with(ProductRouter.PRODUCT_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer orderProductV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(orderProductV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener orderProductV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                System.out.println("order++++++");
                System.out.println(message);
                System.out.println("++++++");

//                ProductView productView = JSON.parseObject(message, ProductView.class);
//                productRepository.save(productView);
            }

        };
    }

}
