package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.service.UserService;

@Component
public class UserListener {
    
    @Autowired
    private UserService userService;

    @RabbitListener(queues = "topic.user.save")
    public void receiveUserSave(String message) {
        User user = JSONObject.parseObject(message, User.class);
        System.out.println("收到用户消息：" + user.toJSONString());
    }

    @RabbitListener(queues = "topic.user.account.save")
    public void receiveUserAccountSave(String message) {
        UserAccount userAccount = JSONObject.parseObject(message, UserAccount.class);
        System.out.println("收到用户账号消息：" + userAccount.toJSONString());
    }
    
    @RabbitListener(queues = "topic.user.email.save")
    public void receiveUserEmailSave(String message) {
        UserEmail userEmail = JSONObject.parseObject(message, UserEmail.class);
        System.out.println("收到用户邮箱消息：" + userEmail.toJSONString());
    }
    

}
