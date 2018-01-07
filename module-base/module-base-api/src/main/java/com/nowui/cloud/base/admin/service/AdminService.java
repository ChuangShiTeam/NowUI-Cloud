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
     * @param userAccount 用户账号
     * @param userNickName 用户昵称
     * @param userMobile 用户手机号码
     * @return Integer 管理员统计
     */
    Integer adminCount(String appId, String userAccount, String userNickName, String userMobile);

    /**
     * 管理员列表
     *
     * @param appId 应用编号
     * @param userAccount 用户账号
     * @param userNickName 用户昵称
     * @param userMobile 用户手机号码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Admin> 管理员列表
     */
    List<Admin> adminList(String appId, String userAccount, String userNickName, String userMobile, Integer pageIndex, Integer pageSize);

}
