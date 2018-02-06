package com.nowui.cloud.base.role.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.role.service.RoleService;
import com.nowui.cloud.base.role.router.RoleRouter;
import com.nowui.cloud.base.role.view.RoleView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 角色新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class RoleV1UpdateListener {

    private final String queueName = "role_v1_update";

    @Autowired
    private RoleService roleService;

    @Bean
    Queue RoleV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding RoleV1UpdateQueueBindingExchange(Queue RoleV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(RoleV1UpdateQueue).to(exchange).with(RoleRouter.ROLE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer RoleV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(RoleV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener RoleV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                RoleView roleView = JSON.parseObject(message, RoleView.class);

                roleService.update(roleView);
            }

        };
    }

}
