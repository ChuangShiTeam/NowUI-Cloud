package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import com.nowui.cloud.cms.article.router.ArticleArticleCategoryRouter;
import com.nowui.cloud.cms.article.view.ArticleArticleCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章分类关联新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Configuration
public class ArticleArticleCategoryV1SaveListener {

    private final String queueName = "article_article_category_v1_save";

    @Autowired
    private ArticleArticleCategoryService articleArticleCategoryService;

    @Bean
    Queue ArticleArticleCategoryV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleArticleCategoryV1SaveQueueBindingExchange(Queue ArticleArticleCategoryV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleArticleCategoryV1SaveQueue).to(exchange).with(ArticleArticleCategoryRouter.ARTICLE_ARTICLE_CATEGORY_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleArticleCategoryV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleArticleCategoryV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleArticleCategoryV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleArticleCategoryView articleArticleCategoryView = JSON.parseObject(message, ArticleArticleCategoryView.class);

                articleArticleCategoryService.save(articleArticleCategoryView);
            }

        };
    }

}
