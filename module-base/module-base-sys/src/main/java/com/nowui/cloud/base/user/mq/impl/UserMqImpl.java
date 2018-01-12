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
    public void sendSave(User user, String userId, String systemRequestUserId) {
        user.setUserId(userId);
        user.setSystemRequestUserId(systemRequestUserId);
        try {
            rabbitTemplate.convertAndSend("exchange", "topic.user.save", user.toJSONString());
            System.out.println("发送保存用户信息：" + user.toJSONString());
        } catch (Exception e) {
            //发送失败存储消息
        }
    }

    @Override
    public void sendUpdate(User user, String userId, String systemRequestUserId, Integer systemVersion) {
        user.setUserId(userId);
        user.setSystemRequestUserId(systemRequestUserId);
        user.setSystemVersion(systemVersion);
        System.out.println("发送更新用户信息：" + user.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.update", user.toJSONString());
    }

    @Override
    public void sendSaveAccount(UserAccount userAccount, String userId, String systemRequestUserId) {
        userAccount.setUserId(userId);
        userAccount.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户账号信息：" + userAccount.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.account.save", userAccount.toJSONString());
    }

    @Override
    public void sendUpdateAccount(UserAccount userAccount, String userId, String systemRequestUserId) {
        userAccount.setUserId(userId);
        userAccount.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户账号信息：" + userAccount.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.account.update", userAccount.toJSONString());
    }

    @Override
    public void sendSaveAvatar(UserAvatar userAvatar, String userId, String systemRequestUserId) {
        userAvatar.setUserId(userId);
        userAvatar.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户头像消息：" + userAvatar.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.avatar.save", userAvatar.toJSONString());
    }

    @Override
    public void sendUpdateAvatar(UserAvatar userAvatar, String userId, String systemRequestUserId) {
        userAvatar.setUserId(userId);
        userAvatar.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户头像消息：" + userAvatar.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.avatar.save", userAvatar.toJSONString());
    }

    @Override
    public void sendSaveEmail(UserEmail userEmail, String userId, String systemRequestUserId) {
        userEmail.setUserId(systemRequestUserId);
        userEmail.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户邮箱消息：" + userEmail.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.email.save", userEmail.toJSONString());
    }

    @Override
    public void sendUpdateEmail(UserEmail userEmail, String userId, String systemRequestUserId) {
        userEmail.setUserId(systemRequestUserId);
        userEmail.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户邮箱消息：" + userEmail.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.email.update", userEmail.toJSONString());
    }

    @Override
    public void sendSaveIdcard(UserIdcard userIdcard, String userId, String systemRequestUserId) {
        userIdcard.setUserId(systemRequestUserId);
        userIdcard.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户身份证消息：" + userIdcard.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.idcard.save", userIdcard.toJSONString());
    }

    @Override
    public void sendUpdateIdcard(UserIdcard userIdcard, String userId, String systemRequestUserId) {
        userIdcard.setUserId(systemRequestUserId);
        userIdcard.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户身份证消息：" + userIdcard.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.idcard.update", userIdcard.toJSONString());
    }

    @Override
    public void sendSaveMobile(UserMobile userMobile, String userId, String systemRequestUserId) {
        userMobile.setUserId(systemRequestUserId);
        userMobile.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户手机号码消息：" + userMobile.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.mobile.save", userMobile.toJSONString());
    }

    @Override
    public void sendUpdateMobile(UserMobile userMobile, String userId, String systemRequestUserId) {
        userMobile.setUserId(systemRequestUserId);
        userMobile.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户手机号码消息：" + userMobile.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.mobile.update", userMobile.toJSONString());
    }

    @Override
    public void sendSaveNickName(UserNickName userNickName, String userId, String systemRequestUserId) {
        userNickName.setUserId(systemRequestUserId);
        userNickName.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户昵称消息：" + userNickName.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.nickName.save", userNickName.toJSONString());
    }

    @Override
    public void sendUpdateNickName(UserNickName userNickName, String userId, String systemRequestUserId) {
        userNickName.setUserId(systemRequestUserId);
        userNickName.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户昵称消息：" + userNickName.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.nickName.update", userNickName.toJSONString());
    }

    @Override
    public void sendSaveWechat(UserWechat userWechat, String userId, String systemRequestUserId) {
        userWechat.setUserId(userId);
        userWechat.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送保存用户微信消息：" + userWechat.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.userWechat.save", userWechat.toJSONString());
    }

    @Override
    public void sendUpdateWechat(UserWechat userWechat, String userId, String systemRequestUserId) {
        userWechat.setUserId(userId);
        userWechat.setSystemRequestUserId(systemRequestUserId);
        System.out.println("发送更新用户微信消息：" + userWechat.toJSONString());
        rabbitTemplate.convertAndSend("exchange", "topic.user.userWechat.update", userWechat.toJSONString());
    }

}
