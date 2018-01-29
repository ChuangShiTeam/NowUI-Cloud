package com.nowui.cloud.base.subscriptioninfo.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.subscriptioninfo.entity.Subscription;

import java.util.List;

/**
 * 用户订阅表业务接口
 *
 * @author shawn
 *
 * 2018-01-28
 */
public interface SubscriptionService extends BaseService<Subscription> {

    /**
     * 用户订阅表统计
     * @param appId 应用编号
     * @param subscriptionTarget 目标的Id
     * @param subscriptionTargetType 目标的类型
     * @param subscriptionAction 订阅动作（订阅动作：comment/like/post/update）
     * @param subscriptionUser 订阅人
     * @param systemCreateUserId 创建用户
     * @return Integer 用户订阅表统计
     */
    Integer countForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId);

    /**
     * 用户订阅表列表
     * @param appId 应用编号
     * @param subscriptionTarget 目标的Id
     * @param subscriptionTargetType 目标的类型
     * @param subscriptionAction 订阅动作（订阅动作：comment/like/post/update）
     * @param subscriptionUser 订阅人
     * @param systemCreateUserId 创建用户
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Subscription> 用户订阅表列表
     */
    List<Subscription> listForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId, Integer pageIndex, Integer pageSize);


    // 获取用户的订阅记录
    List<Subscription> getSubscriptionByUserId(String appId,String userId);
}
