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
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.router.UserNickNameRouter;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.rabbit.RabbitListener;

/**
 * 会员用户昵称消息队列
 * 
 * @author marcus
 *
 * 2018年2月25日
 */
@Configuration
public class MemberV1UserNickNameUpdateListener {
    
    private final String queueName = "member_v1_user_nick_name_update";

    @Autowired
    private MemberService memberService;

    @Bean
    Queue MemberV1UserNickNameUpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberV1UserNickNameUpdateQueueBindingExchange(Queue MemberV1UserNickNameUpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberV1UserNickNameUpdateQueue).to(exchange).with(UserNickNameRouter.USER_NICK_NAME_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberV1UserNickNameUpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberV1UserNickNameUpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberV1UserNickNameUpdateMessageListener() {
        return new RabbitListener() {
            @Override
            public void receive(String message) {
                UserNickName userNickName = JSON.parseObject(message, UserNickName.class);
                
                MemberView memberView = memberService.findByUserId(userNickName.getUserId());
                
                memberView.setUserNickName(userNickName.getUserNickName());
                
                memberService.update(memberView);
            }
        };
    }

}
