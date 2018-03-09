package com.nowui.cloud.sns.forum.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.forum.service.ForumBackgroundMediaService;
import com.nowui.cloud.sns.forum.router.ForumBackgroundMediaRouter;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 论坛背景新增消息队列
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
//@Configuration
public class ForumBackgroundMediaV1SaveListener {

    private final String queueName = "forum_background_media_v1_save";

    @Autowired
    private ForumBackgroundMediaService forumBackgroundMediaService;

    @Bean
    Queue ForumBackgroundMediaV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ForumBackgroundMediaV1SaveQueueBindingExchange(Queue ForumBackgroundMediaV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ForumBackgroundMediaV1SaveQueue).to(exchange).with(ForumBackgroundMediaRouter.FORUM_BACKGROUND_MEDIA_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ForumBackgroundMediaV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ForumBackgroundMediaV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ForumBackgroundMediaV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ForumBackgroundMediaView forumBackgroundMediaView = JSON.parseObject(message, ForumBackgroundMediaView.class);

                forumBackgroundMediaService.save(forumBackgroundMediaView);
            }

        };
    }

}
