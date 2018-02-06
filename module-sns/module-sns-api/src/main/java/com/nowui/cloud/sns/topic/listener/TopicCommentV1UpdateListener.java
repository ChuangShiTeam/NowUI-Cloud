package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.router.TopicCommentRouter;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题评论新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicCommentV1UpdateListener {

    private final String queueName = "topic_comment_v1_update";

    @Autowired
    private TopicCommentService topicCommentService;

    @Bean
    Queue TopicCommentV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicCommentV1UpdateQueueBindingExchange(Queue TopicCommentV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicCommentV1UpdateQueue).to(exchange).with(TopicCommentRouter.TOPIC_COMMENT_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicCommentV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicCommentV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicCommentV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicCommentView topicCommentView = JSON.parseObject(message, TopicCommentView.class);

                topicCommentService.update(topicCommentView);
            }

        };
    }

}
