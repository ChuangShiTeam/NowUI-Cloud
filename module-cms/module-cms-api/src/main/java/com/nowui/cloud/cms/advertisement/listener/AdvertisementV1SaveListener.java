package com.nowui.cloud.cms.advertisement.listener;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.file.view.FileView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.cms.advertisement.router.AdvertisementRouter;
import com.nowui.cloud.cms.advertisement.view.AdvertisementView;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广告新增消息队列
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Configuration
public class AdvertisementV1SaveListener {

    private final String queueName = "advertisement_v1_save";

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private FileRpc fileRpc;

    @Bean
    Queue AdvertisementV1SaveQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding AdvertisementV1SaveQueueBindingExchange(Queue AdvertisementV1SaveQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(AdvertisementV1SaveQueue).to(exchange).with(AdvertisementRouter.ADVERTISEMENT_V1_SAVE);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer AdvertisementV1SaveMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(AdvertisementV1SaveMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener AdvertisementV1SaveMessageListener() {
        return new RabbitListener() {

            @Override
            public void receive(String message) {
                AdvertisementView advertisementView = JSON.parseObject(message, AdvertisementView.class);

                FileView fileView = fileRpc.findV1(advertisementView.getAdvertisementId());
                advertisementView.setAdvertisementImage(fileView);

                advertisementService.save(advertisementView);
            }

        };
    }

}
