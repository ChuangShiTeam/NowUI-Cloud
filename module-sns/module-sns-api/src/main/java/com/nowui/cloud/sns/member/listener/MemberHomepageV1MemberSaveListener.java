package com.nowui.cloud.sns.member.listener;


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
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.member.member.router.MemberRouter;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.sns.member.service.MemberHomepageService;
import com.nowui.cloud.sns.member.view.MemberHomepageView;
import com.nowui.cloud.util.Util;


/**
 * 会员主页的用户信息消息队列
 * @author xupengfei
 *
 * 2018年3月4日
 */
@Configuration
public class MemberHomepageV1MemberSaveListener {
	private final String queueName = "member_v1_save";
	
	@Autowired
    private MemberHomepageService memberHomepageService;
	
	@Bean
    Queue MemberHomepageV1MemberSaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberHomepageV1MemberSaveQueueBindingExchange(Queue MemberFollowV1UserAvatarUpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberFollowV1UserAvatarUpdateQueue).to(exchange).with(MemberRouter.MEMBER_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberHomepageV1MemberSaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberHomepageV1MemberSaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberHomepageV1MemberSaveMessageListener() {
        return new RabbitListener() {
            @Override
            public void receive(String message) {
            	MemberHomepageView homepageView = JSON.parseObject(message, MemberHomepageView.class);
            	
            	homepageView.setMemberHomepageId(Util.getRandomUUID());
            	homepageView.setMemberBeFollowCount(0);
            	homepageView.setMemberFollowCount(0);
            	homepageView.setMemberSendTopicCount(0);
            	homepageView.setMemberSignature("");
            	// TODO 个人主页背景图片
            	homepageView.setMemberBackgroundFilePath(homepageView.getUserAvatarFilePath());
            	
            	memberHomepageService.save(homepageView);
            }
        };
    }
}
