package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.base.user.router.UserWechatRouter;
import com.nowui.cloud.base.user.view.UserWechatView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户微信新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class UserWechatV1UpdateListener {

    private final String queueName = "user_wechat_v1_update";

    @Autowired
    private UserWechatService userWechatService;

    @Bean
    Queue UserWechatV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserWechatV1UpdateQueueBindingExchange(Queue UserWechatV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserWechatV1UpdateQueue).to(exchange).with(UserWechatRouter.USER_WECHAT_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserWechatV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserWechatV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserWechatV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserWechatView userWechatView = JSON.parseObject(message, UserWechatView.class);

                userWechatService.update(userWechatView);
            }

        };
    }

}
