package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.util.Util;

@Component
public class UserListener {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Autowired
    private UserAvatarService userAvatarService;
    
    @Autowired
    private UserEmailService userEmailService;
    
    @Autowired
    private UserIdcardService userIdcardService;
    
    @Autowired
    private UserMobileService userMobileService;
    
    @Autowired
    private UserNickNameService userNickNameService;
    
    @Autowired
    private UserWechatService userWechatService;

    @RabbitListener(queues = "topic.user.save")
    public void receiveUserSave(String message) {
        System.out.println("收到保存用户消息：" + message);
        try {
            User user = JSONObject.parseObject(message, User.class);
            userService.save(user, user.getUserId(), user.getSystemRequestUserId());
        } catch (Exception e) {
            
        }
        
    }
    
    @RabbitListener(queues = "topic.user.update")
    public void receiveUserUpdate(String message) {
        User user = JSONObject.parseObject(message, User.class);
        System.out.println("收到更新用户消息：" + user.toJSONString());
        userService.update(user, user.getUserId(), user.getSystemRequestUserId(), user.getSystemVersion());
    }

    @RabbitListener(queues = "topic.user.account.save")
    public void receiveUserAccountSave(String message) {
        UserAccount userAccount = JSONObject.parseObject(message, UserAccount.class);
        System.out.println("收到保存用户账号消息：" + userAccount.toJSONString());
        userAccountService.save(userAccount, Util.getRandomUUID(), userAccount.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.account.update")
    public void receiveUserAccountUpdate(String message) {
        UserAccount userAccount = JSONObject.parseObject(message, UserAccount.class);
        System.out.println("收到更新用户账号消息：" + userAccount.toJSONString());
        //删除旧的账号信息
        userAccountService.deleteByUserId(userAccount.getUserId(), userAccount.getSystemRequestUserId());
        //保存新的账号信息
        userAccountService.save(userAccount, Util.getRandomUUID(), userAccount.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.email.save")
    public void receiveUserEmailSave(String message) {
        UserEmail userEmail = JSONObject.parseObject(message, UserEmail.class);
        System.out.println("收到保存用户邮箱消息：" + userEmail.toJSONString());
        userEmailService.save(userEmail, Util.getRandomUUID(), userEmail.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.email.update")
    public void receiveUserEmailUpdate(String message) {
        UserEmail userEmail = JSONObject.parseObject(message, UserEmail.class);
        System.out.println("收到保存用户邮箱消息：" + userEmail.toJSONString());
        //删除旧的邮箱信息
        userEmailService.deleteByUserId(userEmail.getUserId(), userEmail.getSystemRequestUserId());
        //保存新的邮箱信息
        userEmailService.save(userEmail, Util.getRandomUUID(), userEmail.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.avatar.save")
    public void receiveUserAvatarSave(String message) {
        UserAvatar userAvatar = JSONObject.parseObject(message, UserAvatar.class);
        System.out.println("收到保存用户头像消息：" + userAvatar.toJSONString());
        userAvatarService.save(userAvatar, Util.getRandomUUID(), userAvatar.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.avatar.update")
    public void receiveUserAvatarUpdate(String message) {
        UserAvatar userAvatar = JSONObject.parseObject(message, UserAvatar.class);
        System.out.println("收到更新用户头像消息：" + userAvatar.toJSONString());
        //删除旧的头像信息
        userAvatarService.deleteByUserId(userAvatar.getUserId(), userAvatar.getSystemRequestUserId());
        //保存新的头像信息
        userAvatarService.save(userAvatar, Util.getRandomUUID(), userAvatar.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.idcard.save")
    public void receiveUserIdcardSave(String message) {
        UserIdcard userIdcard = JSONObject.parseObject(message, UserIdcard.class);
        System.out.println("收到保存用户身份证消息：" + userIdcard.toJSONString());
        userIdcardService.save(userIdcard, Util.getRandomUUID(), userIdcard.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.idcard.update")
    public void receiveUserIdcardUpdate(String message) {
        UserIdcard userIdcard = JSONObject.parseObject(message, UserIdcard.class);
        System.out.println("收到更新用户身份证消息：" + userIdcard.toJSONString());
        //删除旧的身份证信息
        userIdcardService.deleteByUserId(userIdcard.getUserId(), userIdcard.getSystemRequestUserId());
        //保存新的身份证信息
        userIdcardService.save(userIdcard, Util.getRandomUUID(), userIdcard.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.mobile.save")
    public void receiveUserMobileSave(String message) {
        UserMobile userMobile = JSONObject.parseObject(message, UserMobile.class);
        System.out.println("收到保存用户头像消息：" + userMobile.toJSONString());
        userMobileService.save(userMobile, Util.getRandomUUID(), userMobile.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.mobile.update")
    public void receiveUserMobileUpdate(String message) {
        UserMobile userMobile = JSONObject.parseObject(message, UserMobile.class);
        System.out.println("收到更新用户手机号码消息：" + userMobile.toJSONString());
        //删除旧的手机号码信息
        userMobileService.deleteByUserId(userMobile.getUserId(), userMobile.getSystemRequestUserId());
        //保存新的手机号码信息
        userMobileService.save(userMobile, Util.getRandomUUID(), userMobile.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.nickName.save")
    public void receiveUserNickNameSave(String message) {
        UserNickName userNickName = JSONObject.parseObject(message, UserNickName.class);
        System.out.println("收到保存用户昵称消息：" + userNickName.toJSONString());
        userNickNameService.save(userNickName, Util.getRandomUUID(), userNickName.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.nickName.update")
    public void receiveUserNickNameUpdate(String message) {
        UserNickName userNickName = JSONObject.parseObject(message, UserNickName.class);
        System.out.println("收到更新用户昵称消息：" + userNickName.toJSONString());
        //删除旧的用户昵称信息
        userNickNameService.deleteByUserId(userNickName.getUserId(), userNickName.getSystemRequestUserId());
        //保存新的用户昵称信息
        userNickNameService.save(userNickName, Util.getRandomUUID(), userNickName.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.wechat.save")
    public void receiveUserWechatSave(String message) {
        UserWechat userWechat = JSONObject.parseObject(message, UserWechat.class);
        System.out.println("收到保存用户微信消息：" + userWechat.toJSONString());
        userWechatService.save(userWechat, Util.getRandomUUID(), userWechat.getSystemRequestUserId());
    }
    
    @RabbitListener(queues = "topic.user.wechat.update")
    public void receiveUserWechatUpdate(String message) {
        UserWechat userWechat = JSONObject.parseObject(message, UserWechat.class);
        System.out.println("收到更新用户微信消息：" + userWechat.toJSONString());
        //删除旧的用户微信信息
        userWechatService.deleteByUserId(userWechat.getUserId(), userWechat.getSystemRequestUserId());
        //保存新的用户微信信息
        userWechatService.save(userWechat, Util.getRandomUUID(), userWechat.getSystemRequestUserId());
    }
    

}
