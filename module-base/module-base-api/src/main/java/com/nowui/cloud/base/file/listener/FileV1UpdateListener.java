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
public class FileV1UpdateListener {

    private final String queueName = "file_v1_update";

    @Autowired
    private FileService fileService;

    @Bean
    Queue FileV1UpdateQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding FileV1UpdateQueueBindingExchange(Queue FileV1UpdateQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(FileV1UpdateQueue).to(exchange).with(FileRouter.FILE_V1_UPDATE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer FileV1UpdateMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(FileV1UpdateMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener FileV1UpdateMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                FileView fileView = JSON.parseObject(message, FileView.class);

                fileService.update(fileView);
            }

        };
    }

}
