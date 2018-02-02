package com.nowui.cloud.base.subscription.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.subscription.router.SubscriptionRouter;
import com.nowui.cloud.base.subscription.view.SubscriptionView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;

/**
 * 订阅新增消息队列
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Configuration
public class SubscriptionV1SaveListener {

    private final String queueName = "SUBSCRIPTION_V1_save";

    @Bean
    Queue SubscriptionV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding SubscriptionV1SaveQueueBindingExchange(Queue SubscriptionV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(SubscriptionV1SaveQueue).to(exchange).with(SubscriptionRouter.SUBSCRIPTION_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer SubscriptionV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(SubscriptionV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener SubscriptionV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                SubscriptionView subscriptionView = JSON.parseObject(message, SubscriptionView.class);

                System.out.println(subscriptionView.toJSONString());
            }

        };
    }

}
