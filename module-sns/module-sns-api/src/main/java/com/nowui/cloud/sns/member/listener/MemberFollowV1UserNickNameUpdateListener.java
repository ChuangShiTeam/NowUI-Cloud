package com.nowui.cloud.sns.member.listener;

import java.util.List;

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
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.member.service.MemberFollowService;
import com.nowui.cloud.sns.member.view.MemberFollowView;

/**
 * 会员关注用户昵称消息队列
 * 
 * @author marcus
 *
 * 2018年2月26日
 */
@Configuration
public class MemberFollowV1UserNickNameUpdateListener {
    
    private final String queueName = "member_follow_v1_user_nick_name_update";

    @Autowired
    private MemberFollowService memberFollowService;

    @Bean
    Queue MemberFollowV1UserNickNameUpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberFollowV1UserNickNameUpdateQueueBindingExchange(Queue MemberFollowV1UserNickNameUpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberFollowV1UserNickNameUpdateQueue).to(exchange).with(UserNickNameRouter.USER_NICK_NAME_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberFollowV1UserNickNameUpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberFollowV1UserNickNameUpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberFollowV1UserNickNameUpdateMessageListener() {
        return new RabbitListener() {
            @Override
            public void receive(String message) {
                UserNickName userNickName = JSON.parseObject(message, UserNickName.class);
                
                List<MemberFollowView> memberFollowViewList = memberFollowService.listByUserId(userNickName.getUserId());
                for (MemberFollowView memberFollowView : memberFollowViewList) {
                    memberFollowView.setUserNickName(userNickName.getUserNickName());
                    
                    memberFollowService.update(memberFollowView);
                }
                
                List<MemberFollowView> followMemberFollowViewList = memberFollowService.listByFollowUserId(userNickName.getUserId());
                for (MemberFollowView memberFollowView : followMemberFollowViewList) {
                    memberFollowView.setUserNickName(userNickName.getUserNickName());
                    
                    memberFollowService.update(memberFollowView);
                }
            }
        };
    }

}
