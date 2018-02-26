package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.repository.UserRepository;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserSexService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.base.user.view.UserView;
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
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User, UserRepository, UserView> implements UserService {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAvatarService userAvatarService;
	
	@Autowired
	private UserEmailService userEmailService;
	
	@Autowired
	private UserMobileService userMobileService;
	
	@Autowired
	private UserSexService userSexService;
	
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
        
        return userList;
    }
    
    @Override
    public Boolean updateUserAccount(String appId, String userId, String userAccount, String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (userAccount.equals(userView.getUserAccount())) {
            return true;
        }
        
        // 删除旧的用户账号信息
        userAccountService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户账号信息
        UserAccount userAccountBean = new UserAccount();
        userAccountBean.setAppId(appId);
        userAccountBean.setUserId(userId);
        userAccountBean.setUserAccount(userAccount);
        UserAccount bean = userAccountService.save(userAccountBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        
        if (bean != null) {
            // 更新用户视图信息到MongoDB
            userView.setUserAccount(userAccount);
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

     @Override
    public Boolean updateUserEmail(String appId, String userId, String userEmail, String systemRequestUserId) {
         UserView userView = find(userId);
         
         if (userView == null) {
             throw new RuntimeException("用户不存在");
         }
         
         if (userEmail.equals(userView.getUserEmail())) {
             return true;
         }
         
         // 删除旧的用户邮箱信息
         userEmailService.deleteByUserId(userId, systemRequestUserId);
         
         // 保存新的用户邮箱信息
         UserEmail userEmailBean = new UserEmail();
         userEmailBean.setAppId(appId);
         userEmailBean.setUserId(userId);
         userEmailBean.setUserEmail(userEmail);
         UserEmail bean = userEmailService.save(userEmailBean, Util.getRandomUUID(), systemRequestUserId);
         
         Boolean result = false;
         
         if (bean != null) {
             // 更新用户视图信息到MongoDB
             userView.setUserEmail(userEmail);
             
             update(userView);
             
             result = true;
         }
         
         return result;
    }

    @Override
    public Boolean updateUserMobile(String appId, String userId, String userMobile, String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (userMobile.equals(userView.getUserMobile())) {
            return true;
        }
        
        // 删除旧的用户手机号码信息
        userMobileService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户手机号码信息
        UserMobile userMobileBean = new UserMobile();
        userMobileBean.setAppId(appId);
        userMobileBean.setUserId(userId);
        userMobileBean.setUserMobile(userMobile);
        UserMobile bean = userMobileService.save(userMobileBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        
        if (bean != null) {
            // 更新用户视图信息到MongoDB
            userView.setUserMobile(userMobile);
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

    @Override
    public Boolean updateUserSex(String appId, String userId, String userSex, String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (userSex.equals(userView.getUserSex())) {
            return true;
        }
        
        // 删除旧的用户性别信息
        userSexService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户性别信息
        UserSex userSexBean = new UserSex();
        userSexBean.setAppId(appId);
        userSexBean.setUserId(userId);
        userSexBean.setUserSex(userSex);
        UserSex bean = userSexService.save(userSexBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        
        if (bean != null) {
            // 更新用户视图信息到MongoDB
            userView.setUserSex(userSex);
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

    @Override
    public UserView findByUserTypeAndAccount(String appId, String userType, String userAccount) {
        Criteria criteria = Criteria.where(UserView.APP_ID).is(appId)
                .and(UserView.USER_TYPE).is(userType)
                .and(UserView.USER_ACCOUNT).is(userAccount)
                .and(UserView.SYSTEM_STATUS).is(true);
        
        Query query = new Query(criteria);
        
        return find(query);
    }

    @Override
    public Boolean checkUserAccount(String appId, String userType, String userAccount) {
        UserView userView = findByUserTypeAndAccount(appId, userType, userAccount);
        
        return userView != null;
    }

    @Override
    public Boolean updateUserPassword(String appId, String userId, String userPassword, String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (Util.generatePassword(userPassword).equals(userView.getUserPassword())) {
            return true;
        }
        // 删除旧的用户密码信息
        userPasswordService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户密码信息
        UserPassword userPasswordBean = new UserPassword();
        userPasswordBean.setAppId(appId);
        userPasswordBean.setUserId(userId);
        userPasswordBean.setUserPassword(Util.generatePassword(userPassword));
        UserPassword bean = userPasswordService.save(userPasswordBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        if (bean != null) {
            userView.setUserPassword(Util.generatePassword(userPassword));
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

    @Override
    public Boolean updateUserNickName(String appId, String userId, String userNickName,
            String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (userNickName.equals(userView.getUserNickName())) {
            return true;
        }
        // 删除旧的用户昵称信息
        userNickNameService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户昵称信息
        UserNickName userNickNameBean = new UserNickName();
        userNickNameBean.setAppId(appId);
        userNickNameBean.setUserId(userId);
        userNickNameBean.setUserNickName(userNickName);
        UserNickName bean = userNickNameService.save(userNickNameBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        if (bean != null) {
            userView.setUserNickName(userNickName);
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

    @Override
    public Boolean updateUserAvatar(String appId, String userId, String userAvatar, String userAvatarPath, String systemRequestUserId) {
        UserView userView = find(userId);
        
        if (userView == null) {
            throw new RuntimeException("用户不存在");
        }
        
        if (userAvatar.equals(userView.getUserAvatar())) {
            return true;
        }
        
        // 删除旧的用户头像信息
        userAvatarService.deleteByUserId(userId, systemRequestUserId);
        
        // 保存新的用户头像信息
        UserAvatar userAvatarBean = new UserAvatar();
        userAvatarBean.setAppId(appId);
        userAvatarBean.setUserId(userId);
        userAvatarBean.setUserAvatar(userAvatar);
        UserAvatar bean = userAvatarService.save(userAvatarBean, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean result = false;
        if (bean != null) {
            userView.setUserAvatar(userAvatar);
            userView.setUserAvatarPath(userAvatarPath);
            
            update(userView);
            
            result = true;
        }
        
        return result;
    }

}