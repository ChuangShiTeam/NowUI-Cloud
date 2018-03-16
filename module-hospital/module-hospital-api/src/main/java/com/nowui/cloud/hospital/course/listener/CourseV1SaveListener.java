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
public class CourseV1SaveListener {

    private final String queueName = "course_v1_save";

    @Autowired
    private CourseService courseService;

    @Bean
    Queue CourseV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding CourseV1SaveQueueBindingExchange(Queue CourseV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(CourseV1SaveQueue).to(exchange).with(CourseRouter.COURSE_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer CourseV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(CourseV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener CourseV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                CourseView courseView = JSON.parseObject(message, CourseView.class);

                courseService.save(courseView);
            }

        };
    }

}
