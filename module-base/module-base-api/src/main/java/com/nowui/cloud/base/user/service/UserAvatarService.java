package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserAvatar;

import java.util.List;

/**
 * 用户头像业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserAvatarService extends BaseService<UserAvatar> {

    /**
     * 用户头像统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAvatar 用户头像
     * @return Integer 用户头像统计
     */
    Integer countForAdmin(String appId, String userId, String userAvatar);

    /**
     * 用户头像列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userAvatar 用户头像
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserAvatar> 用户头像列表
     */
    List<UserAvatar> listForAdmin(String appId, String userId, String userAvatar, Integer pageIndex, Integer pageSize);
}
