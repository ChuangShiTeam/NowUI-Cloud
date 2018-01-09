package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserNickName;

import java.util.List;

/**
 * 用户昵称业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserNickNameService extends BaseService<UserNickName> {

    /**
     * 用户昵称统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userNickName 用户昵称
     * @return Integer 用户昵称统计
     */
    Integer countForAdmin(String appId, String userId, String userNickName);

    /**
     * 用户昵称列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userNickName 用户昵称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserNickName> 用户昵称列表
     */
    List<UserNickName> listForAdmin(String appId, String userId, String userNickName, Integer pageIndex, Integer pageSize);
}
