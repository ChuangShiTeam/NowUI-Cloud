package com.nowui.cloud.base.user.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserWechatService;
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
        return userService.findById(userId);
    }

    @Override
    public User findByUserAccount(String appId, String userType, String userAccount) {
        if (Util.isNullOrEmpty(userType) || Util.isNullOrEmpty(userAccount)) {
            return null;
        }
        UserAccount bean = userService.findByUserAccount(appId, userAccount);
        
        if (bean == null || Util.isNullOrEmpty(bean.getUserId())) {
            return null;
        }
        
        User user = userService.findById(bean.getUserId());
        
        if (!userType.equals(user.getUserType())) {
            return null;
        }
        return user;
    }

    @Override
    public Boolean checkUserPassword(String userId, String userPassword) {
        
        if (Util.isNullOrEmpty(userId) || Util.isNullOrEmpty(userPassword)) {
            return false;
        }
        
        UserPassword bean = userService.findUserPasswordByUserId(userId);
        
        if (bean == null) {
            return false;
        }
        String encryptPassword = Util.generatePassword(userPassword);
        if (encryptPassword.equals(bean.getUserPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public User fingByUserWechat(String appId, String userType, String wechatOpenId, String wechatUnionId) {
        UserWechat userWechat = userService.findByOpenIdAndUnionId(appId, wechatOpenId, wechatUnionId);
        
        if (userWechat == null || Util.isNullOrEmpty(userWechat.getUserId())) {
            return null;
        }
        
        User user = userService.find(userWechat.getUserId());
        
        if (user == null) {
            return null;
        }
        if (user.getUserType().equals(userType)) {
            return user;
        }
        return null;
    }

    @Override
    public Boolean saveUserWechat(String appId, String userId, String objectId, String userType, UserWechat userWechat, String systemRequestUserId) {
        User user = new User();
        user.setAppId(appId);
        user.setUserId(userId);
        user.setObjectId(objectId);
        user.setUserType(userType);
        
        Boolean result = userService.save(user, userId, systemRequestUserId);
        
        if (result) {
            // 下载保存微信用户头像文件
            userWechat.setAppId(appId);
            userWechat.setUserId(userId);
            userWechat.setWechatHeadImgFileId("");
            
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
    public Boolean updateUserWechat(String userId, UserWechat userWechat, String systemRequestUserId) {
       UserWechat bean = userService.findUserWechatByUserId(userId);
       
       Boolean result = true;
       
       Boolean isNeedUpdate = false;
       
       // 头像更新
       if (!userWechat.getWechatHeadImgUrl().equals(bean.getWechatHeadImgUrl())) {
           bean.setWechatHeadImgFileId(userWechat.getWechatHeadImgFileId());
           UserAvatar userAvatar = userService.findUserAvatarByUserId(userId);
           if (userAvatar == null) {
               // 保存用户头像
               userAvatar = new UserAvatar();
               userAvatar.setAppId(bean.getAppId());
               userAvatar.setUserId(userId);
               userAvatar.setUserAvatar(userWechat.getWechatHeadImgFileId());
               userService.saveUserAvatar(bean.getAppId(), userId, userAvatar, Util.getRandomUUID(),, systemRequestUserId);
           } else {
               // 更新用户头像
               userAvatar.setUserAvatar(userWechat.getWechatHeadImgFileId()); 
               userAvatarService.update(userAvatar, userAvatar.getUserAvatarId(), userId, userAvatar.getSystemVersion());
           }
           bean.setWechatHeadImgUrl(userWechat.getWechatHeadImgUrl());
           isNeedUpdate = true;
       }
           
       // 保存或更新用户昵称
       if (!userWechat.getWechatNickName().equals(bean.getWechatNickName())) {
           UserNickName userNickName = userNickNameService.findByUserId(userId);
           
           if (userNickName == null) {
               // 保存用户昵称
               userNickName = new UserNickName();
               userNickName.setAppId(bean.getAppId());
               userNickName.setUserId(userId);
               userNickName.setUserNickName(userWechat.getWechatNickName());
               
               userNickNameService.save(userNickName, Util.getRandomUUID(), userId);
           } else {
               // 更新用户昵称
               userNickName.setUserNickName(userWechat.getWechatNickName());
               userNickNameService.update(userNickName, userNickName.getUserNickNameId(), userId, userNickName.getSystemVersion());
           }
           isNeedUpdate = true;
       }
       
       // 国家更新
       if (!userWechat.getWechatCountry().equals(bean.getWechatCountry())) {
           bean.setWechatCountry(userWechat.getWechatCountry());
           isNeedUpdate = true;
       }
       
       // 省份更新
       if (!userWechat.getWechatProvince().equals(bean.getWechatProvince())) {
           bean.setWechatProvince(userWechat.getWechatProvince());
           isNeedUpdate = true;
       }
       
       // 市更新
       if (!userWechat.getWechatCity().equals(bean.getWechatCity())) {
           bean.setWechatCity(userWechat.getWechatCity());
           isNeedUpdate = true;
       }
       
       // 性别更新
       if (!userWechat.getWechatSex().equals(bean.getWechatSex())) {
           bean.setWechatSex(userWechat.getWechatSex());
           isNeedUpdate = true;
       }
       
       if (isNeedUpdate) {
           result = userWechatService.update(bean, bean.getUserWechatId(), userId, bean.getSystemVersion());
       }
       
       return result;
       
    }

    @Override
    public Boolean updateUserPassword(String userId, UserPassword userPassword, String systemRequestUserId) {
        //删除旧的密码信息
        userService.deleteUserPasswordByUserId(userId, systemRequestUserId);
        //保存新的密码信息
        userPassword.setUserId(userId);
        Boolean result = userService.saveUserPassword(userPassword.getAppId(), userId, userPassword, Util.getRandomUUID(), systemRequestUserId);
        
        return result;
    }

    @Override
    public Boolean updateUserAvatar(String userId, UserAvatar userAvatar, String systemRequestUserId) {
        //删除旧的头像信息
        userService.deleteUserAvatarByUserId(userId, systemRequestUserId);
        //保存新的头像信息
        userAvatar.setUserId(userId);
        Boolean result = userService.saveUserAvatar(userAvatar.getAppId(), userId, userAvatar, Util.getRandomUUID(), systemRequestUserId);
        
        return result;
    }

    @Override
    public Boolean updateUserNickName(String userId, UserNickName userNickName, String systemRequestUserId) {
        //删除旧的用户昵称信息
        userService.deleteUserNickNameByUserId(userId, userNickName.getSystemRequestUserId());
        //保存新的用户昵称信息
        userNickName.setUserId(userId);
        Boolean result = userService.saveUserNickName(userNickName.getAppId(), userId, userNickName, Util.getRandomUUID(), systemRequestUserId);
        return result;
    }

    @Override
    public Boolean registerUserMobile(String appId, String userId, String objectId, String userType,
            String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        Boolean result = userService.save(user, userId, userId);
        
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
    public Boolean registerUserEmail(String appId, String userId, String objectId, String userType,
            String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        Boolean result = userService.save(user, userId, userId);
        
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

}