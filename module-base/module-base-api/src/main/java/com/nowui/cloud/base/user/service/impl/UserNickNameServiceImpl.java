package com.nowui.cloud.base.user.service.impl;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.mapper.UserNickNameMapper;
import com.nowui.cloud.base.user.repository.UserNickNameRepository;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.view.UserNickNameView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户昵称业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserNickNameServiceImpl extends BaseServiceImpl<UserNickNameMapper, UserNickName, UserNickNameRepository, UserNickNameView> implements UserNickNameService {

    @Override
    public UserNickName findByUserId(String userId) {
        UserNickName userNickName = find(
                new BaseWrapper<UserNickName>()
                    .eq(UserNickName.USER_ID, userId)
                    .eq(UserNickName.SYSTEM_STATUS, true)
                    .orderDesc(Arrays.asList(UserNickName.SYSTEM_CREATE_TIME))
        );
        return userNickName;
    }

    @Override
    public void deleteByUserId(String userId, String systemUpdateUserId) {
        List<UserNickName> userNickNameList = list(
                new BaseWrapper<UserNickName>()
                        .eq(UserNickName.USER_ID, userId)
                        .eq(UserNickName.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserNickName.SYSTEM_CREATE_TIME))
        );
        if (userNickNameList != null && userNickNameList.size() > 0) {
            for (UserNickName userNickName : userNickNameList) {
                delete(userNickName.getUserNickNameId(), systemUpdateUserId, userNickName.getSystemVersion());

            }
        }
    }

}