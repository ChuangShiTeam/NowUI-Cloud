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
@Configuration
public class ProductV1SaveListener {

    private final String queueName = "product_v1_save";

    @Autowired
    private ProductService productService;

    @Bean
    Queue ProductV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ProductV1SaveQueueBindingExchange(Queue ProductV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ProductV1SaveQueue).to(exchange).with(ProductRouter.PRODUCT_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ProductV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ProductV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ProductV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ProductView productView = JSON.parseObject(message, ProductView.class);

                productService.save(productView);
            }

        };
    }

}
