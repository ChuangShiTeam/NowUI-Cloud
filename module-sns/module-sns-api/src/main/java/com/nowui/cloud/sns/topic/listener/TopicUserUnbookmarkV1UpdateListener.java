package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.router.TopicUserUnbookmarkRouter;
import com.nowui.cloud.sns.topic.view.TopicUserUnbookmarkView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题取消收藏新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicUserUnbookmarkV1UpdateListener {

    private final String queueName = "topic_user_unbookmark_v1_update";

    @Autowired
    private TopicUserUnbookmarkService topicUserUnbookmarkService;

    @Bean
    Queue TopicUserUnbookmarkV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicUserUnbookmarkV1UpdateQueueBindingExchange(Queue TopicUserUnbookmarkV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicUserUnbookmarkV1UpdateQueue).to(exchange).with(TopicUserUnbookmarkRouter.TOPIC_USER_UNBOOKMARK_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicUserUnbookmarkV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicUserUnbookmarkV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicUserUnbookmarkV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicUserUnbookmarkView topicUserUnbookmarkView = JSON.parseObject(message, TopicUserUnbookmarkView.class);

                topicUserUnbookmarkService.update(topicUserUnbookmarkView);
            }

        };
    }

}
