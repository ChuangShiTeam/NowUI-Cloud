package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.router.ArticleCategoryRouter;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章分类新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
//@Configuration
public class ArticleCategoryV1UpdateListener {

    private final String queueName = "article_category_v1_update";

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Bean
    Queue ArticleCategoryV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleCategoryV1UpdateQueueBindingExchange(Queue ArticleCategoryV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleCategoryV1UpdateQueue).to(exchange).with(ArticleCategoryRouter.ARTICLE_CATEGORY_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleCategoryV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleCategoryV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleCategoryV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleCategoryView articleCategoryView = JSON.parseObject(message, ArticleCategoryView.class);

                articleCategoryService.update(articleCategoryView);
            }

        };
    }

}
