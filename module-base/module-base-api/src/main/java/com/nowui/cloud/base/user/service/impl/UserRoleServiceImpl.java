package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.mapper.UserRoleMapper;
import com.nowui.cloud.base.user.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户角色业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Integer adminCount(String appId, String userId, String roleId, String userType) {
        Integer count = count(
                new BaseWrapper<UserRole>()
                        .eq(UserRole.APP_ID, appId)
                        .likeAllowEmpty(UserRole.USER_ID, userId)
                        .likeAllowEmpty(UserRole.ROLE_ID, roleId)
                        .likeAllowEmpty(UserRole.USER_TYPE, userType)
                        .eq(UserRole.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserRole> adminList(String appId, String userId, String roleId, String userType, Integer pageIndex, Integer pageSize) {
        List<UserRole> userRoleList = list(
                new BaseWrapper<UserRole>()
                        .eq(UserRole.APP_ID, appId)
                        .likeAllowEmpty(UserRole.USER_ID, userId)
                        .likeAllowEmpty(UserRole.ROLE_ID, roleId)
                        .likeAllowEmpty(UserRole.USER_TYPE, userType)
                        .eq(UserRole.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserRole.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userRoleList;
    }

}