package com.nowui.cloud.rabbit;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息接收者
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class RabbitListener implements ChannelAwareMessageListener {

    @Autowired
    private Jackson2JsonMessageConverter messageConverter;

    /**
     * 接收消息
     *
     * @param message 消息对象
     */
    public void receive(String message) {

    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        Long deliveryTag = messageProperties.getDeliveryTag();

        try {
            receive(messageConverter.fromMessage(message).toString());

            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            e.printStackTrace();

            //消息消费失败
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
