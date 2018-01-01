package com.nowui.cloud.base.role.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.mapper.RoleMapper;
import com.nowui.cloud.base.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 角色业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Integer adminCount(String appId, String roleName, String roleCode) {
        Integer count = count(
                new BaseWrapper<Role>()
                        .eq(Role.APP_ID, appId)
                        .likeAllowEmpty(Role.ROLE_NAME, roleName)
                        .likeAllowEmpty(Role.ROLE_CODE, roleCode)
                        .eq(Role.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Role> adminList(String appId, String roleName, String roleCode, Integer m, Integer n) {
        List<Role> roleList = list(
                new BaseWrapper<Role>()
                        .eq(Role.APP_ID, appId)
                        .likeAllowEmpty(Role.ROLE_NAME, roleName)
                        .likeAllowEmpty(Role.ROLE_CODE, roleCode)
                        .eq(Role.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Role.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return roleList;
    }

}