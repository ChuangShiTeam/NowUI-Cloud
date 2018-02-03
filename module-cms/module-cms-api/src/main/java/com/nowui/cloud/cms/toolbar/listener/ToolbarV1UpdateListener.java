package com.nowui.cloud.cms.toolbar.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.cms.toolbar.router.ToolbarRouter;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工具栏新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Configuration
public class ToolbarV1UpdateListener {

    private final String queueName = "toolbar_v1_update";

    @Autowired
    private ToolbarService toolbarService;

    @Bean
    Queue ToolbarV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ToolbarV1UpdateQueueBindingExchange(Queue ToolbarV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ToolbarV1UpdateQueue).to(exchange).with(ToolbarRouter.TOOLBAR_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ToolbarV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ToolbarV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ToolbarV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ToolbarView toolbarView = JSON.parseObject(message, ToolbarView.class);

                toolbarService.update(toolbarView);
            }

        };
    }

}
