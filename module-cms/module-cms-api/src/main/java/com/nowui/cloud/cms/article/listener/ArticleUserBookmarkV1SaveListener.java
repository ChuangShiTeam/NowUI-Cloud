package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleUserBookmarkService;
import com.nowui.cloud.cms.article.router.ArticleUserBookmarkRouter;
import com.nowui.cloud.cms.article.view.ArticleUserBookmarkView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章用户收藏新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
//@Configuration
public class ArticleUserBookmarkV1SaveListener {

    private final String queueName = "article_user_bookmark_v1_save";

    @Autowired
    private ArticleUserBookmarkService articleUserBookmarkService;

    @Bean
    Queue ArticleUserBookmarkV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleUserBookmarkV1SaveQueueBindingExchange(Queue ArticleUserBookmarkV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleUserBookmarkV1SaveQueue).to(exchange).with(ArticleUserBookmarkRouter.ARTICLE_USER_BOOKMARK_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleUserBookmarkV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleUserBookmarkV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleUserBookmarkV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleUserBookmarkView articleUserBookmarkView = JSON.parseObject(message, ArticleUserBookmarkView.class);

                articleUserBookmarkService.save(articleUserBookmarkView);
            }

        };
    }

}
