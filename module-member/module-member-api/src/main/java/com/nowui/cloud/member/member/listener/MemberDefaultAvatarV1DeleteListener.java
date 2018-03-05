package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberDefaultAvatarService;
import com.nowui.cloud.member.member.router.MemberDefaultAvatarRouter;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户默认头像新增消息队列
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
//@Configuration
public class MemberDefaultAvatarV1DeleteListener {

    private final String queueName = "member_default_avatar_v1_delete";

    @Autowired
    private MemberDefaultAvatarService memberDefaultAvatarService;

    @Bean
    Queue MemberDefaultAvatarV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberDefaultAvatarV1DeleteQueueBindingExchange(Queue MemberDefaultAvatarV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberDefaultAvatarV1DeleteQueue).to(exchange).with(MemberDefaultAvatarRouter.MEMBER_DEFAULT_AVATAR_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberDefaultAvatarV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberDefaultAvatarV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberDefaultAvatarV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberDefaultAvatarView memberDefaultAvatarView = JSON.parseObject(message, MemberDefaultAvatarView.class);

                memberDefaultAvatarService.save(memberDefaultAvatarView);
            }

        };
    }

}
