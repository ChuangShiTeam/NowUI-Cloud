package com.nowui.cloud.base.app.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.base.app.router.AppConfigRouter;
import com.nowui.cloud.base.app.view.AppConfigView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class AppConfigV1SaveListener {

    private final String queueName = "app_config_v1_save";

    @Autowired
    private AppConfigService appConfigService;

    @Bean
    Queue AppConfigV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding AppConfigV1SaveQueueBindingExchange(Queue AppConfigV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(AppConfigV1SaveQueue).to(exchange).with(AppConfigRouter.APP_CONFIG_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer AppConfigV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(AppConfigV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener AppConfigV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                AppConfigView appConfigView = JSON.parseObject(message, AppConfigView.class);

                appConfigService.save(appConfigView);
            }

        };
    }

}
