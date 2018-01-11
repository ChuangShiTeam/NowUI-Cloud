package com.nowui.cloud.base.user.mq;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserWechat;

/**
 * 用户发送消息接口
 * 
 * @author marcus
 *
 * 2018年1月9日
 */
public interface UserMq {
    
    /**
     * 发送保存用户消息
     * 
     * @param user 用户信息
     * @param userId 用户编号
     * @param systemCreateUserId 创建用户编号
     */
    void sendSave(User user, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户消息
     * 
     * @param user 用户信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @param systemVersion 版本号
     */
    void sendUpdate(User user, String userId, String systemUpdateUserId, Integer systemVersion);
    
    /**
     * 发送保存用户账号信息
     * 
     * @param userAccount 用户账号信息
     * @param userId 用户编号
     * @param systemCreateUserId 创建用户编号
     */
    void sendSaveAccount(UserAccount userAccount, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户账号信息
     * 
     * @param userAccount 用户账号信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateAccount(UserAccount userAccount, String userId, String systemUpdateUserId);

    /**
     * 发送保存用户头像信息
     * 
     * @param userAvatar 用户头像信息
     * @param userId 用户编号
     * @param systemCreateUserId 创建用户编号
     */
    void sendSaveAvatar(UserAvatar userAvatar, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户头像信息
     * 
     * @param userAvatar 用户头像信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateAvatar(UserAvatar userAvatar, String userId, String systemUpdateUserId);

    /**
     * 发送保存用户邮箱信息
     * 
     * @param userEmail 用户邮箱信息
     * @param userId 用户编号
     * @param systemCreateUserId 保存用户编号
     */
    void sendSaveEmail(UserEmail userEmail, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户邮箱信息
     * 
     * @param userEmail 用户邮箱信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateEmail(UserEmail userEmail, String userId, String systemUpdateUserId);

    /**
     * 发送保存用户身份证信息
     * 
     * @param userIdcard 用户身份证信息
     * @param userId 用户编号
     * @param systemCreateUserId 保存用户编号
     */
    void sendSaveIdcard(UserIdcard userIdcard, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户身份证信息
     * 
     * @param userIdcard 用户身份证信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateIdcard(UserIdcard userIdcard, String userId, String systemUpdateUserId);

    /**
     * 发送保存用户手机号码信息
     * 
     * @param userMobile 用户手机号码信息
     * @param userId 用户编号
     * @param systemUpdateUserId 保存用户编号
     */
    void sendSaveMobile(UserMobile userMobile, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户手机号码信息
     * 
     * @param userMobile 用户手机号码信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateMobile(UserMobile userMobile, String userId, String systemUpdateUserId);

    /**
     * 发送保存用户昵称信息
     * 
     * @param userNickName 用户昵称信息
     * @param userId 用户编号
     * @param systemUpdateUserId 保存用户编号
     */
    void sendSaveNickName(UserNickName userNickName, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户昵称信息
     * 
     * @param userNickName 用户昵称信息
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateNickName(UserNickName userNickName, String userId, String systemUpdateUserId);

    /**
     * 发送更新用户账号信息
     * 
     * @param userWechat 用户微信信息
     * @param userId 用户编号
     * @param systemUpdateUserId 保存用户编号
     */
    void sendSaveWechat(UserWechat userWechat, String userId, String systemCreateUserId);
    
    /**
     * 发送更新用户微信信息
     * 
     * @param userWechat 用户微信
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     */
    void sendUpdateWechat(UserWechat userWechat, String userId, String systemUpdateUserId);
}
