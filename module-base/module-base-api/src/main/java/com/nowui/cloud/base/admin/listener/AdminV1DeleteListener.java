package com.nowui.cloud.base.admin.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.router.AdminRouter;
import com.nowui.cloud.base.admin.view.AdminView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 管理员新增消息队列
 *
 * @author marcus
 *
 * 2018-02-03
 */
//@Configuration
public class AdminV1DeleteListener {

    private final String queueName = "admin_v1_delete";

    @Autowired
    private AdminService adminService;

    @Bean
    Queue AdminV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding AdminV1DeleteQueueBindingExchange(Queue AdminV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(AdminV1DeleteQueue).to(exchange).with(AdminRouter.ADMIN_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer AdminV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(AdminV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener AdminV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                AdminView adminView = JSON.parseObject(message, AdminView.class);

                adminService.save(adminView);
            }

        };
    }

}
