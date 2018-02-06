package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberInformService;
import com.nowui.cloud.member.member.router.MemberInformRouter;
import com.nowui.cloud.member.member.view.MemberInformView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员举报	新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
//@Configuration
public class MemberInformV1DeleteListener {

    private final String queueName = "member_inform_v1_delete";

    @Autowired
    private MemberInformService memberInformService;

    @Bean
    Queue MemberInformV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberInformV1DeleteQueueBindingExchange(Queue MemberInformV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberInformV1DeleteQueue).to(exchange).with(MemberInformRouter.MEMBER_INFORM_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberInformV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberInformV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberInformV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberInformView memberInformView = JSON.parseObject(message, MemberInformView.class);

                memberInformService.save(memberInformView);
            }

        };
    }

}
