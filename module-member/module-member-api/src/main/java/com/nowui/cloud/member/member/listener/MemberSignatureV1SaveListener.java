package com.nowui.cloud.member.member.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.member.member.router.MemberSignatureRouter;
import com.nowui.cloud.member.member.view.MemberSignatureView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 会员签名	新增消息队列
 *
 * @author shawn
 *
 * 2018-02-03
 */
//@Configuration
public class MemberSignatureV1SaveListener {

    private final String queueName = "member_signature_v1_save";

    @Autowired
    private MemberSignatureService memberSignatureService;

    @Bean
    Queue MemberSignatureV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding MemberSignatureV1SaveQueueBindingExchange(Queue MemberSignatureV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(MemberSignatureV1SaveQueue).to(exchange).with(MemberSignatureRouter.MEMBER_SIGNATURE_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer MemberSignatureV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(MemberSignatureV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener MemberSignatureV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                MemberSignatureView memberSignatureView = JSON.parseObject(message, MemberSignatureView.class);

                memberSignatureService.save(memberSignatureView);
            }

        };
    }

}
