package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.mapper.UserAccountMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户账号业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Override
    public UserAccount findByUserId(String userId) {
        UserAccount userAccount = find(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.USER_ID, userId)
                        .eq(UserAccount.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAccount.SYSTEM_CREATE_TIME))
        );
        
        return userAccount;
    }

}