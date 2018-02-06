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
public class ArticleCategoryV1SaveListener {

    private final String queueName = "article_category_v1_save";

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Bean
    Queue ArticleCategoryV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleCategoryV1SaveQueueBindingExchange(Queue ArticleCategoryV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleCategoryV1SaveQueue).to(exchange).with(ArticleCategoryRouter.ARTICLE_CATEGORY_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleCategoryV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleCategoryV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleCategoryV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleCategoryView articleCategoryView = JSON.parseObject(message, ArticleCategoryView.class);

                articleCategoryService.save(articleCategoryView);
            }

        };
    }

}
