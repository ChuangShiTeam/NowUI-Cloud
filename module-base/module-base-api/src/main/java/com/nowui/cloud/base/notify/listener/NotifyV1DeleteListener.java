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
@Configuration
public class NotifyV1DeleteListener {

    private final String queueName = "notify_v1_delete";

    @Autowired
    private NotifyService notifyService;

    @Bean
    Queue NotifyV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding NotifyV1DeleteQueueBindingExchange(Queue NotifyV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(NotifyV1DeleteQueue).to(exchange).with(NotifyRouter.NOTIFY_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer NotifyV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(NotifyV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener NotifyV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                NotifyView notifyView = JSON.parseObject(message, NotifyView.class);

                notifyService.save(notifyView);
            }

        };
    }

}
