package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.router.TopicUserLikeRouter;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题点赞新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicUserLikeV1DeleteListener {

    private final String queueName = "topic_user_like_v1_delete";

    @Autowired
    private TopicUserLikeService topicUserLikeService;

    @Bean
    Queue TopicUserLikeV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicUserLikeV1DeleteQueueBindingExchange(Queue TopicUserLikeV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicUserLikeV1DeleteQueue).to(exchange).with(TopicUserLikeRouter.TOPIC_USER_LIKE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicUserLikeV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicUserLikeV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicUserLikeV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicUserLikeView topicUserLikeView = JSON.parseObject(message, TopicUserLikeView.class);

                topicUserLikeService.save(topicUserLikeView);
            }

        };
    }

}
