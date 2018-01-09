package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.service.UserMessageService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserRoleService;
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
	private FileRpc fileRpc;
	
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
	private UserMessageService userMessageService;
	
	@Autowired
	private UserNickNameService userNickNameService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
    private UserWechatService userWechatService;
	
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
        // 查询用户账号
        UserAccount userAccount = userAccountService.find(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.USER_ID, userId)
                        .eq(UserAccount.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAccount.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_ACCOUNT, userAccount);
        // 查询用户头像
        UserAvatar userAvatar = userAvatarService.find(
                new BaseWrapper<UserAvatar>()
                    .eq(UserAvatar.USER_ID, userId)
                    .eq(UserAvatar.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserAvatar.SYSTEM_CREATE_TIME))
        );
        if (userAvatar != null) {
            File file = fileRpc.find(userAvatar.getUserAvatar());
            file.keep(File.FILE_ID, File.FILE_PATH);
            userAvatar.put(UserAvatar.USER_AVATAR, file);
        }
        user.put(User.USER_AVATAR, userAvatar);
        // 查询用户身份证
        UserIdcard userIdcard = userIdcardService.find(
                new BaseWrapper<UserIdcard>()
                    .eq(UserIdcard.USER_ID, userId)
                    .eq(UserIdcard.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserIdcard.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_IDCARD, userIdcard);
        // 查询用户手机号码
        UserMobile userMobile = userMobileService.find(
                new BaseWrapper<UserMobile>()
                    .eq(UserMobile.USER_ID, userId)
                    .eq(UserMobile.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserMobile.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_MOBILE, userMobile);
        //查询用户邮箱
        UserEmail userEmail = userEmailService.find(
                new BaseWrapper<UserEmail>()
                    .eq(UserEmail.USER_ID, userId)
                    .eq(UserEmail.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserEmail.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_EMAIL, userEmail);
        // 查询用户昵称
        UserNickName userNickName = userNickNameService.find(
                new BaseWrapper<UserNickName>()
                    .eq(UserNickName.USER_ID, userId)
                    .eq(UserNickName.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserNickName.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_NICK_NAME, userNickName);
        // 查询用户微信
        UserWechat userWechat = userWechatService.find(
                new BaseWrapper<UserWechat>()
                    .eq(UserWechat.USER_ID, userId)
                    .eq(UserWechat.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserWechat.SYSTEM_CREATE_TIME))
        );
        user.put(User.USER_WECHAT, userWechat);
        return user;
    }

    @Override
    public Boolean save(User user, String userId, UserAccount userAccount, UserNickName userNickName,
            String systemCreateUserId) {
        
        return null;
    }

    @Override
    public Boolean save(User user, String userId, UserMobile userMobile, UserNickName userNickName,
            String systemCreateUserId) {
        return null;
    }

    @Override
    public Boolean save(User user, String userId, UserEmail userEmail, UserNickName userNickName,
            String systemCreateUserId) {
        return null;
    }

    @Override
    public Boolean save(User user, String userId, UserWechat userWechat, String systemCreateUserId) {
        return null;
    }

}