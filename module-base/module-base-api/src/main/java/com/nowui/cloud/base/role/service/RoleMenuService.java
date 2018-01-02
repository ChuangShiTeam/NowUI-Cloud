package com.nowui.cloud.base.role.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.role.entity.RoleMenu;

import java.util.List;

/**
 * 角色菜单业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface RoleMenuService extends BaseService<RoleMenu> {

    /**
     * 角色菜单统计
     *
     * @param appId 应用编号
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @return Integer 角色菜单统计
     */
    Integer adminCount(String appId, String roleId, String menuId);

    /**
     * 角色菜单列表
     *
     * @param appId 应用编号
     * @param roleId 角色编号
     * @param menuId 菜单编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<RoleMenu> 角色菜单列表
     */
    List<RoleMenu> adminList(String appId, String roleId, String menuId, Integer m, Integer n);
}
