package com.nowui.cloud.base.admin.mq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.admin.mq.AdminMq;
import com.nowui.cloud.base.user.entity.User;

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


    @Override
    public void sendSaveUser(User user) {
        user.put("handleType", "save"); 
        System.out.println("发送保存用户消息: " + user.toJSONString());
        rabbitTemplate.convertAndSend("user", user);
    }

}
