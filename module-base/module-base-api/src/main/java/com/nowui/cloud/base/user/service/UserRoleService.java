package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserRole;

import java.util.List;

/**
 * 用户角色业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface UserRoleService extends BaseService<UserRole> {

    /**
     * 用户角色统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param roleId 角色编号
     * @param userType 用户类型
     * @return Integer 用户角色统计
     */
    Integer countForAdmin(String appId, String userId, String roleId, String userType);

    /**
     * 用户角色列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param roleId 角色编号
     * @param userType 用户类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserRole> 用户角色列表
     */
    List<UserRole> listForAdmin(String appId, String userId, String roleId, String userType, Integer pageIndex, Integer pageSize);
}
