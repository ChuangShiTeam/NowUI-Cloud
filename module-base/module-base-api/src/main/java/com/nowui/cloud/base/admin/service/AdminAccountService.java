package com.nowui.cloud.base.admin.service;

import java.util.List;

import com.nowui.cloud.base.admin.entity.AdminAccount;
import com.nowui.cloud.base.admin.view.AdminAccountView;
import com.nowui.cloud.service.BaseService;

/**
 * 管理员账号业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface AdminAccountService extends BaseService<AdminAccount, AdminAccountView> {
    
    /**
     * 管理员账号统计
     *
     * @param appId 应用编号
     * @return Integer 管理员账号统计
     */
    Integer countForAdmin(String appId);

    /**
     * 管理员账号列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<AdminAccountView> 管理员账号视图列表
     */
    List<AdminAccountView> listForAdmin(String appId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据管理员编号删除管理员账号
     * 
     * @param adminId 管理员编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByAdminId(String adminId, String systemRequestUserId);
    
    /**
     * 根据管理员编号查询管理员账号
     * 
     * @param adminId 管理员编号
     * @return AdminAccount 管理员账号
     */
    AdminAccount findByAdminId(String adminId);

}
