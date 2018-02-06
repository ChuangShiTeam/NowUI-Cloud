package com.nowui.cloud.base.notify.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.notify.service.NotifyService;
import com.nowui.cloud.base.notify.router.NotifyRouter;
import com.nowui.cloud.base.notify.view.NotifyView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
//@Configuration
public class NotifyV1SaveListener {

    private final String queueName = "notify_v1_save";

    @Autowired
    private NotifyService notifyService;

    @Bean
    Queue NotifyV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding NotifyV1SaveQueueBindingExchange(Queue NotifyV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(NotifyV1SaveQueue).to(exchange).with(NotifyRouter.NOTIFY_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer NotifyV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(NotifyV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener NotifyV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                NotifyView notifyView = JSON.parseObject(message, NotifyView.class);

                notifyService.save(notifyView);
            }

        };
    }

}
