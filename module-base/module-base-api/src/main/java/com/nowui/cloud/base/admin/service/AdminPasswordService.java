package com.nowui.cloud.base.admin.service;

import java.util.List;

import com.nowui.cloud.base.admin.entity.AdminPassword;
import com.nowui.cloud.base.admin.view.AdminPasswordView;
import com.nowui.cloud.service.BaseService;

/**
 * 管理员密码业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface AdminPasswordService extends BaseService<AdminPassword, AdminPasswordView> {
    
    /**
     * 管理员密码统计
     *
     * @param appId 应用编号
     * @return Integer 管理员密码统计
     */
    Integer countForAdmin(String appId);

    /**
     * 管理员密码列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<AdminPassword> 管理员密码列表
     */
    List<AdminPasswordView> listForAdmin(String appId, Integer pageIndex, Integer pageSize);

}
