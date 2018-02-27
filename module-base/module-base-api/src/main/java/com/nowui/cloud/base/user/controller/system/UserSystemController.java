package com.nowui.cloud.base.user.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.router.UserAvatarRouter;
import com.nowui.cloud.base.user.router.UserNickNameRouter;
import com.nowui.cloud.base.user.router.UserSexRouter;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserSexService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.base.user.view.UserAvatarView;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;
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
public class UserSystemController extends BaseController implements UserRpc {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAvatarService userAvatarService;
	
	@Autowired
    private UserEmailService userEmailService;
	
	@Autowired
    private UserSexService userSexService;
	
	@Autowired
    private UserMobileService userMobileService;
	
	@Autowired
    private UserNickNameService userNickNameService;
	
	@Autowired
    private UserPasswordService userPasswordService;
	
	@Autowired
    private UserWechatService userWechatService;
	
	@Override
	public Integer countV1(String appId, String userType) {
		return userService.count(appId, userType);
	}

	@Override
	public List<User> listV1(String appId, String userType, Integer pageIndex, Integer pageSize) {
		return userService.list(appId, userType, pageIndex, pageSize);
	}

    @Override
    public UserView findV1(String userId) {
        return userService.find(userId);
	}

    @Override
    public UserView findByUserTypeAndAccountV1(String appId, String userType, String userAccount) {
        return userService.findByUserTypeAndAccount(appId, userType, userAccount);
    }

    @Override
    public Boolean checkUserPasswordV1(String userId, String userPassword) {
        if (Util.isNullOrEmpty(userId) || Util.isNullOrEmpty(userPassword)) {
            return false;
        }
        
        UserView userView = userService.find(userId);
        
        if (userView == null) {
            return false;
        }
        
        String encryptPassword = Util.generatePassword(userPassword);
        if (encryptPassword.equals(userView.getUserPassword())) {
            return true;
        }
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
//        User user = new User();
//        user.setAppId(appId);
//        user.setUserId(userId);
//        user.setObjectId(objectId);
//        user.setUserType(userType);
//
//        User result = userService.save(user, userId, systemRequestUserId);
//        if (result != null) {
//            userWechat.setAppId(appId);
//            userWechat.setUserId(userId);
//            
//            if (!Util.isNullOrEmpty(userWechat.getWechatHeadImgUrl()) && !Util.isNullOrEmpty(userWechat.getWechatHeadImgFileId())) {
////                // 保存用户头像
////                UserAvatar userAvatar = new UserAvatar();
////                userAvatar.setUserAvatar(userWechat.getWechatHeadImgFileId());
////                userService.saveUserAvatar(appId, userId, userAvatar, Util.getRandomUUID(), systemRequestUserId);
//            }
//            
//            if (!Util.isNullOrEmpty(userWechat.getWechatNickName())) {
//                // 保存用户昵称
////                UserNickName userNickName = new UserNickName();
////                userNickName.setUserNickName(userWechat.getWechatNickName());
////                
////                userService.saveUserNickName(appId, userId, userNickName, Util.getRandomUUID(), systemRequestUserId);
//            }
//            // 保存用户微信头像
//            userService.saveUserWechat(appId, userId, userWechat, Util.getRandomUUID(), systemRequestUserId);
//
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }

    @Override
    public Boolean updateUserWechatV1(String userId, UserWechat userWechat, String systemRequestUserId) {
        
        UserView user = userService.find(userId);

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
    public Boolean updateUserSexV1(String appId, String userId, String userSex, String systemRequestUserId) {

        Boolean result = userService.updateUserSex(appId, userId, userSex, systemRequestUserId);

        if (result) {
            UserSex userSexBean = new UserSex();
            userSexBean.setAppId(appId);
            userSexBean.setUserId(userId);
            userSexBean.setUserSex(userSex);
            // 发送用户身份更新消息
            sendMessage(userSexBean, UserSexRouter.USER_SEX_V1_UPDATE, appId, systemRequestUserId);

        }   
        
        return result;
    }

    @Override
    public Boolean registerUserMobileV1(String appId, String userId, String objectId, String userType,
            String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        User result = userService.save(user, userId, systemRequestUserId);
        
        Boolean flag = false;
        
        if (result != null) {
            // 保存用户账号
            UserAccount userAccountBean = new UserAccount();
            userAccountBean.setAppId(appId);
            userAccountBean.setUserId(userId);
            userAccountBean.setUserAccount(userAccount);
            userAccountService.save(userAccountBean, Util.getRandomUUID(), userId);
            // 保存用户密码
            UserPassword userPasswordbean = new UserPassword();
            userPasswordbean.setAppId(appId);
            userPasswordbean.setUserId(userId);
            userPasswordbean.setUserPassword(userPassword);
            userPasswordService.save(userPasswordbean, Util.getRandomUUID(), userId);
            // 保存用户手机号码
            UserMobile userMobile = new UserMobile();
            userMobile.setAppId(appId);
            userMobile.setUserId(userId);
            userMobile.setUserMobile(userAccount);
            userMobileService.save(userMobile, Util.getRandomUUID(), userId);

            // 保存用户视图信息到mongodb
            
            UserView userView = JSON.parseObject(result.toJSONString(), UserView.class);
            userView.setUserAccount(userAccount);
            userView.setUserPassword(userPassword);
            userView.setUserMobile(userAccount);
            
            userService.save(userView);
            
            flag = true;
        } 
        
        return flag;
    }

    @Override
    public Boolean registerUserEmailV1(String appId, String userId, String objectId, String userType, String userAccount, String userPassword, String systemRequestUserId) {
        User user = new User();
        user.setUserId(userId);
        user.setAppId(appId);
        user.setUserType(userType);
        user.setObjectId(objectId);
        
        User result = userService.save(user, userId,systemRequestUserId);
        
        Boolean flag = false;
        
        if (result != null) {
            // 保存用户账号
            UserAccount userAccountBean = new UserAccount();
            userAccountBean.setAppId(appId);
            userAccountBean.setUserId(userId);
            userAccountBean.setUserAccount(userAccount);
            userAccountService.save(userAccountBean, Util.getRandomUUID(), systemRequestUserId);
            // 保存用户密码
            UserPassword userPasswordbean = new UserPassword();
            userPasswordbean.setAppId(appId);
            userPasswordbean.setUserId(userId);
            userPasswordbean.setUserPassword(userPassword);
            userPasswordService.save(userPasswordbean, Util.getRandomUUID(), userId);
            // 保存用户邮箱
            UserEmail userEmail = new UserEmail();
            userEmail.setAppId(appId);
            userEmail.setUserId(userId);
            userEmail.setUserEmail(userAccount);
            userEmailService.save(userEmail, Util.getRandomUUID(), userId);

            // 保存用户视图到MongoDB
            UserView userView = JSON.parseObject(result.toJSONString(), UserView.class);
            userView.setUserAccount(userAccount);
            userView.setUserPassword(userPassword);
            userView.setUserEmail(userAccount);
            
            userService.save(userView);
            
            flag = true;
        } 
        
        return flag;
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

    @Override
    public Boolean checkUserAccountV1(String appId, String userType, String userAccount) {
        return userService.checkUserAccount(appId, userType, userAccount);
    }

    @Override
    public Boolean updateUserAvatarV1(String appId, String userId, String userAvatarFileId, String userAvatarFilePath,
            String systemRequestUserId) {
        Boolean result = userService.updateUserAvatar(appId, userId, userAvatarFileId, userAvatarFilePath, systemRequestUserId);

        if (result) {
            // 发送用户头像更新消息
            UserAvatar userAvatarBean = new UserAvatar();
            userAvatarBean.setAppId(appId);
            userAvatarBean.setUserId(userId);
            userAvatarBean.setUserAvatarFileId(userAvatarFileId);
            userAvatarBean.setUserAvatarFilePath(userAvatarFilePath);
            sendMessage(userAvatarBean, UserAvatarRouter.USER_AVATAR_V1_UPDATE, appId, systemRequestUserId);
        }                
        
        return result;
    }

    @Override
    public Boolean updateUserNickNameV1(String appId, String userId, String userNickName, String systemRequestUserId) {
        Boolean result = userService.updateUserNickName(appId, userId, userNickName, systemRequestUserId);

        if (result) {
            // 发送用户昵称更新消息
            UserNickName userNickNameBean = new UserNickName();
            userNickNameBean.setAppId(appId);
            userNickNameBean.setUserId(userId);
            userNickNameBean.setUserNickName(userNickName);
            sendMessage(userNickNameBean, UserNickNameRouter.USER_NICK_NAME_V1_UPDATE, appId, systemRequestUserId);
        }  
        
        return result;
    }

}