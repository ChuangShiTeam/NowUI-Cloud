package com.nowui.cloud.base.sms.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.sms.router.SmsCaptchaRouter;
import com.nowui.cloud.base.sms.view.SmsCaptchaView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;

/**
 * 短信验证码新增消息队列
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Configuration
public class SmsCaptchaV1SaveListener {

    private final String queueName = "sms_captcha_v1_save";

    @Bean
    Queue SmsCaptchaV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding SmsCaptchaV1SaveQueueBindingExchange(Queue SmsCaptchaV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(SmsCaptchaV1SaveQueue).to(exchange).with(SmsCaptchaRouter.SMS_CAPTCHA_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer SmsCaptchaV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(SmsCaptchaV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener SmsCaptchaV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                SmsCaptchaView smsCaptchaView = JSON.parseObject(message, SmsCaptchaView.class);

                System.out.println(smsCaptchaView.toJSONString());
            }

        };
    }

}
