package com.nowui.cloud.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * 消息接收者
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class RabbitListener implements ChannelAwareMessageListener {

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
            receive(new String(message.getBody(),"UTF-8"));

            channel.basicAck(deliveryTag,false);
        } catch (Exception e) {
            //消息消费失败

        }
    }
}
