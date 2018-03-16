package com.nowui.cloud.hospital.course.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.hospital.course.service.CourseService;
import com.nowui.cloud.hospital.course.router.CourseRouter;
import com.nowui.cloud.hospital.course.view.CourseView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 课程新增消息队列
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
//@Configuration
public class CourseV1DeleteListener {

    private final String queueName = "course_v1_delete";

    @Autowired
    private CourseService courseService;

    @Bean
    Queue CourseV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding CourseV1DeleteQueueBindingExchange(Queue CourseV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(CourseV1DeleteQueue).to(exchange).with(CourseRouter.COURSE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer CourseV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(CourseV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener CourseV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                CourseView courseView = JSON.parseObject(message, CourseView.class);

                courseService.save(courseView);
            }

        };
    }

}
