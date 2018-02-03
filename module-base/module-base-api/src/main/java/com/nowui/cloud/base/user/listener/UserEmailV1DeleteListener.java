package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.router.UserEmailRouter;
import com.nowui.cloud.base.user.view.UserEmailView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户邮箱新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class UserEmailV1DeleteListener {

    private final String queueName = "user_email_v1_delete";

    @Autowired
    private UserEmailService userEmailService;

    @Bean
    Queue UserEmailV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserEmailV1DeleteQueueBindingExchange(Queue UserEmailV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserEmailV1DeleteQueue).to(exchange).with(UserEmailRouter.USER_EMAIL_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserEmailV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserEmailV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserEmailV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserEmailView userEmailView = JSON.parseObject(message, UserEmailView.class);

                userEmailService.save(userEmailView);
            }

        };
    }

}
