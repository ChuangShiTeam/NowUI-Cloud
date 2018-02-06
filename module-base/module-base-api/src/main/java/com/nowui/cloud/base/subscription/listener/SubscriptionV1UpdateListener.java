package com.nowui.cloud.base.subscription.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.subscription.service.SubscriptionService;
import com.nowui.cloud.base.subscription.router.SubscriptionRouter;
import com.nowui.cloud.base.subscription.view.SubscriptionView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 订阅新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class SubscriptionV1UpdateListener {

    private final String queueName = "subscription_v1_update";

    @Autowired
    private SubscriptionService subscriptionService;

    @Bean
    Queue SubscriptionV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding SubscriptionV1UpdateQueueBindingExchange(Queue SubscriptionV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(SubscriptionV1UpdateQueue).to(exchange).with(SubscriptionRouter.SUBSCRIPTION_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer SubscriptionV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(SubscriptionV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener SubscriptionV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                SubscriptionView subscriptionView = JSON.parseObject(message, SubscriptionView.class);

                subscriptionService.update(subscriptionView);
            }

        };
    }

}
