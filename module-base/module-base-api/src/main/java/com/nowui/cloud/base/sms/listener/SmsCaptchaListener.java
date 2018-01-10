package com.nowui.cloud.base.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 短信验证码消息队列接口
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component
//@RabbitListener(queues = "smsCaptcha")
public class SmsCaptchaListener {

    @RabbitHandler
    public void process(String message) {

    }

}
