package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.base.user.repository.UserRoleRepository;
import com.nowui.cloud.base.user.view.UserRoleView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.mapper.UserRoleMapper;
import com.nowui.cloud.base.user.service.UserRoleService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户角色业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole, UserRoleRepository, UserRoleView> implements UserRoleService {

}