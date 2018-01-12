package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

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
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
	
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
                        .likeAllowEmpty(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<User> list(String appId, String userType, Integer pageIndex, Integer pageSize) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .likeAllowEmpty(User.USER_TYPE, userType)
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
        return delete(user.getUserId(), systemRequestUserId, user.getSystemVersion());
    }

    @Override
    public User findById(String userId) {
        User user = find(userId);
        if (user != null) {
            // 查询用户账号
            user.put(User.USER_ACCOUNT, findUserAccountByUserId(userId));
            // 查询用户密码
            user.put(User.USER_PASSWORD, findUserPasswordByUserId(userId));
            // 查询用户头像
            user.put(User.USER_AVATAR, userAvatarService.findByUserId(userId));
            // 查询用户身份证
            user.put(User.USER_IDCARD, userIdcardService.findByUserId(userId));
            // 查询用户手机号码
            user.put(User.USER_MOBILE, userMobileService.findByUserId(userId));
            // 查询用户邮箱
            user.put(User.USER_EMAIL, userEmailService.findByUserId(userId));
            // 查询用户昵称
            user.put(User.USER_NICK_NAME, userNickNameService.findByUserId(userId));
            // 查询用户微信
            user.put(User.USER_WECHAT, userWechatService.findByUserId(userId));
        }
        
        return user;
    }

    @Override
    public UserAccount findUserAccountByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserAccount userAccount = (UserAccount) user.get(User.USER_ACCOUNT);
            
            if (userAccount != null) {
                return userAccount;
            }
            userAccount = userAccountService.findByUserId(userId);
            
            user.put(User.USER_ACCOUNT, userAccount);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userAccount;
        }
        return userAccountService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserAccount(String appId, String userId, UserAccount userAccount, String userAccountId,
            String systemRequestUserId) {
        userAccount.setAppId(appId);
        userAccount.setUserId(userId);
        Boolean result = userAccountService.save(userAccount, userAccountId, systemRequestUserId);
        if (result) {
            // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_ACCOUNT, userAccountService.find(userAccountId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserAccountByUserId(String userId, String systemRequestUserId) {
        userAccountService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_ACCOUNT);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserPassword findUserPasswordByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserPassword userPassword = (UserPassword) user.get(User.USER_PASSWORD);
            
            if (userPassword != null) {
                return userPassword;
            }
            userPassword = userPasswordService.findByUserId(userId);
            
            user.put(User.USER_PASSWORD, userPassword);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userPassword;
        }
        return userPasswordService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserPassword(String appId, String userId, UserPassword userPassword, String userPasswordId,
            String systemRequestUserId) {
        userPassword.setAppId(appId);
        userPassword.setUserId(userId);
        
        Boolean result = userPasswordService.save(userPassword, userPasswordId, systemRequestUserId);
        
        if (result) {
            // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_PASSWORD, userPasswordService.find(userPasswordId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return null;
    }

    @Override
    public void deleteUserPasswordByUserId(String userId, String systemRequestUserId) {
        userPasswordService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_PASSWORD);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserEmail findUserEmailByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserEmail userEmail = (UserEmail) user.get(User.USER_EMAIL);
            
            if (userEmail != null) {
                return userEmail;
            }
            userEmail = userEmailService.findByUserId(userId);
            
            user.put(User.USER_EMAIL, userEmail);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userEmail;
        }
        return userEmailService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserEmail(String appId, String userId, UserEmail userEmail, String userEmailId,
            String systemRequestUserId) {
        userEmail.setAppId(appId);
        userEmail.setUserId(userId);
        
        Boolean result = userEmailService.save(userEmail, userEmailId, systemRequestUserId);
        
        if (result) {
            // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_EMAIL, userEmailService.find(userEmailId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserEmailByUserId(String userId, String systemRequestUserId) {
        userEmailService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_EMAIL);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserAvatar findUserAvatarByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserAvatar userAvatar = (UserAvatar) user.get(User.USER_AVATAR);
            
            if (userAvatar != null) {
                return userAvatar;
            }
            userAvatar = userAvatarService.findByUserId(userId);
            
            user.put(User.USER_AVATAR, userAvatar);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userAvatar;
        }
        return userAvatarService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserAvatar(String appId, String userId, UserAvatar userAvatar, String userAvatarId,
            String systemRequestUserId) {
        userAvatar.setAppId(appId);
        userAvatar.setUserId(userId);
        
        Boolean result = userAvatarService.save(userAvatar, userAvatarId, systemRequestUserId);
        
        if (result) {
            // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_AVATAR, userAvatarService.find(userAvatarId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserAvatarByUserId(String userId, String systemRequestUserId) {
        userAvatarService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_AVATAR);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserMobile findUserMobileByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserMobile userMobile = (UserMobile) user.get(User.USER_MOBILE);
            
            if (userMobile != null) {
                return userMobile;
            }
            userMobile = userMobileService.findByUserId(userId);
            
            user.put(User.USER_MOBILE, userMobile);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userMobile;
        }
        return userMobileService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserMobile(String appId, String userId, UserMobile userMobile, String userMobileId,
            String systemRequestUserId) {
        userMobile.setAppId(appId);
        userMobile.setUserId(userId);
        
        Boolean result = userMobileService.save(userMobile, userMobileId, systemRequestUserId);
        
        if (result) {
         // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_MOBILE, userMobileService.find(userMobileId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserMobileByUserId(String userId, String systemRequestUserId) {
        userMobileService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_MOBILE);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserIdcard findUserIdcardByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserIdcard userIdcard = (UserIdcard) user.get(User.USER_IDCARD);
            
            if (userIdcard != null) {
                return userIdcard;
            }
            userIdcard = userIdcardService.findByUserId(userId);
            
            user.put(User.USER_IDCARD, userIdcard);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userIdcard;
        }
        return userIdcardService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserIdcard(String appId, String userId, UserIdcard userIdcard, String userIdcardId,
            String systemRequestUserId) {
        userIdcard.setAppId(appId);
        userIdcard.setUserId(userId);
        
        Boolean result = userIdcardService.save(userIdcard, userIdcardId, systemRequestUserId);
        
        if (result) {
         // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_IDCARD, userIdcardService.find(userIdcardId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserIdcardByUserId(String userId, String systemRequestUserId) {
        userIdcardService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_IDCARD);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
        
    }

    @Override
    public UserNickName findUserNickNameByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserNickName userNickName = (UserNickName) user.get(User.USER_NICK_NAME);
            
            if (userNickName != null) {
                return userNickName;
            }
            userNickName = userNickNameService.findByUserId(userId);
            
            user.put(User.USER_IDCARD, userNickName);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userNickName;
        }
        return userNickNameService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserNickName(String appId, String userId, UserNickName userNickName, String userNickNameId,
            String systemRequestUserId) {
        userNickName.setAppId(appId);
        userNickName.setUserId(userId);
        
        Boolean result = userNickNameService.save(userNickName, userNickNameId, systemRequestUserId);
        
        if (result) {
         // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_NICK_NAME, userNickNameService.find(userNickNameId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserNickNameByUserId(String userId, String systemRequestUserId) {
        userNickNameService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_NICK_NAME);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
        }
    }

    @Override
    public UserWechat findUserWechatByUserId(String userId) {
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        
        if (user != null) {
            UserWechat userWechat = (UserWechat) user.get(User.USER_WECHAT);
            
            if (userWechat != null) {
                return userWechat;
            }
            userWechat = userWechatService.findByUserId(userId);
            
            user.put(User.USER_WECHAT, userWechat);
            redis.opsForValue().set(getItemCacheName(userId), user);
            
            return userWechat;
        }
        return userWechatService.findByUserId(userId);
    }

    @Override
    public Boolean saveUserWechat(String appId, String userId, UserWechat userWechat, String userWechatId,
            String systemRequestUserId) {
        userWechat.setAppId(appId);
        userWechat.setUserId(userId);
        
        Boolean result = userWechatService.save(userWechat, userWechatId, systemRequestUserId);
        
        if (result) {
         // 更新用户缓存
            User user = (User) redis.opsForValue().get(getItemCacheName(userId));
            if (user != null) {
                user.put(User.USER_NICK_NAME, userWechatService.find(userWechatId));
                
                redis.opsForValue().set(getItemCacheName(userId), user);
            }
        }
        return result;
    }

    @Override
    public void deleteUserWechatByUserId(String userId, String systemRequestUserId) {
        userWechatService.deleteByUserId(userId, systemRequestUserId);
        
        // 更新用户缓存
        User user = (User) redis.opsForValue().get(getItemCacheName(userId));
        if (user != null) {
            user.remove(User.USER_WECHAT);
            
            redis.opsForValue().set(getItemCacheName(userId), user);
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