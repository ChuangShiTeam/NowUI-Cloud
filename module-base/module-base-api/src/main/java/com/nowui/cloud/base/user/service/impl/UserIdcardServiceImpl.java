package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.mapper.UserIdcardMapper;
import com.nowui.cloud.base.user.service.UserIdcardService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户身份证业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserIdcardServiceImpl extends BaseServiceImpl<UserIdcardMapper, UserIdcard> implements UserIdcardService {

    @Override
    public Integer adminCount(String appId, String userId, String userName, String userIdcardNumber) {
        Integer count = count(
                new BaseWrapper<UserIdcard>()
                        .eq(UserIdcard.APP_ID, appId)
                        .likeAllowEmpty(UserIdcard.USER_ID, userId)
                        .likeAllowEmpty(UserIdcard.USER_NAME, userName)
                        .likeAllowEmpty(UserIdcard.USER_IDCARD_NUMBER, userIdcardNumber)
                        .eq(UserIdcard.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserIdcard> adminList(String appId, String userId, String userName, String userIdcardNumber, Integer pageIndex, Integer pageSize) {
        List<UserIdcard> userIdcardList = list(
                new BaseWrapper<UserIdcard>()
                        .eq(UserIdcard.APP_ID, appId)
                        .likeAllowEmpty(UserIdcard.USER_ID, userId)
                        .likeAllowEmpty(UserIdcard.USER_NAME, userName)
                        .likeAllowEmpty(UserIdcard.USER_IDCARD_NUMBER, userIdcardNumber)
                        .eq(UserIdcard.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserIdcard.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userIdcardList;
    }

}