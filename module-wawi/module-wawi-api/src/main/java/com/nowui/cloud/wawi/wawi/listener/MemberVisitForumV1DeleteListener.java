package com.nowui.cloud.wawi.wawi.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.wawi.wawi.service.MemberVisitForumService;
import com.nowui.cloud.wawi.wawi.router.MemberVisitForumRouter;
import com.nowui.cloud.wawi.wawi.view.MemberVisitForumView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员访问圈子记录新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Configuration
public class MemberVisitForumV1DeleteListener {

    private final String queueName = "member_visit_forum_v1_delete";

    @Autowired
    private MemberVisitForumService memberVisitForumService;

    @Bean
    Queue MemberVisitForumV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberVisitForumV1DeleteQueueBindingExchange(Queue MemberVisitForumV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberVisitForumV1DeleteQueue).to(exchange).with(MemberVisitForumRouter.MEMBER_VISIT_FORUM_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberVisitForumV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberVisitForumV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberVisitForumV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberVisitForumView memberVisitForumView = JSON.parseObject(message, MemberVisitForumView.class);

                memberVisitForumService.save(memberVisitForumView);
            }

        };
    }

}
