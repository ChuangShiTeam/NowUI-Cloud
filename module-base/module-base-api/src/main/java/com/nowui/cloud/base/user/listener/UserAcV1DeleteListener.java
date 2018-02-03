package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserAcService;
import com.nowui.cloud.base.user.router.UserAcRouter;
import com.nowui.cloud.base.user.view.UserAccountView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户账号新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class UserAcV1DeleteListener {

    private final String queueName = "user_ac_v1_delete";

    @Autowired
    private UserAcService userAcService;

    @Bean
    Queue UserAcV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserAcV1DeleteQueueBindingExchange(Queue UserAcV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserAcV1DeleteQueue).to(exchange).with(UserAcRouter.USER_AC_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserAcV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserAcV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserAcV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserAccountView userAcView = JSON.parseObject(message, UserAccountView.class);

                userAcService.save(userAcView);
            }

        };
    }

}
