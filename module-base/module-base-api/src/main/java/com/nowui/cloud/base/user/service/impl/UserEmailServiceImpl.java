package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.mapper.UserEmailMapper;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

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
    public UserEmail findByUserId(String userId) {
        UserEmail userEmail = find(
                new BaseWrapper<UserEmail>()
                    .eq(UserEmail.USER_ID, userId)
                    .eq(UserEmail.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserEmail.SYSTEM_CREATE_TIME))
        );
        return userEmail;
    }

    @Override
    public void deletByUserId(String userId, String systemUpdateUserId) {
        List<UserEmail> userEmailList = list(
                new BaseWrapper<UserEmail>()
                        .eq(UserEmail.USER_ID, userId)
                        .eq(UserEmail.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserEmail.SYSTEM_CREATE_TIME))
        );
        if (userEmailList != null && userEmailList.size() > 0) {
            for (UserEmail userEmail : userEmailList) {
                delete(userEmail.getUserEmailId(), systemUpdateUserId, userEmail.getSystemVersion());
            }
        }
    }

}