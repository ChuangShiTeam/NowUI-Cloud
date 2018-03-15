package com.nowui.cloud.base.role.service;
import com.nowui.cloud.base.role.view.RoleView;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.role.entity.Role;

import java.util.List;

/**
 * 角色业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface RoleService extends BaseService<Role,RoleView> {

    /**
     * 角色统计
     *
     * @param appId 应用编号
     * @param roleName 名称
     * @param roleCode 编码
     * @return Integer 角色统计
     */
    Integer countForAdmin(String appId, String roleName, String roleCode);

    /**
     * 角色列表
     *
     * @param appId 应用编号
     * @param roleName 名称
     * @param roleCode 编码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Role> 角色列表
     */
    List<RoleView> listForAdmin(String appId, String roleName, String roleCode, Integer pageIndex, Integer pageSize);
}
