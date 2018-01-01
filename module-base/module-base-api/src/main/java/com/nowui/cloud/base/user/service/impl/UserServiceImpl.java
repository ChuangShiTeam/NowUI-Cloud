package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Integer adminCount(String appId, String userType, String userAccount, String userName, String userMobile) {
        Integer count = count(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .likeAllowEmpty(User.USER_TYPE, userType)
                        .likeAllowEmpty(User.USER_ACCOUNT, userAccount)
                        .likeAllowEmpty(User.USER_NAME, userName)
                        .likeAllowEmpty(User.USER_MOBILE, userMobile)
                        .eq(User.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<User> adminList(String appId, String userType, String userAccount, String userName, String userMobile, Integer m, Integer n) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .likeAllowEmpty(User.USER_TYPE, userType)
                        .likeAllowEmpty(User.USER_ACCOUNT, userAccount)
                        .likeAllowEmpty(User.USER_NAME, userName)
                        .likeAllowEmpty(User.USER_MOBILE, userMobile)
                        .eq(User.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(User.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return userList;
    }

}