package com.nowui.cloud.hospital.course.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.hospital.course.service.CourseCategoryService;
import com.nowui.cloud.hospital.course.router.CourseCategoryRouter;
import com.nowui.cloud.hospital.course.view.CourseCategoryView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 课程分类新增消息队列
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
//@Configuration
public class CourseCategoryV1SaveListener {

    private final String queueName = "course_category_v1_save";

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Bean
    Queue CourseCategoryV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding CourseCategoryV1SaveQueueBindingExchange(Queue CourseCategoryV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(CourseCategoryV1SaveQueue).to(exchange).with(CourseCategoryRouter.COURSE_CATEGORY_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer CourseCategoryV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(CourseCategoryV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener CourseCategoryV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                CourseCategoryView courseCategoryView = JSON.parseObject(message, CourseCategoryView.class);

                courseCategoryService.save(courseCategoryView);
            }

        };
    }

}
