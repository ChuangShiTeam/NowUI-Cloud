package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.user.repository.UserEmailRepository;
import com.nowui.cloud.base.user.router.UserEmailRouter;
import com.nowui.cloud.base.user.view.UserEmailView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.mapper.UserEmailMapper;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户邮箱业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserEmailServiceImpl extends SuperServiceImpl<UserEmailMapper, UserEmail,UserEmailRepository,UserEmailView> implements UserEmailService {

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
    public void deleteByUserId(String userId, String systemUpdateUserId) {
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