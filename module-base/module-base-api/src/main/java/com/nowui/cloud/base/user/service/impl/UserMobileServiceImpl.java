package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.mapper.UserMobileMapper;
import com.nowui.cloud.base.user.repository.UserMobileRepository;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.view.UserMobileView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户手机号码业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserMobileServiceImpl extends BaseServiceImpl<UserMobileMapper, UserMobile, UserMobileRepository, UserMobileView> implements UserMobileService {

    @Override
    public UserMobile findByUserId(String userId) {
        UserMobile userMobile = find(
                new BaseWrapper<UserMobile>()
                    .eq(UserMobile.USER_ID, userId)
                    .eq(UserMobile.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserMobile.SYSTEM_CREATE_TIME))
        );
        return userMobile;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserMobile> userMobileList = list(
                new BaseWrapper<UserMobile>()
                        .eq(UserMobile.USER_ID, userId)
                        .eq(UserMobile.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserMobile.SYSTEM_CREATE_TIME))
        );
        if (userMobileList != null && userMobileList.size() > 0) {
            for (UserMobile userMobile : userMobileList) {
                delete(userMobile.getUserMobileId(), systemUpdateUserId, userMobile.getSystemVersion());

            }
        }
    }

}