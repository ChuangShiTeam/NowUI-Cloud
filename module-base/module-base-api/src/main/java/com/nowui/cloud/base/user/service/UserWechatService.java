package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserWechat;

import java.util.List;

/**
 * 用户微信业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserWechatService extends BaseService<UserWechat> {

    /**
     * 用户微信统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param wechatNickName 微信昵称
     * @return Integer 用户微信统计
     */
    Integer adminCount(String appId, String userId, String wechatNickName);

    /**
     * 用户微信列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param wechatNickName 微信昵称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserWechat> 用户微信列表
     */
    List<UserWechat> adminList(String appId, String userId, String wechatNickName, Integer pageIndex, Integer pageSize);
}
