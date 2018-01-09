package com.nowui.cloud.base.user.mq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.mq.UserMq;

/**
 * 用户发送消息接口实现
 * 
 * @author marcus
 *
 * 2018年1月9日
 */
@Component
public class UserMqImpl implements UserMq {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendSave(User body, String userId, String systemCreateUserId) {
        body.setUserId(userId);
        body.setSystemCreateUserId(systemCreateUserId);
        System.out.println("发送用户信息" + body.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.save", body.toJSONString());
    }

    @Override
    public void sendUpdate(User user, String userId, String systemUpdateUserId, Integer systemVersion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveAccount(UserAccount userAccount, String userId, String systemCreateUserId) {
        userAccount.setUserId(userId);
        userAccount.setSystemCreateUserId(systemCreateUserId);
        System.out.println("发送用户账号信息" + userAccount.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.account.save", userAccount.toJSONString());
    }

    @Override
    public void sendUpdateAccount(UserAccount userAccount, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveAvatar(UserAvatar userAvatar, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateAvatar(UserAccount userAccount, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveEmail(UserEmail userEmail, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateEmail(UserEmail userEmail, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveIdcard(UserIdcard userIdcard, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateIdcard(UserAccount userIdcard, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveMobile(UserMobile userMobile, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateMobile(UserMobile userMobile, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveNickName(UserNickName userNickName, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateNickName(UserNickName userNickName, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendSaveWechat(UserWechat userWechat, String userId, String systemCreateUserId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendUpdateWechat(UserWechat userWechat, String userId, String systemUpdateUserId) {
        // TODO Auto-generated method stub
        
    }

}
