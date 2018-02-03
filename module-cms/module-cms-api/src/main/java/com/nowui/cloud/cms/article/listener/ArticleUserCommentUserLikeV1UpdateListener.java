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
public class ArticleUserCommentUserLikeV1UpdateListener {

    private final String queueName = "article_user_comment_user_like_v1_update";

    @Autowired
    private ArticleUserCommentUserLikeService articleUserCommentUserLikeService;

    @Bean
    Queue ArticleUserCommentUserLikeV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleUserCommentUserLikeV1UpdateQueueBindingExchange(Queue ArticleUserCommentUserLikeV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleUserCommentUserLikeV1UpdateQueue).to(exchange).with(ArticleUserCommentUserLikeRouter.ARTICLE_USER_COMMENT_USER_LIKE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleUserCommentUserLikeV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleUserCommentUserLikeV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleUserCommentUserLikeV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleUserCommentUserLikeView articleUserCommentUserLikeView = JSON.parseObject(message, ArticleUserCommentUserLikeView.class);

                articleUserCommentUserLikeService.update(articleUserCommentUserLikeView);
            }

        };
    }

}
