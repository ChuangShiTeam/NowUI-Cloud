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
public class MemberPerferenceLanguageV1DeleteListener {

    private final String queueName = "member_perference_language_v1_delete";

    @Autowired
    private MemberPerferenceLanguageService memberPerferenceLanguageService;

    @Bean
    Queue MemberPerferenceLanguageV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberPerferenceLanguageV1DeleteQueueBindingExchange(Queue MemberPerferenceLanguageV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberPerferenceLanguageV1DeleteQueue).to(exchange).with(MemberPerferenceLanguageRouter.MEMBER_PERFERENCE_LANGUAGE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberPerferenceLanguageV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberPerferenceLanguageV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberPerferenceLanguageV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberPerferenceLanguageView memberPerferenceLanguageView = JSON.parseObject(message, MemberPerferenceLanguageView.class);

                memberPerferenceLanguageService.save(memberPerferenceLanguageView);
            }

        };
    }

}
