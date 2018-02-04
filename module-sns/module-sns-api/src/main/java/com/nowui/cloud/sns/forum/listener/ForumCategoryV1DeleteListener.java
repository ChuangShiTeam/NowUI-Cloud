package com.nowui.cloud.sns.forum.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.forum.service.ForumCategoryService;
import com.nowui.cloud.sns.forum.router.ForumCategoryRouter;
import com.nowui.cloud.sns.forum.view.ForumCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 论坛分类新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Configuration
public class ForumCategoryV1DeleteListener {

    private final String queueName = "forum_category_v1_delete";

    @Autowired
    private ForumCategoryService forumCategoryService;

    @Bean
    Queue ForumCategoryV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ForumCategoryV1DeleteQueueBindingExchange(Queue ForumCategoryV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ForumCategoryV1DeleteQueue).to(exchange).with(ForumCategoryRouter.FORUM_CATEGORY_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ForumCategoryV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ForumCategoryV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ForumCategoryV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ForumCategoryView forumCategoryView = JSON.parseObject(message, ForumCategoryView.class);

                forumCategoryService.save(forumCategoryView);
            }

        };
    }

}
