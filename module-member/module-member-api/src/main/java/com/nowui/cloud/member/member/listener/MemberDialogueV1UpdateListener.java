package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberDialogueService;
import com.nowui.cloud.member.member.router.MemberDialogueRouter;
import com.nowui.cloud.member.member.view.MemberDialogueView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员对话新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
//@Configuration
public class MemberDialogueV1UpdateListener {

    private final String queueName = "member_dialogue_v1_update";

    @Autowired
    private MemberDialogueService memberDialogueService;

    @Bean
    Queue MemberDialogueV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberDialogueV1UpdateQueueBindingExchange(Queue MemberDialogueV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberDialogueV1UpdateQueue).to(exchange).with(MemberDialogueRouter.MEMBER_DIALOGUE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberDialogueV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberDialogueV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberDialogueV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberDialogueView memberDialogueView = JSON.parseObject(message, MemberDialogueView.class);

                memberDialogueService.update(memberDialogueView);
            }

        };
    }

}
