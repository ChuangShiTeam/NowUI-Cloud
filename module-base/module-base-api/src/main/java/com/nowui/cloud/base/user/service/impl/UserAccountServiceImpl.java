package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.mapper.UserAccountMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
    public Integer countForAdmin(String appId, String userId, String userAccount) {
        Integer count = count(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.APP_ID, appId)
                        .likeAllowEmpty(UserAccount.USER_ID, userId)
                        .likeAllowEmpty(UserAccount.USER_ACCOUNT, userAccount)
                        .eq(UserAccount.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserAccount> listForAdmin(String appId, String userId, String userAccount, Integer pageIndex, Integer pageSize) {
        List<UserAccount> userAccountList = list(
                new BaseWrapper<UserAccount>()
                        .eq(UserAccount.APP_ID, appId)
                        .likeAllowEmpty(UserAccount.USER_ID, userId)
                        .likeAllowEmpty(UserAccount.USER_ACCOUNT, userAccount)
                        .eq(UserAccount.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserAccount.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userAccountList;
    }

}