package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.mapper.UserEmailMapper;
import com.nowui.cloud.base.user.service.UserEmailService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户邮箱业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserEmailServiceImpl extends BaseServiceImpl<UserEmailMapper, UserEmail> implements UserEmailService {

    @Override
    public Integer adminCount(String appId, String userId, String userEmail) {
        Integer count = count(
                new BaseWrapper<UserEmail>()
                        .eq(UserEmail.APP_ID, appId)
                        .likeAllowEmpty(UserEmail.USER_ID, userId)
                        .likeAllowEmpty(UserEmail.USER_EMAIL, userEmail)
                        .eq(UserEmail.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserEmail> adminList(String appId, String userId, String userEmail, Integer pageIndex, Integer pageSize) {
        List<UserEmail> userEmailList = list(
                new BaseWrapper<UserEmail>()
                        .eq(UserEmail.APP_ID, appId)
                        .likeAllowEmpty(UserEmail.USER_ID, userId)
                        .likeAllowEmpty(UserEmail.USER_EMAIL, userEmail)
                        .eq(UserEmail.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserEmail.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userEmailList;
    }

}