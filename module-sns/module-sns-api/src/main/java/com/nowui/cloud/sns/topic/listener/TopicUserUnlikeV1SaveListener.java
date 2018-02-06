package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.router.TopicUserUnlikeRouter;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题取消点赞新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicUserUnlikeV1SaveListener {

    private final String queueName = "topic_user_unlike_v1_save";

    @Autowired
    private TopicUserUnlikeService topicUserUnlikeService;

    @Bean
    Queue TopicUserUnlikeV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicUserUnlikeV1SaveQueueBindingExchange(Queue TopicUserUnlikeV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicUserUnlikeV1SaveQueue).to(exchange).with(TopicUserUnlikeRouter.TOPIC_USER_UNLIKE_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicUserUnlikeV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicUserUnlikeV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicUserUnlikeV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicUserUnlikeView topicUserUnlikeView = JSON.parseObject(message, TopicUserUnlikeView.class);

                topicUserUnlikeService.save(topicUserUnlikeView);
            }

        };
    }

}
