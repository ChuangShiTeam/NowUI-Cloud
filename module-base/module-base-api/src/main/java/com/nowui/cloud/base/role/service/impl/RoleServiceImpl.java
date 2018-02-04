package com.nowui.cloud.base.role.service.impl;

import com.nowui.cloud.base.role.repository.RoleRepository;
import com.nowui.cloud.base.role.view.RoleView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;
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
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role,RoleRepository,RoleView> implements RoleService {

    @Override
    public Integer countForAdmin(String appId, String roleName, String roleCode) {
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
    public List<Role> listForAdmin(String appId, String roleName, String roleCode, Integer pageIndex, Integer pageSize) {
        List<Role> roleList = list(
                new BaseWrapper<Role>()
                        .eq(Role.APP_ID, appId)
                        .likeAllowEmpty(Role.ROLE_NAME, roleName)
                        .likeAllowEmpty(Role.ROLE_CODE, roleCode)
                        .eq(Role.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Role.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return roleList;
    }

}