package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.mapper.UserSexMapper;
import com.nowui.cloud.base.user.repository.UserSexRepository;
import com.nowui.cloud.base.user.service.UserSexService;
import com.nowui.cloud.base.user.view.UserSexView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 用户性别业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserSexServiceImpl extends SuperServiceImpl<UserSexMapper, UserSex, UserSexRepository, UserSexView> implements UserSexService {

    @Override
    public UserSex findByUserId(String userId) {
        UserSex userIdcard = find(
                new BaseWrapper<UserSex>()
                    .eq(UserSex.USER_ID, userId)
                    .eq(UserSex.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserSex.SYSTEM_CREATE_TIME))
        );
        return userIdcard;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserSex> userIdcardList = list(
                new BaseWrapper<UserSex>()
                        .eq(UserSex.USER_ID, userId)
                        .eq(UserSex.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserSex.SYSTEM_CREATE_TIME))
        );
        if (userIdcardList != null && userIdcardList.size() > 0) {
            for (UserSex userIdcard : userIdcardList) {
                delete(userIdcard.getUserIdcardId(), systemUpdateUserId, userIdcard.getSystemVersion());
            }
        }
    }

}