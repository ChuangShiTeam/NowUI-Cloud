package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleUserLikeService;
import com.nowui.cloud.cms.article.router.ArticleUserLikeRouter;
import com.nowui.cloud.cms.article.view.ArticleUserLikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章用户点赞新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Configuration
public class ArticleUserLikeV1DeleteListener {

    private final String queueName = "article_user_like_v1_delete";

    @Autowired
    private ArticleUserLikeService articleUserLikeService;

    @Bean
    Queue ArticleUserLikeV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleUserLikeV1DeleteQueueBindingExchange(Queue ArticleUserLikeV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleUserLikeV1DeleteQueue).to(exchange).with(ArticleUserLikeRouter.ARTICLE_USER_LIKE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleUserLikeV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleUserLikeV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleUserLikeV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleUserLikeView articleUserLikeView = JSON.parseObject(message, ArticleUserLikeView.class);

                articleUserLikeService.save(articleUserLikeView);
            }

        };
    }

}
