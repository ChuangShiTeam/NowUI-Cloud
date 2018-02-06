package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.router.UserIdcardRouter;
import com.nowui.cloud.base.user.view.UserIdcardView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户身份证新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class UserIdcardV1DeleteListener {

    private final String queueName = "user_idcard_v1_delete";

    @Autowired
    private UserIdcardService userIdcardService;

    @Bean
    Queue UserIdcardV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserIdcardV1DeleteQueueBindingExchange(Queue UserIdcardV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserIdcardV1DeleteQueue).to(exchange).with(UserIdcardRouter.USER_IDCARD_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserIdcardV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserIdcardV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserIdcardV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserIdcardView userIdcardView = JSON.parseObject(message, UserIdcardView.class);

                userIdcardService.save(userIdcardView);
            }

        };
    }

}
