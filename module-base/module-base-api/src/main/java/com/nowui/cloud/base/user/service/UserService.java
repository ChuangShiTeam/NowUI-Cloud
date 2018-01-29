package com.nowui.cloud.base.user.service;
import java.util.List;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.service.BaseService;

/**
 * 用户业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface UserService extends BaseService<User> {

    /**
     * 用户统计
     *
     * @param appId 应用编号
     * @param userType 类型
     * @return Integer 用户统计
     */
    Integer count(String appId, String userType);

    /**
     * 用户列表
     *
     * @param appId 应用编号
     * @param userType 类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<User> 用户列表
     */
    List<User> list(String appId, String userType, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户主体编号和用户类型查询用户信息
     * 
     * @param objectId 用户主体编号
     * @param userType 用户类型
     * @return User 用户信息
     */
    User findByObjectIdAndUserType(String objectId, String userType);

    /**
     * 根据用户主体编号和用户类型逻辑删除用户信息
     * 
     * @param objectId 用户主体编号
     * @param userType 用户信息
     * @param systemRequestUserId 请求用户编号
     * @param Boolean 成功或失败
     */
    Boolean deleteByObjectIdAndUserType(String objectId, String userType, String systemRequestUserId);
    
    /**
     * 根据用户编号查询用户详细信息
     * 
     * @param userId 用户编号
     * @return User 用户详细信息
     */
    User findById(String userId);
    
    /**
     * 保存用户账号信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAccount 用户账号
     * @param userAccountId 用户账号编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserAccount(String appId, String userId, UserAccount userAccount, String userAccountId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户账号信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserAccountByUserId(String userId, String systemRequestUserId);
    
    
    /**
     * 保存用户密码信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userPassword 用户密码信息
     * @param userPasswordId 用户密码编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserPassword(String appId, String userId, UserPassword userPassword, String userPasswordId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户密码信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserPasswordByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户邮箱信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userEmail 用户邮箱
     * @param userEmailId 用户邮箱编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserEmail(String appId, String userId, UserEmail userEmail, String userEmailId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户邮箱信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserEmailByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户头像信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAvatar 用户头像信息
     * @param userAvatarId 用户头像编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserAvatar(String appId, String userId, UserAvatar userAvatar, String userAvatarId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户头像信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserAvatarByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户手机号码信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userMobile 用户手机号码信息
     * @param userMobileId 用户手机号码编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserMobile(String appId, String userId, UserMobile userMobile, String userMobileId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户手机号码信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserMobileByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户身份证信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userIdcard 用户身份证信息
     * @param userIdcardId 用户身份证编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserIdcard(String appId, String userId, UserIdcard userIdcard, String userIdcardId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户身份证信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserIdcardByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户昵称信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userNickName 用户昵称信息
     * @param userNickNameId 用户昵称编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserNickName(String appId, String userId, UserNickName userNickName, String userNickNameId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户昵称信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserNickNameByUserId(String userId, String systemRequestUserId);
    
    /**
     * 保存用户微信信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userWechat 用户微信信息
     * @param userWechatId 用户微信编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean saveUserWechat(String appId, String userId, UserWechat userWechat, String userWechatId, String systemRequestUserId);
    
    /**
     * 根据用户编号删除用户微信信息
     * 
     * @param userId 用户编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteUserWechatByUserId(String userId, String systemRequestUserId);

    /**
     * 根据用户账号查询用户账号信息
     * 
     * @param appId 应用编号
     * @param userAccount 用户账号信息
     * @return UserAccount 用户账号信息
     */
    UserAccount findByUserAccount(String appId, String userAccount);

    /**
     * 根据用户微信openId和微信unionId查询用户微信信息
     * 
     * @param appId 
     * @param wechatOpenId
     * @param wechatUnionId
     * @return
     */
    UserWechat findByOpenIdAndUnionId(String appId, String wechatOpenId, String wechatUnionId);
}
