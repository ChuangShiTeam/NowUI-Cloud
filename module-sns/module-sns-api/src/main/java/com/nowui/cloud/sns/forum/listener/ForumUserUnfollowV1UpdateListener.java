package com.nowui.cloud.sns.forum.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.router.ForumUserUnfollowRouter;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 论坛取消关注新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class ForumUserUnfollowV1UpdateListener {

    private final String queueName = "forum_user_unfollow_v1_update";

    @Autowired
    private ForumUserUnfollowService forumUserUnfollowService;

    @Bean
    Queue ForumUserUnfollowV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ForumUserUnfollowV1UpdateQueueBindingExchange(Queue ForumUserUnfollowV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ForumUserUnfollowV1UpdateQueue).to(exchange).with(ForumUserUnfollowRouter.FORUM_USER_UNFOLLOW_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ForumUserUnfollowV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ForumUserUnfollowV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ForumUserUnfollowV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ForumUserUnfollowView forumUserUnfollowView = JSON.parseObject(message, ForumUserUnfollowView.class);

                forumUserUnfollowService.update(forumUserUnfollowView);
            }

        };
    }

}
