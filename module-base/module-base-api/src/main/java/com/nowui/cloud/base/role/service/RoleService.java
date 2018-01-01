package com.nowui.cloud.base.role.service;
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
public interface RoleService extends BaseService<Role> {

    /**
     * 角色统计
     *
     * @param appId 应用编号
     * @param roleName 名称
     * @param roleCode 编码
     * @return Integer 角色统计
     */
    Integer adminCount(String appId, String roleName, String roleCode);

    /**
     * 角色列表
     *
     * @param appId 应用编号
     * @param roleName 名称
     * @param roleCode 编码
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Role> 角色列表
     */
    List<Role> adminList(String appId, String roleName, String roleCode, Integer m, Integer n);
}
