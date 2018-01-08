package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserAccount;

import java.util.List;

/**
 * 用户账号业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserAccountService extends BaseService<UserAccount> {

    /**
     * 用户账号统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAccount 用户账号
     * @return Integer 用户账号统计
     */
    Integer adminCount(String appId, String userId, String userAccount);

    /**
     * 用户账号列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAccount 用户账号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserAccount> 用户账号列表
     */
    List<UserAccount> adminList(String appId, String userId, String userAccount, Integer pageIndex, Integer pageSize);
}
