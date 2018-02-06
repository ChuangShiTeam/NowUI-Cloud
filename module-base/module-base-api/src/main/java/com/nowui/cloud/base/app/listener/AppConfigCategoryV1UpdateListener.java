package com.nowui.cloud.base.app.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.router.AppConfigCategoryRouter;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置分类新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class AppConfigCategoryV1UpdateListener {

    private final String queueName = "app_config_category_v1_update";

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;

    @Bean
    Queue AppConfigCategoryV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding AppConfigCategoryV1UpdateQueueBindingExchange(Queue AppConfigCategoryV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(AppConfigCategoryV1UpdateQueue).to(exchange).with(AppConfigCategoryRouter.APP_CONFIG_CATEGORY_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer AppConfigCategoryV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(AppConfigCategoryV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener AppConfigCategoryV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                AppConfigCategoryView appConfigCategoryView = JSON.parseObject(message, AppConfigCategoryView.class);

                appConfigCategoryService.update(appConfigCategoryView);
            }

        };
    }

}
