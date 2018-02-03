package com.nowui.cloud.base.role.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.role.service.RoleMenuService;
import com.nowui.cloud.base.role.router.RoleMenuRouter;
import com.nowui.cloud.base.role.view.RoleMenuView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 角色菜单新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class RoleMenuV1UpdateListener {

    private final String queueName = "role_menu_v1_update";

    @Autowired
    private RoleMenuService roleMenuService;

    @Bean
    Queue RoleMenuV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding RoleMenuV1UpdateQueueBindingExchange(Queue RoleMenuV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(RoleMenuV1UpdateQueue).to(exchange).with(RoleMenuRouter.ROLE_MENU_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer RoleMenuV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(RoleMenuV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener RoleMenuV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                RoleMenuView roleMenuView = JSON.parseObject(message, RoleMenuView.class);

                roleMenuService.update(roleMenuView);
            }

        };
    }

}
