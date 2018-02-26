package com.nowui.cloud.member.member.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.router.UserSexRouter;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.rabbit.RabbitListener;

public class MemberV1UserSexUpdateListener {
    
    private final String queueName = "member_v1_user_sex_update";

    @Autowired
    private MemberService memberService;

    @Bean
    Queue MemberV1UserSexUpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberV1UserSexUpdateQueueBindingExchange(Queue MemberV1UserSexUpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberV1UserSexUpdateQueue).to(exchange).with(UserSexRouter.USER_SEX_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberV1UserSexUpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberV1UserSexUpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberV1UserSexUpdateMessageListener() {
        return new RabbitListener() {
            @Override
            public void receive(String message) {
                UserSex userSex = JSON.parseObject(message, UserSex.class);
                
                MemberView memberView = memberService.findByUserId(userSex.getUserId());
                
                memberView.setUserSex(userSex.getUserSex());
                
                memberService.update(memberView);
            }
        };
    }

}
