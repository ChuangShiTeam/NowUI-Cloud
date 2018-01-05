package com.nowui.cloud.base.user.mq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.user.mq.UserMq;

@Component
public class UserMqImpl implements UserMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
	@Override
	public void sendSave(String message) {
		
	}

}
