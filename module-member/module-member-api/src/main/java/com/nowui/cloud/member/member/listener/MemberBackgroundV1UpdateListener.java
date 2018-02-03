package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.member.member.router.MemberBackgroundRouter;
import com.nowui.cloud.member.member.view.MemberBackgroundView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员背景	新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Configuration
public class MemberBackgroundV1UpdateListener {

    private final String queueName = "member_background_v1_update";

    @Autowired
    private MemberBackgroundService memberBackgroundService;

    @Bean
    Queue MemberBackgroundV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberBackgroundV1UpdateQueueBindingExchange(Queue MemberBackgroundV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberBackgroundV1UpdateQueue).to(exchange).with(MemberBackgroundRouter.MEMBER_BACKGROUND_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberBackgroundV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberBackgroundV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberBackgroundV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberBackgroundView memberBackgroundView = JSON.parseObject(message, MemberBackgroundView.class);

                memberBackgroundService.update(memberBackgroundView);
            }

        };
    }

}
