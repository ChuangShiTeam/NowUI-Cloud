package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.mapper.UserWechatMapper;
import com.nowui.cloud.base.user.service.UserWechatService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户微信业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserWechatServiceImpl extends BaseServiceImpl<UserWechatMapper, UserWechat> implements UserWechatService {

    @Override
    public Integer countForAdmin(String appId, String userId, String wechatNickName) {
        Integer count = count(
                new BaseWrapper<UserWechat>()
                        .eq(UserWechat.APP_ID, appId)
                        .likeAllowEmpty(UserWechat.USER_ID, userId)
                        .likeAllowEmpty(UserWechat.WECHAT_NICK_NAME, wechatNickName)
                        .eq(UserWechat.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserWechat> listForAdmin(String appId, String userId, String wechatNickName, Integer pageIndex, Integer pageSize) {
        List<UserWechat> userWechatList = list(
                new BaseWrapper<UserWechat>()
                        .eq(UserWechat.APP_ID, appId)
                        .likeAllowEmpty(UserWechat.USER_ID, userId)
                        .likeAllowEmpty(UserWechat.WECHAT_NICK_NAME, wechatNickName)
                        .eq(UserWechat.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserWechat.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userWechatList;
    }

}