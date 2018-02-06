package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleUserCommentService;
import com.nowui.cloud.cms.article.router.ArticleUserCommentRouter;
import com.nowui.cloud.cms.article.view.ArticleUserCommentView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章用户评论新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
//@Configuration
public class ArticleUserCommentV1SaveListener {

    private final String queueName = "article_user_comment_v1_save";

    @Autowired
    private ArticleUserCommentService articleUserCommentService;

    @Bean
    Queue ArticleUserCommentV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleUserCommentV1SaveQueueBindingExchange(Queue ArticleUserCommentV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleUserCommentV1SaveQueue).to(exchange).with(ArticleUserCommentRouter.ARTICLE_USER_COMMENT_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleUserCommentV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleUserCommentV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleUserCommentV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleUserCommentView articleUserCommentView = JSON.parseObject(message, ArticleUserCommentView.class);

                articleUserCommentService.save(articleUserCommentView);
            }

        };
    }

}
