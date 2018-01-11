package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.mapper.UserIdcardMapper;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

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
    public UserIdcard findByUserId(String userId) {
        UserIdcard userIdcard = find(
                new BaseWrapper<UserIdcard>()
                    .eq(UserIdcard.USER_ID, userId)
                    .eq(UserIdcard.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserIdcard.SYSTEM_CREATE_TIME))
        );
        return userIdcard;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserIdcard> userIdcardList = list(
                new BaseWrapper<UserIdcard>()
                        .eq(UserIdcard.USER_ID, userId)
                        .eq(UserIdcard.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserIdcard.SYSTEM_CREATE_TIME))
        );
        if (userIdcardList != null && userIdcardList.size() > 0) {
            for (UserIdcard userIdcard : userIdcardList) {
                delete(userIdcard.getUserIdcardId(), systemUpdateUserId, userIdcard.getSystemVersion());
            }
        }
    }

}