package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.user.repository.UserRepository;
import com.nowui.cloud.base.user.router.*;
import com.nowui.cloud.base.user.view.UserIdcardView;
import com.nowui.cloud.base.user.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 用户业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User,UserRepository,UserView> implements UserService {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAvatarService userAvatarService;
	
	@Autowired
	private UserEmailService userEmailService;
	
	@Autowired
	private UserMobileService userMobileService;
	
	@Autowired
	private UserIdcardService userIdcardService;
	
	@Autowired
	private UserNickNameService userNickNameService;
	
	@Autowired
    private UserWechatService userWechatService;
	
	@Autowired
	private UserPasswordService userPasswordService;
	
    @Override
    public Integer count(String appId, String userType) {
        Integer count = count(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .eq(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<User> list(String appId, String userType, Integer pageIndex, Integer pageSize) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .eq(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(User.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
        
        //查询用户相关信息
        for (User user : userList) {
            user.putAll(findById(user.getUserId()));
        }
        return userList;
    }
    
    @Override
    public User findByObjectIdAndUserType(String objectId, String userType) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.OBJECT_ID, objectId)
                        .eq(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(User.SYSTEM_CREATE_TIME))
        );
        if (userList == null || userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Boolean deleteByObjectIdAndUserType(String objectId, String userType, String systemRequestUserId) {
        User user = findByObjectIdAndUserType(objectId, userType);
        if (user == null) {
            return false;
        }
//        return delete(user.getUserId(), systemRequestUserId, user.getSystemVersion());

        return delete(user.getUserId(),user.getAppId(), UserRouter.USER_V1_DELETE,systemRequestUserId,
                user.getSystemVersion());
    }

    @Override
    public UserView findById(String userId) {
        UserView user = (UserView) redisTemplate.opsForValue().get(getItemCacheName(userId));
        
        if (user == null) {
            user = find(userId);
            
            // 查询用户账号
            UserAccount userAccount = userAccountService.findByUserId(userId);
            user.put(User.USER_ACCOUNT, userAccount == null ? "" : userAccount.getUserAccount());
            
            // 查询用户密码
            UserPassword userPassword = userPasswordService.findByUserId(userId);
            user.put(User.USER_PASSWORD, userPassword == null ? "" : userPassword.getUserPassword());
            
            // 查询用户头像
            UserAvatar userAvatar = userAvatarService.findByUserId(userId);
            user.put(User.USER_AVATAR, userAvatar == null ? "" : userAvatar.getUserAvatar());
            
            // 查询用户身份证
            UserIdcard userIdcard = userIdcardService.findByUserId(userId);
            user.put(User.USER_NAME, userIdcard == null ? "" : userIdcard.getUserName());
            user.put(User.USER_IDCARD_NUMBER, userIdcard == null ? "" : userIdcard.getUserIdcardNumber());
            user.put(User.USER_SEX, userIdcard == null ? "" : userIdcard.getUserSex());
            
            // 查询用户手机号码
            UserMobile userMobile = userMobileService.findByUserId(userId);
            user.put(User.USER_MOBILE, userMobile == null ? "" : userMobile.getUserMobile());
            
            // 查询用户邮箱
            UserEmail userEmail = userEmailService.findByUserId(userId);
            user.put(User.USER_EMAIL, userEmail == null ? "" : userEmail.getUserEmail());
            
            // 查询用户昵称
            UserNickName userNickName = userNickNameService.findByUserId(userId);
            user.put(User.USER_NICK_NAME, userNickName == null ? "" : userNickName.getUserNickName());
           
            // 查询用户微信
            user.put(User.USER_WECHAT, userWechatService.findByUserId(userId));

            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
        
        return user;
    }

    @Override
    public Boolean saveUserAccount(String appId, String userId, UserAccount userAccount, String userAccountId,
            String systemRequestUserId) {
        userAccount.setAppId(appId);
        userAccount.setUserId(userId);
        Boolean result = userAccountService.save(userAccount,userAccountId,appId, UserAccountRouter.USER_ACCOUNT_V1_SAVE,
                systemRequestUserId);
//        Boolean result = userAccountService.save(userAccount, userAccountId, systemRequestUserId);

        if (result) {
            // 更新用户缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_ACCOUNT, userAccountService.find(userAccountId).getUserAccount());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserAccountByUserId(String userId, String systemRequestUserId) {
        userAccountService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_ACCOUNT, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserPassword(String appId, String userId, UserPassword userPassword, String userPasswordId,
            String systemRequestUserId) {
        userPassword.setAppId(appId);
        userPassword.setUserId(userId);
        
        userPassword.setUserPassword(Util.generatePassword(userPassword.getUserPassword()));
        Boolean result = userPasswordService.save(userPassword, userPasswordId,appId,
                UserPasswordRouter.USER_PASSWORD_V1_SAVE,systemRequestUserId);
//        Boolean result = userPasswordService.save(userPassword, userPasswordId, systemRequestUserId);

        if (result) {
            // 更新用户缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_PASSWORD, userPasswordService.find(userPasswordId).getUserPassword());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserPasswordByUserId(String userId, String systemRequestUserId) {
        userPasswordService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_PASSWORD, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserEmail(String appId, String userId, UserEmail userEmail, String userEmailId,
            String systemRequestUserId) {
        userEmail.setAppId(appId);
        userEmail.setUserId(userId);
        
        Boolean result = userEmailService.save(userEmail, userEmailId,appId, UserEmailRouter.USER_EMAIL_V1_SAVE,
                systemRequestUserId);

//        Boolean result = userEmailService.save(userEmail, userEmailId, systemRequestUserId);
        if (result) {
            // 更新用户邮箱缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_EMAIL, userEmailService.find(userEmailId).getUserEmail());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserEmailByUserId(String userId, String systemRequestUserId) {
        userEmailService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户邮箱缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_EMAIL, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserAvatar(String appId, String userId, UserAvatar userAvatar, String userAvatarId,
            String systemRequestUserId) {
        userAvatar.setAppId(appId);
        userAvatar.setUserId(userId);
        
//        Boolean result = userAvatarService.save(userAvatar, userAvatarId, systemRequestUserId);
        Boolean result = userAvatarService.save(userAvatar, userAvatarId,appId,UserAvatarRouter.USER_AVATAR_V1_SAVE,
                systemRequestUserId);

        if (result) {
            // 更新用户头像缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_AVATAR, userAvatarService.find(userAvatarId).getUserAvatar());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserAvatarByUserId(String userId, String systemRequestUserId) {
        userAvatarService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户头像缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_AVATAR, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserMobile(String appId, String userId, UserMobile userMobile, String userMobileId,
            String systemRequestUserId) {
        userMobile.setAppId(appId);
        userMobile.setUserId(userId);
        
        Boolean result = userMobileService.save(userMobile, userMobileId,appId,
                UserMobileRouter.USER_MOBILE_V1_SAVE,systemRequestUserId);
//        Boolean result = userMobileService.save(userMobile, userMobileId, systemRequestUserId);

        if (result) {
            // 更新用户手机号码缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_MOBILE, userMobileService.find(userMobileId).getUserMobile());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserMobileByUserId(String userId, String systemRequestUserId) {
        userMobileService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户手机号码缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_MOBILE, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserIdcard(String appId, String userId, UserIdcard userIdcard, String userIdcardId,
            String systemRequestUserId) {
        userIdcard.setAppId(appId);
        userIdcard.setUserId(userId);
        
        Boolean result = userIdcardService.save(userIdcard,userIdcardId,appId, UserIdcardRouter.USER_IDCARD_V1_SAVE,
                systemRequestUserId);
//        Boolean result = userIdcardService.save(userIdcard, userIdcardId, systemRequestUserId);

        if (result) {
            // 更新用户身份证缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                UserIdcardView bean = userIdcardService.find(userIdcardId);
//                UserIdcard bean = userIdcardService.find(userIdcardId);
                user.put(User.USER_IDCARD_NUMBER, bean.getUserIdcardNumber());
                user.put(User.USER_SEX, bean.getUserSex());
                user.put(User.USER_NAME, bean.getUserName());
                
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserIdcardByUserId(String userId, String systemRequestUserId) {
        userIdcardService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户身份证缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_IDCARD_NUMBER, "");
            user.put(User.USER_NAME, "");
            user.put(User.USER_SEX, "");
            
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
        
    }

    @Override
    public Boolean saveUserNickName(String appId, String userId, UserNickName userNickName, String userNickNameId,
            String systemRequestUserId) {
        userNickName.setAppId(appId);
        userNickName.setUserId(userId);
        
        Boolean result = userNickNameService.save(userNickName, userNickNameId,appId,
                UserNickNameRouter.USER_NICK_NAME_V1_SAVE,systemRequestUserId);
//        Boolean result = userNickNameService.save(userNickName, userNickNameId, systemRequestUserId);


        if (result) {
            // 更新用户昵称缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_NICK_NAME, userNickNameService.find(userNickNameId).getUserNickName());
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserNickNameByUserId(String userId, String systemRequestUserId) {
        userNickNameService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户昵称缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_NICK_NAME, "");
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public Boolean saveUserWechat(String appId, String userId, UserWechat userWechat, String userWechatId,
            String systemRequestUserId) {
        userWechat.setAppId(appId);
        userWechat.setUserId(userId);
        
        Boolean result = userWechatService.save(userWechat, userWechatId,appId,UserWechatRouter.USER_WECHAT_V1_SAVE,systemRequestUserId);
//        Boolean result = userWechatService.save(userWechat, userWechatId, systemRequestUserId);


        if (result) {
            // 更新用户微信缓存
            User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_WECHAT, userWechatService.find(userWechatId));
                redisTemplate.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserWechatByUserId(String userId, String systemRequestUserId) {
        userWechatService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户微信缓存
        User user = (User) redisTemplate.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.put(User.USER_WECHAT, null);
            redisTemplate.opsForValue().set(getItemCacheName(userId), user);
        }
    }
    
    @Override
    public UserAccount findByUserAccount(String appId, String userAccount) {
        return userAccountService.findByUserAccount(appId, userAccount);
    }

    @Override
    public UserWechat findByOpenIdAndUnionId(String appId, String wechatOpenId, String wechatUnionId) {
        return userWechatService.findByOpenIdAndUnionId(appId, wechatOpenId, wechatUnionId);
    }

}