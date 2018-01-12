package com.nowui.cloud.base.sms.mq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.sms.mq.SmsCaptchaMq;

/**
 * 短信验证码消息队列接口
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Component
public class SmsCaptchaMqImpl implements SmsCaptchaMq {

    @Autowired
    private RabbitTemplate rabbit;

}
