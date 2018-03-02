package com.nowui.cloud.base.user.rpc;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.view.UserView;

/**
 * 用户服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "userRpc")
@FeignClient(name = "module-base")
public interface UserRpc {

    /**
     * 根据用户编号查询用户信息
     * 
     * @param userId 用户编号
     * @return User 用户信息
     */
    @RequestMapping(value = "/user/system/v1/find", method = RequestMethod.POST)
    UserView findV1(@RequestParam(value = "userId", required = true) String userId);
    
    /**
     * 根据用户编号集合查询用户信息列表
     * 
     * @param userIds 用户编号集合
     * @return List<User> 用户信息列表
     */
    @RequestMapping(value = "/user/system/v1/finds", method = RequestMethod.POST)
    List<User> findsV1(@RequestParam(value = "userIds", required = true) String userIds);
    
    /**
     * 根据用户类型和账号查询用户信息
     * 
     * @param appId 应用编号
     * @param userType 用户类型
     * @param userAccount 用户账号
     * @return UserView 用户视图信息
     */
    @RequestMapping(value = "/user/system/v1/find/by/user/type/and/account", method = RequestMethod.POST)
    UserView findByUserTypeAndAccountV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userType", required = true) String userType,
            @RequestParam(value = "userAccount", required = true) String userAccount
    );
    
    /**
     * 验证用户账号
     * 
     * @param appId 应用编号
     * @param userType 用户类型
     * @param userAccount 用户账号
     * @return true 存在   false 不存在
     */
    @RequestMapping(value = "/user/system/v1/check/user/account", method = RequestMethod.POST)
    Boolean checkUserAccountV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userType", required = true) String userType,
            @RequestParam(value = "userAccount", required = true) String userAccount
    );
    
    /**
     * 验证用户密码
     * 
     * @param userId 用户编号
     * @param userPassword 用户密码
     * @return true 正确   false 不正确 
     */
    @RequestMapping(value = "/user/system/v1/check/user/password", method = RequestMethod.POST)
    Boolean checkUserPasswordV1(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "userPassword", required = true) String userPassword
    );
    
    @RequestMapping(value = "/user/system/v1/find/by/user/wechat", method = RequestMethod.POST)
    UserView findByUserWechatV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userType", required = true) String userType,
            @RequestParam(value = "wechatOpenId", required = true) String wechatOpenId,
            @RequestParam(value = "wechatUnionId", required = true) String wechatUnionId
    );
    
    /**
     * 微信用户注册
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param objectId 用户主体编号
     * @param userType 用户类型
     * @param userWechat 用户微信信息
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/user/system/v1/save/user/wechat", method = RequestMethod.POST)
    Boolean saveUserWechatV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "objectId", required = true) String objectId,
            @RequestParam(value = "userType", required = true) String userType,
            UserWechat userWechat,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
    /**
     * 微信用户信息更新
     * 
     * @param userId 用户编号
     * @param userWechat 用户微信信息
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/user/system/v1/update/user/wechat", method = RequestMethod.POST)
    Boolean updateUserWechatV1(
            @RequestParam(value = "userId", required = true) String userId,
            UserWechat userWechat,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
    /**
     * 用户头像更新
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAvatar 用户头像
     * @param userAvatarPath 用户头像路径
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/user/system/v1/update/user/avatar", method = RequestMethod.POST)
    Boolean updateUserAvatarV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "userAvatarFileId", required = true) String userAvatarFileId,
            @RequestParam(value = "userAvatarFilePath", required = true) String userAvatarFilePath,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
    /**
     * 用户昵称更新
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userNickName 用户昵称
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/user/system/v1/update/user/nick/name", method = RequestMethod.POST)
    Boolean updateUserNickNameV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "userNickName", required = true) String userNickName,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
    /**
     * 用户性别更新
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userSex 用户性别
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/user/system/v1/update/user/sex", method = RequestMethod.POST)
    Boolean updateUserSexV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "userSex", required = true) String userSex,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
}