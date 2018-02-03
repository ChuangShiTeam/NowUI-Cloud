package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserNotifyService;
import com.nowui.cloud.base.user.router.UserNotifyRouter;
import com.nowui.cloud.base.user.view.UserNotifyView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户消息新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class UserNotifyV1UpdateListener {

    private final String queueName = "user_notify_v1_update";

    @Autowired
    private UserNotifyService userNotifyService;

    @Bean
    Queue UserNotifyV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserNotifyV1UpdateQueueBindingExchange(Queue UserNotifyV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserNotifyV1UpdateQueue).to(exchange).with(UserNotifyRouter.USER_NOTIFY_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserNotifyV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserNotifyV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserNotifyV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserNotifyView userNotifyView = JSON.parseObject(message, UserNotifyView.class);

                userNotifyService.update(userNotifyView);
            }

        };
    }

}
