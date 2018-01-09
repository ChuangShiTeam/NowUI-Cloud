package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.mapper.UserAvatarMapper;
import com.nowui.cloud.base.user.service.UserAvatarService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户头像业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserAvatarServiceImpl extends BaseServiceImpl<UserAvatarMapper, UserAvatar> implements UserAvatarService {

    @Override
    public Integer adminCount(String appId, String userId, String userAvatar) {
        Integer count = count(
                new BaseWrapper<UserAvatar>()
                        .eq(UserAvatar.APP_ID, appId)
                        .likeAllowEmpty(UserAvatar.USER_ID, userId)
                        .likeAllowEmpty(UserAvatar.USER_AVATAR, userAvatar)
                        .eq(UserAvatar.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserAvatar> adminList(String appId, String userId, String userAvatar, Integer pageIndex, Integer pageSize) {
        List<UserAvatar> userAvatarList = list(
                new BaseWrapper<UserAvatar>()
                        .eq(UserAvatar.APP_ID, appId)
                        .likeAllowEmpty(UserAvatar.USER_ID, userId)
                        .likeAllowEmpty(UserAvatar.USER_AVATAR, userAvatar)
                        .eq(UserAvatar.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAvatar.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userAvatarList;
    }

}