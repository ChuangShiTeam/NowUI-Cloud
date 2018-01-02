package com.nowui.cloud.base.admin.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.admin.entity.Admin;

import java.util.List;

/**
 * 管理员业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface AdminService extends BaseService<Admin> {

    /**
     * 管理员统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @return Integer 管理员统计
     */
    Integer adminCount(String appId, String userId);

    /**
     * 管理员列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Admin> 管理员列表
     */
    List<Admin> adminList(String appId, String userId, Integer m, Integer n);
}
