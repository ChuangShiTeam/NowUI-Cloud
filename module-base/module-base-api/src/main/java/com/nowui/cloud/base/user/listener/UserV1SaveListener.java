package com.nowui.cloud.base.user.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.router.UserRouter;
import com.nowui.cloud.base.user.view.UserView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class UserV1SaveListener {

    private final String queueName = "user_v1_save";

    @Autowired
    private UserService userService;

    @Bean
    Queue UserV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserV1SaveQueueBindingExchange(Queue UserV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserV1SaveQueue).to(exchange).with(UserRouter.USER_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                UserView userView = JSON.parseObject(message, UserView.class);

                userService.save(userView);
            }

        };
    }

}
