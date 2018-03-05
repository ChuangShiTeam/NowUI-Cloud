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
public class MemberDefaultAvatarV1UpdateListener {

    private final String queueName = "member_default_avatar_v1_update";

    @Autowired
    private MemberDefaultAvatarService memberDefaultAvatarService;

    @Bean
    Queue MemberDefaultAvatarV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberDefaultAvatarV1UpdateQueueBindingExchange(Queue MemberDefaultAvatarV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberDefaultAvatarV1UpdateQueue).to(exchange).with(MemberDefaultAvatarRouter.MEMBER_DEFAULT_AVATAR_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberDefaultAvatarV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberDefaultAvatarV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberDefaultAvatarV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberDefaultAvatarView memberDefaultAvatarView = JSON.parseObject(message, MemberDefaultAvatarView.class);

                memberDefaultAvatarService.update(memberDefaultAvatarView);
            }

        };
    }

}
