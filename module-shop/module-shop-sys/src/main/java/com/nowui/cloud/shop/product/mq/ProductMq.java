package com.nowui.cloud.shop.product.mq;

/**
 * @author ZhongYongQiang
 */
public interface ProductMq {

    /**
     * 发送消息到队列
     * @param message 消息内容
     */
    void sendFoo2Rabbitmq(String message);

    /**
     * 发送消息到队列
     * @param message 消息内容
     */
    void sendBar2Rabbitmq(String message);

}
