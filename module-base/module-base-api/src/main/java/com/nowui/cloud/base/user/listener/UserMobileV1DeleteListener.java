package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.router.UserMobileRouter;
import com.nowui.cloud.base.user.view.UserMobileView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 	用户手机号码新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class UserMobileV1DeleteListener {

    private final String queueName = "user_mobile_v1_delete";

    @Autowired
    private UserMobileService userMobileService;

    @Bean
    Queue UserMobileV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserMobileV1DeleteQueueBindingExchange(Queue UserMobileV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserMobileV1DeleteQueue).to(exchange).with(UserMobileRouter.USER_MOBILE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserMobileV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserMobileV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserMobileV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserMobileView userMobileView = JSON.parseObject(message, UserMobileView.class);

                userMobileService.save(userMobileView);
            }

        };
    }

}
