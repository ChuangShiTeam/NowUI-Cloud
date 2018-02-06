package com.nowui.cloud.cms.article.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.article.service.ArticleAuditService;
import com.nowui.cloud.cms.article.router.ArticleAuditRouter;
import com.nowui.cloud.cms.article.view.ArticleAuditView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文章审核新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
//@Configuration
public class ArticleAuditV1DeleteListener {

    private final String queueName = "article_audit_v1_delete";

    @Autowired
    private ArticleAuditService articleAuditService;

    @Bean
    Queue ArticleAuditV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding ArticleAuditV1DeleteQueueBindingExchange(Queue ArticleAuditV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(ArticleAuditV1DeleteQueue).to(exchange).with(ArticleAuditRouter.ARTICLE_AUDIT_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer ArticleAuditV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(ArticleAuditV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener ArticleAuditV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                ArticleAuditView articleAuditView = JSON.parseObject(message, ArticleAuditView.class);

                articleAuditService.save(articleAuditView);
            }

        };
    }

}
