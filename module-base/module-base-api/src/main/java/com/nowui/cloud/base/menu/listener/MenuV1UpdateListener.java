package com.nowui.cloud.base.menu.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.menu.service.MenuService;
import com.nowui.cloud.base.menu.router.MenuRouter;
import com.nowui.cloud.base.menu.view.MenuView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 菜单新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class MenuV1UpdateListener {

    private final String queueName = "menu_v1_update";

    @Autowired
    private MenuService menuService;

    @Bean
    Queue MenuV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MenuV1UpdateQueueBindingExchange(Queue MenuV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MenuV1UpdateQueue).to(exchange).with(MenuRouter.MENU_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MenuV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MenuV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MenuV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MenuView menuView = JSON.parseObject(message, MenuView.class);

                menuService.update(menuView);
            }

        };
    }

}
