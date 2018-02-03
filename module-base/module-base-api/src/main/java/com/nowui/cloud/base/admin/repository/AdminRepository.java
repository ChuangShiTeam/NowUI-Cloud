package com.nowui.cloud.base.admin.repository;

import com.nowui.cloud.repository.BaseRepository;

import java.util.List;

import com.nowui.cloud.base.admin.view.AdminView;

/**
 * 管理员视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-03
 */
public interface AdminRepository extends BaseRepository<AdminView> {

    /**
     * 管理员统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @return Integer 管理员统计
     */
    Integer countForAdmin(String appId, String userId);

    /**
     * 管理员列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Admin> 管理员列表
     */
    List<AdminView> listForAdmin(String appId, String userId, Integer pageIndex, Integer pageSize);

}
