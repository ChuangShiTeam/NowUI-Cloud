package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.mapper.UserWechatMapper;
import com.nowui.cloud.base.user.repository.UserWechatRepository;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.base.user.view.UserWechatView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户微信业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserWechatServiceImpl extends SuperServiceImpl<UserWechatMapper, UserWechat, UserWechatRepository, UserWechatView> implements UserWechatService {

    @Override
    public UserWechat findByUserId(String userId) {
        UserWechat userWechat = find(
                new BaseWrapper<UserWechat>()
                    .eq(UserWechat.USER_ID, userId)
                    .eq(UserWechat.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserWechat.SYSTEM_CREATE_TIME))
        );
        return userWechat;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserWechat> userWechatList = list(
                new BaseWrapper<UserWechat>()
                        .eq(UserWechat.USER_ID, userId)
                        .eq(UserWechat.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserWechat.SYSTEM_CREATE_TIME))
        );
        if (userWechatList != null && userWechatList.size() > 0) {
            for (UserWechat userWechat : userWechatList) {
                delete(userWechat.getUserWechatId(), systemUpdateUserId, userWechat.getSystemVersion());
            }
        }
    }

    @Override
    public UserWechat findByOpenIdAndUnionId(String appId, String wechatOpenId, String wechatUnionId) {
        UserWechat userWechat = find(
            new BaseWrapper<UserWechat>()
                .eq(UserWechat.APP_ID, appId)
                .eq(UserWechat.WECHAT_OPEN_ID, wechatOpenId)
                .eq(UserWechat.WECHAT_UNION_ID, wechatUnionId)
                .eq(UserWechat.APP_ID, appId)
                .eq(UserWechat.SYSTEM_STATUS, true)
        );
        return userWechat;
    }

}