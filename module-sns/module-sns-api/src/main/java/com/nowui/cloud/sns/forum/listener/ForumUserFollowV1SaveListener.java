package com.nowui.cloud.sns.forum.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 论坛用户关注新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class ForumUserFollowV1SaveListener {

    private final String queueName = "forum_user_follow_v1_save";

    @Autowired
    private ForumUserFollowService forumUserFollowService;

    @Bean
    Queue ForumUserFollowV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ForumUserFollowV1SaveQueueBindingExchange(Queue ForumUserFollowV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ForumUserFollowV1SaveQueue).to(exchange).with(ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ForumUserFollowV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ForumUserFollowV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ForumUserFollowV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ForumUserFollowView forumUserFollowView = JSON.parseObject(message, ForumUserFollowView.class);

                forumUserFollowService.save(forumUserFollowView);
            }

        };
    }

}
