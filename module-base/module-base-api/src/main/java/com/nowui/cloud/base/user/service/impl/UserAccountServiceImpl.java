package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.mapper.UserAccountMapper;
import com.nowui.cloud.base.user.repository.UserAcRepository;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.view.UserAccountView;
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
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccountMapper, UserAccount, UserAcRepository, UserAccountView> implements UserAccountService {

    @Override
    public UserAccount findByUserId(String userId) {
        UserAccount userAccount = find(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.USER_ID, userId)
                        .eq(UserAccount.SYSTEM_STATUS, true)
        );
        return userAccount;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserAccount> userAccountList = list(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.USER_ID, userId)
                        .eq(UserAccount.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAccount.SYSTEM_CREATE_TIME))
        );
        if (userAccountList != null && userAccountList.size() > 0) {
            for (UserAccount userAccount : userAccountList) {
                delete(userAccount.getUserAccountId(), systemUpdateUserId, userAccount.getSystemVersion());
            }
        }
    }

    @Override
    public UserAccount findByUserAccount(String appId, String userAccount) {
        UserAccount bean = find( 
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.APP_ID, appId)
                        .eq(UserAccount.USER_ACCOUNT, userAccount)
                        .eq(UserAccount.SYSTEM_STATUS, true)
        );
        return bean;
    }

}