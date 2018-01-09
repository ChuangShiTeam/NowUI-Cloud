package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserEmail;

import java.util.List;

/**
 * 用户邮箱业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserEmailService extends BaseService<UserEmail> {

    /**
     * 用户邮箱统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userEmail 用户邮箱
     * @return Integer 用户邮箱统计
     */
    Integer countForAdmin(String appId, String userId, String userEmail);

    /**
     * 用户邮箱列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userEmail 用户邮箱
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserEmail> 用户邮箱列表
     */
    List<UserEmail> listForAdmin(String appId, String userId, String userEmail, Integer pageIndex, Integer pageSize);
}
