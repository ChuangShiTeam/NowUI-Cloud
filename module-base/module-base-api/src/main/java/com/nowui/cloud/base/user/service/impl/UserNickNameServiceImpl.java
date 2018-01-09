package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.mapper.UserNickNameMapper;
import com.nowui.cloud.base.user.service.UserNickNameService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户昵称业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserNickNameServiceImpl extends BaseServiceImpl<UserNickNameMapper, UserNickName> implements UserNickNameService {

    @Override
    public Integer countForAdmin(String appId, String userId, String userNickName) {
        Integer count = count(
                new BaseWrapper<UserNickName>()
                        .eq(UserNickName.APP_ID, appId)
                        .likeAllowEmpty(UserNickName.USER_ID, userId)
                        .likeAllowEmpty(UserNickName.USER_NICK_NAME, userNickName)
                        .eq(UserNickName.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserNickName> listForAdmin(String appId, String userId, String userNickName, Integer pageIndex, Integer pageSize) {
        List<UserNickName> userNickNameList = list(
                new BaseWrapper<UserNickName>()
                        .eq(UserNickName.APP_ID, appId)
                        .likeAllowEmpty(UserNickName.USER_ID, userId)
                        .likeAllowEmpty(UserNickName.USER_NICK_NAME, userNickName)
                        .eq(UserNickName.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserNickName.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userNickNameList;
    }

}