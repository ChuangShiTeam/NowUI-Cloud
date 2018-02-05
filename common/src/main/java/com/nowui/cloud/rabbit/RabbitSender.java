package com.nowui.cloud.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.event.service.EventService;
import com.nowui.cloud.exception.SystemException;
import com.nowui.cloud.util.Util;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送者
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
public class RabbitSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    protected EventService eventService;

    @Autowired
    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息确认成功22");
            eventService.delete(correlationData.getId());
        } else {
            //处理丢失的消息（nack）
            System.out.println("消息确认失败");
            System.out.println(correlationData.getId());
        }
    }

    //消息发送到RabbitMQ交换器，但无相应Exchange时的回调
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        //重新发布
    }

    public void send(String appId, String routing, BaseEntity baseEntity, String systemCreateUserId) {
        baseEntity.removeSystemValue();

        String message = baseEntity.toJSONString();

        String eventId = Util.getRandomUUID();

        Boolean success = eventService.save(eventId, appId, routing, message, systemCreateUserId);

        if (success) {
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setMessageId(eventId);
                    // 设置消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                }
            };

            rabbitTemplate.convertAndSend(Constant.EXCHANGE, routing, message, messagePostProcessor, new CorrelationData(eventId));
        } else {
            throw new SystemException("");
        }
    }

    public void send(String appId, String routing, JSONObject message, String systemCreateUserId) {
        String eventId = Util.getRandomUUID();

        Boolean success = eventService.save(eventId, appId, routing, message.toJSONString(), systemCreateUserId);

        if (success) {
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setMessageId(eventId);
                    // 设置消息持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                }
            };

            rabbitTemplate.convertAndSend(Constant.EXCHANGE, routing, message, messagePostProcessor, new CorrelationData(eventId));
        } else {
            throw new SystemException(Constant.ERROR);
        }
    }

}
