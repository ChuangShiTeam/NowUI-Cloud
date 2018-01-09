package com.nowui.cloud.base.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    
    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    
    @Bean
    Queue queueUserSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserAccountSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.account.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserAccountUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.account.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserAvatarSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.avatar.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserAvatarUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.avatar.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserEmailSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.email.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserEmailUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.email.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserIdcardSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.idcard.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserIdcardUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.idcard.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserMobileSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.mobile.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserMobileUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.mobile.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserNickNameSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.nickName.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserNickNameUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.nickName.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
    
    @Bean
    Queue queueUserWechatSave(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.wechat.save", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueUserWechatUpdate(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("topic.user.wechat.update", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding bindingExchangeUserSave(Queue queueUserSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserSave).to(exchange).with("topic.user.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserUpdate(Queue queueUserUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserUpdate).to(exchange).with("topic.user.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserAccountSave(Queue queueUserAccountSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserAccountSave).to(exchange).with("topic.user.account.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserAccountUpdate(Queue queueUserAccountUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserAccountUpdate).to(exchange).with("topic.user.account.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserAvatarSave(Queue queueUserAvatarSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserAvatarSave).to(exchange).with("topic.user.avatar.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserAvatarUpdate(Queue queueUserAvatarUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserAvatarUpdate).to(exchange).with("topic.user.avatar.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserEmailSave(Queue queueUserEmailSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserEmailSave).to(exchange).with("topic.user.email.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserEmailUpdate(Queue queueUserEmailUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserEmailUpdate).to(exchange).with("topic.user.email.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserIdcardSave(Queue queueUserIdcardSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserIdcardSave).to(exchange).with("topic.user.idcard.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserIdcardUpdate(Queue queueUserIdcardUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserIdcardUpdate).to(exchange).with("topic.user.idcard.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserMobileSave(Queue queueUserMobileSave, TopicExchange exchange,RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserMobileSave).to(exchange).with("topic.user.mobile.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserMobileUpdate(Queue queueUserMobileUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserMobileUpdate).to(exchange).with("topic.user.mobile.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserNickNameSave(Queue queueUserNickNameSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserNickNameSave).to(exchange).with("topic.user.nickName.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserNickNameUpdate(Queue queueUserNickNameUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserNickNameUpdate).to(exchange).with("topic.user.nickName.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }
    
    @Bean
    Binding bindingExchangeUserWechatSave(Queue queueUserWechatSave, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserWechatSave).to(exchange).with("topic.user.wechat.save");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeUserWechatUpdate(Queue queueUserWechatUpdate, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueUserWechatUpdate).to(exchange).with("topic.user.wechat.update");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}
