package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.mapper.UserPasswordMapper;
import com.nowui.cloud.base.user.repository.UserPasswordRepository;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.view.UserPasswordView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户密码业务实现
 *
 * @author marcus
 *
 * 2018-01-11
 */
@Service
public class UserPasswordServiceImpl extends BaseServiceImpl<UserPasswordMapper, UserPassword, UserPasswordRepository, UserPasswordView> implements UserPasswordService {

    @Override
    public UserPassword findByUserId(String userId) {
        UserPassword userPassword = find(
                new BaseWrapper<UserPassword>()
                        .eq(UserPassword.USER_ID, userId)
                        .eq(UserPassword.SYSTEM_STATUS, true)
        );
        return userPassword;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserPassword> userPasswordList = list(
                new BaseWrapper<UserPassword>()
                        .eq(UserPassword.USER_ID, userId)
                        .eq(UserPassword.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserPassword.SYSTEM_CREATE_TIME))
        );
        if (userPasswordList != null && userPasswordList.size() > 0) {
            for (UserPassword userPassword : userPasswordList) {
                delete(userPassword.getUserPasswordId(), systemUpdateUserId, userPassword.getSystemVersion());
            }
        }
        
    }


}