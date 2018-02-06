package com.nowui.cloud.wawi.pet.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;
import com.nowui.cloud.wawi.pet.router.PetCategoryRouter;
import com.nowui.cloud.wawi.pet.view.PetCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 宠物分类新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
//@Configuration
public class PetCategoryV1UpdateListener {

    private final String queueName = "pet_category_v1_update";

    @Autowired
    private PetCategoryService petCategoryService;

    @Bean
    Queue PetCategoryV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding PetCategoryV1UpdateQueueBindingExchange(Queue PetCategoryV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(PetCategoryV1UpdateQueue).to(exchange).with(PetCategoryRouter.PET_CATEGORY_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer PetCategoryV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(PetCategoryV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener PetCategoryV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                PetCategoryView petCategoryView = JSON.parseObject(message, PetCategoryView.class);

                petCategoryService.update(petCategoryView);
            }

        };
    }

}
