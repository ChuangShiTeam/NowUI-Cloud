package com.nowui.cloud.event.config;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Configuration
public class EventConfig {

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(Constant.EXCHANGE);
        rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

}
