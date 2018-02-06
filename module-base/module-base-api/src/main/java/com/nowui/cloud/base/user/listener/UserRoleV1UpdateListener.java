package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserRoleService;
import com.nowui.cloud.base.user.router.UserRoleRouter;
import com.nowui.cloud.base.user.view.UserRoleView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户角色新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class UserRoleV1UpdateListener {

    private final String queueName = "user_role_v1_update";

    @Autowired
    private UserRoleService userRoleService;

    @Bean
    Queue UserRoleV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserRoleV1UpdateQueueBindingExchange(Queue UserRoleV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserRoleV1UpdateQueue).to(exchange).with(UserRoleRouter.USER_ROLE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserRoleV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserRoleV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserRoleV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserRoleView userRoleView = JSON.parseObject(message, UserRoleView.class);

                userRoleService.update(userRoleView);
            }

        };
    }

}
