package com.nowui.cloud.base.admin.service;
import java.util.List;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.service.BaseService;

/**
 * 管理员业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface AdminService extends BaseService<Admin, AdminView> {

    /**
     * 管理员统计
     *
     * @param appId 应用编号
     * @return Integer 管理员统计
     */
    Integer countForAdmin(String appId);

    /**
     * 管理员列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Admin> 管理员列表
     */
    List<AdminView> listForAdmin(String appId, Integer pageIndex, Integer pageSize);

}
