package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberPerferenceLanguageService;
import com.nowui.cloud.member.member.router.MemberPerferenceLanguageRouter;
import com.nowui.cloud.member.member.view.MemberPerferenceLanguageView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员偏好语言新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
//@Configuration
public class MemberPerferenceLanguageV1UpdateListener {

    private final String queueName = "member_perference_language_v1_update";

    @Autowired
    private MemberPerferenceLanguageService memberPerferenceLanguageService;

    @Bean
    Queue MemberPerferenceLanguageV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberPerferenceLanguageV1UpdateQueueBindingExchange(Queue MemberPerferenceLanguageV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberPerferenceLanguageV1UpdateQueue).to(exchange).with(MemberPerferenceLanguageRouter.MEMBER_PERFERENCE_LANGUAGE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberPerferenceLanguageV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberPerferenceLanguageV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberPerferenceLanguageV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberPerferenceLanguageView memberPerferenceLanguageView = JSON.parseObject(message, MemberPerferenceLanguageView.class);

                memberPerferenceLanguageService.update(memberPerferenceLanguageView);
            }

        };
    }

}
