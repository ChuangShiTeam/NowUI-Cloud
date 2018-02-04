package com.nowui.cloud.base.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.user.repository.UserNotifyRepository;
import com.nowui.cloud.base.user.view.UserNotifyView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserNotify;
import com.nowui.cloud.base.user.mapper.UserNotifyMapper;
import com.nowui.cloud.base.user.service.UserNotifyService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 用户消息队列表业务实现
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Service
public class UserNotifyServiceImpl extends SuperServiceImpl<UserNotifyMapper, UserNotify,UserNotifyRepository,UserNotifyView> implements UserNotifyService {

    @Override
    public Integer countForAdmin(String appId, Boolean userNotifyIsRead, String userNotifyOwerId) {
        Integer count = count(
                new BaseWrapper<UserNotify>()
                        .eq(UserNotify.APP_ID, appId)
                        .likeAllowEmpty(UserNotify.USER_NOTIFY_IS_READ, userNotifyIsRead.toString())
                        .likeAllowEmpty(UserNotify.USER_NOTIFY_OWER_ID, userNotifyOwerId)
                        .eq(UserNotify.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserNotify> listForAdmin(String appId, Boolean userNotifyIsRead, String userNotifyOwerId, Integer pageIndex, Integer pageSize) {
        List<UserNotify> userNotifyList = list(
                new BaseWrapper<UserNotify>()
                        .eq(UserNotify.APP_ID, appId)
                        .likeAllowEmpty(UserNotify.USER_NOTIFY_IS_READ, userNotifyIsRead.toString())
                        .likeAllowEmpty(UserNotify.USER_NOTIFY_OWER_ID, userNotifyOwerId)
                        .eq(UserNotify.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserNotify.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userNotifyList;
    }

    @Override
    public UserNotify getNewNotify(String appId) {

        List<UserNotify> notifys = list(
                new BaseWrapper<UserNotify>()
                        .eq(UserNotify.APP_ID, appId)
                        .eq(UserNotify.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserNotify.SYSTEM_CREATE_TIME))
        );

        return notifys == null ? null : notifys.get(0);
    }

    @Override
    public List<UserNotify> getUserNotifyByUserId(String userNotifyOwerId, String appId, String notifyType) {

        List<UserNotify> userNotifies = mapper.getUserNotifyByUserId(userNotifyOwerId, appId, notifyType);

        if (Util.isNullOrEmpty(userNotifies)) {
            return new ArrayList<>();
        }

        return userNotifies;
    }

}