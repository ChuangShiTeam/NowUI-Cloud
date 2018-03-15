package com.nowui.cloud.base.role.service.impl;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.role.entity.RoleMenu;
import com.nowui.cloud.base.role.mapper.RoleMenuMapper;
import com.nowui.cloud.base.role.repository.RoleMenuRepository;
import com.nowui.cloud.base.role.service.RoleMenuService;
import com.nowui.cloud.base.role.view.RoleMenuView;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 角色菜单业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu, RoleMenuRepository, RoleMenuView> implements RoleMenuService {

}