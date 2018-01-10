package com.nowui.cloud.shop.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhongYongQiang
 */
@Configuration
public class ProductConfig {

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    Queue queueFoo(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("queue.foo", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue queueBar(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("queue.bar", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Queue testBard(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue("test", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange("exchange");
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    @Bean
    Binding bindingExchangeFoo(Queue queueFoo, TopicExchange exchange,RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueFoo).to(exchange).with("test");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeBar(Queue queueBar, TopicExchange exchange,RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queueBar).to(exchange).with("queue.bar");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    Binding bindingExchangeBar2(Queue testBard, TopicExchange exchange,RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(testBard).to(exchange).with("test");
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

}
