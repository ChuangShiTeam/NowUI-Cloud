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
public class CourseV1UpdateListener {

    private final String queueName = "course_v1_update";

    @Autowired
    private CourseService courseService;

    @Bean
    Queue CourseV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding CourseV1UpdateQueueBindingExchange(Queue CourseV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(CourseV1UpdateQueue).to(exchange).with(CourseRouter.COURSE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer CourseV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(CourseV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener CourseV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                CourseView courseView = JSON.parseObject(message, CourseView.class);

                courseService.update(courseView, courseView.getCourseId());
            }

        };
    }

}
