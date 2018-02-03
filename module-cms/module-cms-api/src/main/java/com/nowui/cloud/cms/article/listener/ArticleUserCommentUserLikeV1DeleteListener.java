package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleUserCommentUserLikeService;
import com.nowui.cloud.cms.article.router.ArticleUserCommentUserLikeRouter;
import com.nowui.cloud.cms.article.view.ArticleUserCommentUserLikeView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章用户评论点赞新增消息队列
 *
 * @author x
 *
 * 2018-02-03
 */
@Configuration
public class ArticleUserCommentUserLikeV1DeleteListener {

    private final String queueName = "article_user_comment_user_like_v1_delete";

    @Autowired
    private ArticleUserCommentUserLikeService articleUserCommentUserLikeService;

    @Bean
    Queue ArticleUserCommentUserLikeV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleUserCommentUserLikeV1DeleteQueueBindingExchange(Queue ArticleUserCommentUserLikeV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleUserCommentUserLikeV1DeleteQueue).to(exchange).with(ArticleUserCommentUserLikeRouter.ARTICLE_USER_COMMENT_USER_LIKE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleUserCommentUserLikeV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleUserCommentUserLikeV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleUserCommentUserLikeV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleUserCommentUserLikeView articleUserCommentUserLikeView = JSON.parseObject(message, ArticleUserCommentUserLikeView.class);

                articleUserCommentUserLikeService.save(articleUserCommentUserLikeView);
            }

        };
    }

}
