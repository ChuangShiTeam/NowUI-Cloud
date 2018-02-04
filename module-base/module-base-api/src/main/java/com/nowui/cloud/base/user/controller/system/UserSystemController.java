package com.nowui.cloud.base.user.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import com.nowui.cloud.base.user.router.UserRouter;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.base.user.view.UserWechatView;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 用户系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户系统端接口管理")
@RestController
public class UserSystemController implements UserRpc {
	
	@Autowired
	private UserService userService;

	@Override
	public Integer countV1(String appId, String userType) {
		return userService.count(appId, userType);
	}

	@Override
	public List<User> listV1(String appId, String userType, Integer pageIndex, Integer pageSize) {
		return userService.list(appId, userType, pageIndex, pageSize);
	}

    @Override
    public User findV1(String userId) {
        return null;
//        return userService.findById(userId);
	}


    @Override
    public User findByUserAccountV1(String appId, String userType, String userAccount) {

        return null;
//        if (Util.isNullOrEmpty(userType) || Util.isNullOrEmpty(userAccount)) {
//            return null;
//        }
//        UserAccount bean = userService.findByUserAccount(appId, userAccount);
//
//        if (bean == null || Util.isNullOrEmpty(bean.getUserId())) {
//            return null;
//        }
//        UserView user = userService.findById(bean.getUserId());
//        if (!userType.equals(user.getUserType())) {
//            return null;
//        }
//        return user;
    }

    @Override
    public Boolean checkUserPasswordV1(String userId, String userPassword) {

        // TODO: UserView 中没有 getUserPassword 方法.
        if (Util.isNullOrEmpty(userId) || Util.isNullOrEmpty(userPassword)) {
            return false;
        }
        UserView user = userService.findById(userId);
        
        if (user == null) {
            return false;
        }
//        String encryptPassword = Util.generatePassword(userPassword);
//        if (encryptPassword.equals(user.getUserPassword())) {
//            return true;
//        }
        return false;
    }

    @Override
    public User findByUserWechatV1(String appId, String userType, String wechatOpenId, String wechatUnionId) {

	    // TODO:  findByUserWechatV1
//        UserWechatView userWechat = userService.findByOpenIdAndUnionId(appId, wechatOpenId, wechatUnionId);
        
//        if (userWechat == null || Util.isNullOrEmpty(userWechat.getUserId())) {
//            return null;
//        }
//
//        User user = userService.findById(userWechat.getUserId());
//
//        if (user == null) {
//            return null;
//        }
//        if (user.getUserType().equals(userType)) {
//            return user;
//        }
        return null;
    }

    @Override
    public Boolean saveUserWechatV1(String appId, String userId, String objectId, String userType, UserWechat userWechat, String systemRequestUserId) {
        User user = new User();
        user.setAppId(appId);
        user.setUserId(userId);
        user.setObjectId(objectId);
        user.setUserType(userType);
        
        Boolean result = userService.save(user,userId, appId,UserRouter.USER_V1_SAVE,user.getSystemCreateUserId());
//        Boolean result = userService.save(user, userId, systemRequestUserId);
        if (result) {
            userWechat.setAppId(appId);
            userWechat.setUserId(userId);
            
            if (!Util.isNullOrEmpty(userWechat.getWechatHeadImgUrl()) && !Util.isNullOrEmpty(userWechat.getWechatHeadImgFileId())) {
                // 保存用户头像
                UserAvatar userAvatar = new UserAvatar();
                userAvatar.setUserAvatar(userWechat.getWechatHeadImgFileId());
                userService.saveUserAvatar(appId, userId, userAvatar, Util.getRandomUUID(), systemRequestUserId);
            }
            
            if (!Util.isNullOrEmpty(userWechat.getWechatNickName())) {
                // 保存用户昵称
                UserNickName userNickName = new UserNickName();
                userNickName.setUserNickName(userWechat.getWechatNickName());
                
                userService.saveUserNickName(appId, userId, userNickName, Util.getRandomUUID(), systemRequestUserId);
            }
            // 保存用户微信头像
            userService.saveUserWechat(appId, userId, userWechat, Util.getRandomUUID(), systemRequestUserId);
        }
        
        return result;
    }

    @Override
    public Boolean updateUserWechatV1(String userId, UserWechat userWechat, String systemRequestUserId) {
        
        UserView user = userService.findById(userId);

        // TODO: USERSERVICE 中没有getUserWechat 方法
//        UserWechatView bean = user.getUserWechat();
//        UserWechat bean =
       
        Boolean result = true;
       
        Boolean isNeedUpdate = false;
       
//        // 头像更新
//        if (!userWechat.getWechatHeadImgUrl().equals(bean.getWechatHeadImgUrl())) {
//            // 刪除旧的用户头像
//            userService.deleteUserAvatarByUserId(userId, systemRequestUserId);
//            // 保存用户头像
//            UserAvatar userAvatar = new UserAvatar();
//            userAvatar.setAppId(bean.getAppId());
//            userAvatar.setUserId(userId);
//            userAvatar.setUserAvatar(userWechat.getWechatHeadImgFileId());
//            userService.saveUserAvatar(bean.getAppId(), userId, userAvatar, Util.getRandomUUID(), systemRequestUserId);
//
//            isNeedUpdate = true;
//        }
           
        // 保存或更新用户昵称
//        if (!userWechat.getWechatNickName().equals(bean.getWechatNickName())) {
//            // 删除用户昵称
//            userService.deleteUserNickNameByUserId(userId, systemRequestUserId);
//            // 保存用户昵称
//            UserNickName userNickName = new UserNickName();
//            userNickName.setAppId(bean.getAppId());
//            userNickName.setUserId(userId);
//            userNickName.setUserNickName(userWechat.getWechatNickName());
//
//            userService.saveUserNickName(bean.getAppId(), userId, userNickName, Util.getRandomUUID(), systemRequestUserId);
//
//            isNeedUpdate = true;
//        }
//
        // 保存或更新用户性别
//        if (!userWechat.getWechatSex().equals(bean.getWechatSex())) {
//
//            //删除旧的用户身份证信息
//            userService.deleteUserIdcardByUserId(userId, systemRequestUserId);
//
//            UserIdcard userIdcard = new UserIdcard();
//            userIdcard.setUserSex(userWechat.getWechatSex());
//            userIdcard.setUserName(user.getUserName());
//            userIdcard.setUserIdcardNumber(user.getUserIdcardNumber());
//
//            userService.saveUserIdcard(bean.getAppId(), systemRequestUserId, userIdcard, Util.getRandomUUID(), systemRequestUserId);
//
//            isNeedUpdate = true;
//        }
//
//        // 国家更新
//        if (!userWechat.getWechatCountry().equals(bean.getWechatCountry())) {
//            isNeedUpdate = true;
//        }
       
//        // 省份更新
//        if (!userWechat.getWechatProvince().equals(bean.getWechatProvince())) {
//            isNeedUpdate = true;
//        }
//
//        // 市更新
//        if (!userWechat.getWechatCity().equals(bean.getWechatCity())) {
//            isNeedUpdate = true;
//        }
//
//        if (isNeedUpdate) {
//            // 删除旧的用户微信信息
//            userService.deleteUserWechatByUserId(userId, systemRequestUserId);
//            // 保存用户微信信息
//            result = userService.saveUserWechat(bean.getAppId(), userId, userWechat, Util.getRandomUUID(), systemRequestUserId);
//        }
//
        return result;
       
    }

    @Override
    public Boolean updateUserPasswordV1(String appId, String userId, String userPassword, String systemRequestUserId) {
        //删除旧的密码信息
        userService.deleteUserPasswordByUserId(userId, systemRequestUserId);
        //保存新的密码信息
        UserPassword userPasswordBean = new UserPassword();
        userPasswordBean.setUserPassword(userPassword);
        
        Boolean result = userService.saveUserPassword(appId, userId, userPasswordBean, Util.getRandomUUID(), systemRequestUserId);
        
        return result;
    }

    @Override
    public Boolean updateUserAvatarV1(String appId, String userId, String userAvatar, String systemRequestUserId) {

	    // TODO: UserView 中没有 getUserAvatar 方法.
        UserView user = userService.findById(userId);
//
//        if (userAvatar.equals(user.getUserAvatar())) {
//            return true;
//        }
        //删除旧的头像信息
        userService.deleteUserAvatarByUserId(userId, systemRequestUserId);
        if (Util.isNullOrEmpty(userAvatar)) {
            return true;
        }
        //保存新的头像信息
        UserAvatar userAvatarBean = new UserAvatar();
        userAvatarBean.setUserAvatar(userAvatar);
        
        Boolean result = userService.saveUserAvatar(appId, userId, userAvatarBean, Util.getRandomUUID(), systemRequestUserId);
        
        return result;
    }

    @Override
    public Boolean updateUserNickNameV1(String appId, String userId, String userNickName, String systemRequestUserId) {

	    // TODO: userService 中没有 getUserNickName 方法.
        UserView user = userService.findById(userId);
        
//        if (userNickName.equals(user.getUserNickName())) {
//            return true;
//        }
        //删除旧的用户昵称信息
        userService.deleteUserNickNameByUserId(userId, systemRequestUserId);
        if (Util.isNullOrEmpty(userNickName)) {
            return true;
        }
        
        //保存新的用户昵称信息
        UserNickName userNickNameBean = new UserNickName();
        userNickNameBean.setUserNickName(userNickName);
        
        Boolean result = userService.saveUserNickName(appId, userId, userNickNameBean, Util.getRandomUUID(), systemRequestUserId);
        return result;
    }
    
    @Override
    public Boolean updateUserSexV1(String appId, String userId, String userSex, String systemRequestUserId) {

        // TODO: userService 中没有 getUserSex 方法.
        UserView user = userService.findById(userId);
        
//        if (userSex.equals(user.getUserSex())) {
//            return true;
//        }
//        //删除旧的用户身份证信息
//        userService.deleteUserIdcardByUserId(userId, systemRequestUserId);
//
//        UserIdcard userIdcard = new UserIdcard();
//        userIdcard.setUserSex(userSex);
//        userIdcard.setUserName(user.getUserName());
//        userIdcard.setUserIdcardNumber(user.getUserIdcardNumber());
//
//        return userService.saveUserIdcard(appId, systemRequestUserId, userIdcard, Util.getRandomUUID(), systemRequestUserId);

        return false;
    }

    @Override
    public Boolean registerUserMobileV1(String appId, String userId, String objectId, String userType,
            String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        Boolean result = userService.save(user, userId, userId,UserRouter.USER_V1_SAVE,systemRequestUserId);
        
        if (result) {
            // 保存用户账号
            UserAccount userAccountBean = new UserAccount();
            userAccountBean.setUserAccount(userAccount);
            userService.saveUserAccount(appId, userId, userAccountBean, Util.getRandomUUID(), userId);
            // 保存用户密码
            UserPassword userPasswordbean = new UserPassword();
            userPasswordbean.setUserPassword(userPassword);
            userService.saveUserPassword(appId, userId, userPasswordbean, Util.getRandomUUID(), userId);
            // 保存用户手机号码
            UserMobile userMobile = new UserMobile();
            userMobile.setUserMobile(userAccount);
            userService.saveUserMobile(appId, userId, userMobile, Util.getRandomUUID(), userId);
        }
        
        return result;
    }

    @Override
    public Boolean registerUserEmailV1(String appId, String userId, String objectId, String userType,
            String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        Boolean result = userService.save(user, userId, userId,UserRouter.USER_V1_SAVE,systemRequestUserId);
        
        if (result) {
            // 保存用户账号
            UserAccount userAccountBean = new UserAccount();
            userAccountBean.setUserAccount(userAccount);
            userService.saveUserAccount(appId, userId, userAccountBean, Util.getRandomUUID(), systemRequestUserId);
            // 保存用户密码
            UserPassword userPasswordbean = new UserPassword();
            userPasswordbean.setUserPassword(userPassword);
            userService.saveUserPassword(appId, userId, userPasswordbean, Util.getRandomUUID(), userId);
            // 保存用户邮箱
            UserEmail userEmail = new UserEmail();
            userEmail.setUserEmail(userAccount);
            userService.saveUserEmail(appId, userId, userEmail, Util.getRandomUUID(), userId);
        }
        
        return result;
    }

    @Override
    public List<User> findsV1(String userIds) {
        
        if (Util.isNullOrEmpty(userIds)) {
            return null;
        }
        
        List<String> userIdList = JSONArray.parseArray(userIds, String.class);
//        return userIdList.stream().map(userId -> findV1(userId)).collect(Collectors.toList());
        // TODO： 查询用户列表异常
        return null;
    }

}