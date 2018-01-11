package com.nowui.cloud.base.sms.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfig {
    
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    
    @Bean
    Queue queueSmsCaptchaAliyunSend(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.sms.captcha.aliyun.send", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Binding bindingExchangeUserWechatUpdate(Queue queueSmsCaptchaSend, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueSmsCaptchaSend).to(exchange).with("topic.sms.captcha.aliyun.send");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }


}
