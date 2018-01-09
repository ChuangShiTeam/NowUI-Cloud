package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserMobile;

import java.util.List;

/**
 * 用户手机号码业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserMobileService extends BaseService<UserMobile> {

    /**
     * 用户手机号码统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userMobile 手机号码
     * @return Integer 用户手机号码统计
     */
    Integer countForAdmin(String appId, String userId, String userMobile);

    /**
     * 用户手机号码列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userMobile 手机号码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserMobile> 用户手机号码列表
     */
    List<UserMobile> listForAdmin(String appId, String userId, String userMobile, Integer pageIndex, Integer pageSize);
}
