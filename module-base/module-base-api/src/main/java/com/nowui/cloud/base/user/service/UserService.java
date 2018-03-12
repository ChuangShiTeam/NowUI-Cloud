package com.nowui.cloud.base.user.service;
import java.util.List;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.service.BaseService;

/**
 * 用户业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface UserService extends BaseService<User,UserView> {

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
     * 根据用户类型和用户账号查询用户视图信息
     * 
     * @param appId 应用编号
     * @param userType 用户类型
     * @param userAccount 用户账号
     * @return UserView 用户视图信息
     */
    UserView findByUserTypeAndAccount(String appId, String userType, String userAccount);
    
    /**
     * 根据微信openId和微信unionId查询用户视图信息
     * 
     * @param appId 应用编号
     * @param userType 用户类型
     * @param wechatOpenId 微信openId
     * @param wechatUnionId 微信unionId
     * @return UserView 用户视图信息
     */
    UserView findByUserTypeAndOpenIdAndUnionId(String appId, String userType, String wechatOpenId, String wechatUnionId);

    /**
     * 更新用户账号信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAccount 用户账号
     * @param userAccountId 用户账号编号
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean updateUserAccount(String appId, String userId, String userAccount, String systemRequestUserId);
    
    /**
     * 更新用户邮箱信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userEmail 用户邮箱
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean updateUserEmail(String appId, String userId, String userEmail, String systemRequestUserId);
    
    /**
     * 更新用户手机号码信息
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userMobile 用户手机号码信息
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean updateUserMobile(String appId, String userId, String userMobile, String systemRequestUserId);
    
    /**
     * 验证用户账号是否存在
     * 
     * @param appId 应用编号
     * @param userType 用户类型
     * @param userAccount 用户账号
     * @return true 存在    false 不存在
     */
    Boolean checkUserAccount(String appId, String userType, String userAccount);
    
    /**
     * 更新用户密码
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userPassword 用户密码
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 失败
     */
    Boolean updateUserPassword(String appId, String userId, String userPassword, String systemRequestUserId);
    
    /**
     * 更新用户昵称
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userNickName 用户昵称
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 失败
     */
    Boolean updateUserNickName(String appId, String userId, String userNickName, String systemRequestUserId);
    
    /**
     * 更新用户头像
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAvatarFileId 用户头像文件编号
     * @param userAvatarFilePath 用户头像文件路径
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 失败
     */
    Boolean updateUserAvatar(String appId, String userId, String userAvatarFileId, String userAvatarFilePath, String systemRequestUserId);
    
    /**
     * 更新用户性别
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userSex 用户性别
     * @param systemRequestUserId 请求用户编号
     * @return true 成功    false 失败
     */
    Boolean updateUserSex(String appId, String userId, String userSex, String systemRequestUserId);
}
