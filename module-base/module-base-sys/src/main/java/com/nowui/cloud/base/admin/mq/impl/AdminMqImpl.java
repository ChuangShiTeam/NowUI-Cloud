package com.nowui.cloud.base.admin.mq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.admin.mq.AdminMq;

/**
 * 管理员发送消息接口实现
 * 
 * @author marcus
 *
 * 2018年1月7日
 */
@Component
public class AdminMqImpl implements AdminMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;


}
