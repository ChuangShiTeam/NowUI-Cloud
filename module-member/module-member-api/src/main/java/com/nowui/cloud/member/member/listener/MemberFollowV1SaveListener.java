package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberFollowService;
import com.nowui.cloud.member.member.router.MemberFollowRouter;
import com.nowui.cloud.member.member.view.MemberFollowView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 关注会员信息新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Configuration
public class MemberFollowV1SaveListener {

    private final String queueName = "member_follow_v1_save";

    @Autowired
    private MemberFollowService memberFollowService;

    @Bean
    Queue MemberFollowV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberFollowV1SaveQueueBindingExchange(Queue MemberFollowV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberFollowV1SaveQueue).to(exchange).with(MemberFollowRouter.MEMBER_FOLLOW_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberFollowV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberFollowV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberFollowV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberFollowView memberFollowView = JSON.parseObject(message, MemberFollowView.class);

                memberFollowService.save(memberFollowView);
            }

        };
    }

}
