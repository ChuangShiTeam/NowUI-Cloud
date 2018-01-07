package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.util.Util;

@Component
@RabbitListener(queues = "user")
public class UserReceiver {
    
    @Autowired
    private UserService userService;

    @RabbitHandler
    public void process(User user) {
        if ("save".equals(user.get("handleType"))) {
            System.out.println("收到消息：" + user.toJSONString());
            if (!Util.isNullOrEmpty(user.getUserId())) {
                userService.save(user, user.getUserId(), user.getSystemRequestUserId());
            }
        }
    }

}
