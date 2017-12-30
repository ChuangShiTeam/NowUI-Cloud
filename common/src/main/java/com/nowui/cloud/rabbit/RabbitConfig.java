package com.nowui.cloud.rabbit;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue(Constant.QUEUE);
    }

}
