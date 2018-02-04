package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.user.repository.UserAvatarRepository;
import com.nowui.cloud.base.user.router.UserAvatarRouter;
import com.nowui.cloud.base.user.view.UserAvatarView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.mapper.UserAvatarMapper;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户头像业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserAvatarServiceImpl extends SuperServiceImpl<UserAvatarMapper, UserAvatar,UserAvatarRepository,UserAvatarView> implements UserAvatarService {

    @Override
    public UserAvatar findByUserId(String userId) {
        UserAvatar userAvatar = find(
                new BaseWrapper<UserAvatar>()
                    .eq(UserAvatar.USER_ID, userId)
                    .eq(UserAvatar.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserAvatar.SYSTEM_CREATE_TIME))
        );
        
        return userAvatar;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserAvatar> userAvatarList = list(
                new BaseWrapper<UserAvatar>()
                        .eq(UserAvatar.USER_ID, userId)
                        .eq(UserAvatar.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAvatar.SYSTEM_CREATE_TIME))
        );
        if (userAvatarList != null && userAvatarList.size() > 0) {
            for (UserAvatar userAvatar : userAvatarList) {
                delete(userAvatar.getUserAvatarId(), systemUpdateUserId, UserAvatarRouter.USER_AVATAR_V1_DELETE,userAvatar.getSystemUpdateUserId(),
                        userAvatar.getSystemVersion());
//                delete(userAvatar.getUserAvatarId(), systemUpdateUserId, userAvatar.getSystemVersion());
            }
        }
    }

}