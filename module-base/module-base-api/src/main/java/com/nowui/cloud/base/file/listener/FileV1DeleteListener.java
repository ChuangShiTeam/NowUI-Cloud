package com.nowui.cloud.base.file.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.base.file.service.FileService;
import com.nowui.cloud.base.file.router.FileRouter;
import com.nowui.cloud.base.file.view.FileView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件新增消息队列
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Configuration
public class FileV1DeleteListener {

    private final String queueName = "file_v1_delete";

    @Autowired
    private FileService fileService;

    @Bean
    Queue FileV1DeleteQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding FileV1DeleteQueueBindingExchange(Queue FileV1DeleteQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(FileV1DeleteQueue).to(exchange).with(FileRouter.FILE_V1_DELETE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer FileV1DeleteMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(FileV1DeleteMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener FileV1DeleteMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                FileView fileView = JSON.parseObject(message, FileView.class);

                fileService.save(fileView);
            }

        };
    }

}
