package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.mapper.UserMobileMapper;
import com.nowui.cloud.base.user.service.UserMobileService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户手机号码业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserMobileServiceImpl extends BaseServiceImpl<UserMobileMapper, UserMobile> implements UserMobileService {

    @Override
    public Integer adminCount(String appId, String userId, String userMobile) {
        Integer count = count(
                new BaseWrapper<UserMobile>()
                        .eq(UserMobile.APP_ID, appId)
                        .likeAllowEmpty(UserMobile.USER_ID, userId)
                        .likeAllowEmpty(UserMobile.USER_MOBILE, userMobile)
                        .eq(UserMobile.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserMobile> adminList(String appId, String userId, String userMobile, Integer pageIndex, Integer pageSize) {
        List<UserMobile> userMobileList = list(
                new BaseWrapper<UserMobile>()
                        .eq(UserMobile.APP_ID, appId)
                        .likeAllowEmpty(UserMobile.USER_ID, userId)
                        .likeAllowEmpty(UserMobile.USER_MOBILE, userMobile)
                        .eq(UserMobile.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserMobile.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userMobileList;
    }

}