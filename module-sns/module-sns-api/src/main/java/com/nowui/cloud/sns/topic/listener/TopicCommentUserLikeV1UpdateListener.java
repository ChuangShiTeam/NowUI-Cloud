package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.router.TopicCommentUserLikeRouter;
import com.nowui.cloud.sns.topic.view.TopicCommentUserLikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题评论的点赞新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicCommentUserLikeV1UpdateListener {

    private final String queueName = "topic_comment_user_like_v1_update";

    @Autowired
    private TopicCommentUserLikeService topicCommentUserLikeService;

    @Bean
    Queue TopicCommentUserLikeV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicCommentUserLikeV1UpdateQueueBindingExchange(Queue TopicCommentUserLikeV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicCommentUserLikeV1UpdateQueue).to(exchange).with(TopicCommentUserLikeRouter.TOPIC_COMMENT_USER_LIKE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicCommentUserLikeV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicCommentUserLikeV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicCommentUserLikeV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicCommentUserLikeView topicCommentUserLikeView = JSON.parseObject(message, TopicCommentUserLikeView.class);

                topicCommentUserLikeService.update(topicCommentUserLikeView);
            }

        };
    }

}
