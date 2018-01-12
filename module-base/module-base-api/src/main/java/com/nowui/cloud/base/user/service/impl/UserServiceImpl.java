package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
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
            user.put(User.USER_ACCOUNT, userAccountService.findByUserId(userId));
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

}