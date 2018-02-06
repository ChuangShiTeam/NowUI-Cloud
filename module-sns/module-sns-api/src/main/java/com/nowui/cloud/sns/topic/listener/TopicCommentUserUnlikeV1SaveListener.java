package com.nowui.cloud.sns.topic.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.sns.topic.router.TopicCommentUserUnlikeRouter;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 话题评论的取消点赞新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class TopicCommentUserUnlikeV1SaveListener {

    private final String queueName = "topic_comment_user_unlike_v1_save";

    @Autowired
    private TopicCommentUserUnlikeService topicCommentUserUnlikeService;

    @Bean
    Queue TopicCommentUserUnlikeV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding TopicCommentUserUnlikeV1SaveQueueBindingExchange(Queue TopicCommentUserUnlikeV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(TopicCommentUserUnlikeV1SaveQueue).to(exchange).with(TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer TopicCommentUserUnlikeV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(TopicCommentUserUnlikeV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener TopicCommentUserUnlikeV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                TopicCommentUserUnlikeView topicCommentUserUnlikeView = JSON.parseObject(message, TopicCommentUserUnlikeView.class);

                topicCommentUserUnlikeService.save(topicCommentUserUnlikeView);
            }

        };
    }

}
