package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 用户身份证消息队列接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component
@RabbitListener(queues = "userIdcard")
public class UserIdcardListener {

    @RabbitHandler
    public void process(String message) {

    }

}
