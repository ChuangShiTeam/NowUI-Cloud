package com.nowui.cloud.wawi.pet.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.wawi.pet.service.PetService;
import com.nowui.cloud.wawi.pet.router.PetRouter;
import com.nowui.cloud.wawi.pet.view.PetView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 宠物新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class PetV1DeleteListener {

    private final String queueName = "pet_v1_delete";

    @Autowired
    private PetService petService;

    @Bean
    Queue PetV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding PetV1DeleteQueueBindingExchange(Queue PetV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(PetV1DeleteQueue).to(exchange).with(PetRouter.PET_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer PetV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(PetV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener PetV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                PetView petView = JSON.parseObject(message, PetView.class);

                petService.save(petView);
            }

        };
    }

}
