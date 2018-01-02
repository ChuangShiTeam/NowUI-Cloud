package com.nowui.cloud.base.role.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.role.entity.RoleMenu;
import com.nowui.cloud.base.role.mapper.RoleMenuMapper;
import com.nowui.cloud.base.role.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 角色菜单业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public Integer adminCount(String appId, String roleId, String menuId) {
        Integer count = count(
                new BaseWrapper<RoleMenu>()
                        .eq(RoleMenu.APP_ID, appId)
                        .likeAllowEmpty(RoleMenu.ROLE_ID, roleId)
                        .likeAllowEmpty(RoleMenu.MENU_ID, menuId)
                        .eq(RoleMenu.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<RoleMenu> adminList(String appId, String roleId, String menuId, Integer m, Integer n) {
        List<RoleMenu> roleMenuList = list(
                new BaseWrapper<RoleMenu>()
                        .eq(RoleMenu.APP_ID, appId)
                        .likeAllowEmpty(RoleMenu.ROLE_ID, roleId)
                        .likeAllowEmpty(RoleMenu.MENU_ID, menuId)
                        .eq(RoleMenu.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(RoleMenu.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return roleMenuList;
    }

}