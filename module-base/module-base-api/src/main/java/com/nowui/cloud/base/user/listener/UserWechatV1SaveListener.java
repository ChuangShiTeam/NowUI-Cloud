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
public class UserWechatV1SaveListener {

    private final String queueName = "user_wechat_v1_save";

    @Autowired
    private UserWechatService userWechatService;

    @Bean
    Queue UserWechatV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserWechatV1SaveQueueBindingExchange(Queue UserWechatV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserWechatV1SaveQueue).to(exchange).with(UserWechatRouter.USER_WECHAT_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserWechatV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserWechatV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserWechatV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserWechatView userWechatView = JSON.parseObject(message, UserWechatView.class);

                userWechatService.save(userWechatView);
            }

        };
    }

}
