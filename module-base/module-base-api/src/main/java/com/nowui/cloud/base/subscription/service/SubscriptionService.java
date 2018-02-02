package com.nowui.cloud.base.subscription.service;
import java.util.List;

import com.nowui.cloud.base.subscription.entity.Subscription;
import com.nowui.cloud.service.BaseService;

/**
 * 订阅业务接口
 *
 * @author shawn
 *
 * 2018-01-28
 */
public interface SubscriptionService extends BaseService<Subscription> {

    /**
     * 订阅统计
     * @param appId 应用编号
     * @param subscriptionTarget 目标编号
     * @param subscriptionTargetType 目标类型
     * @param subscriptionAction 订阅动作（订阅动作：comment/like/post/update）
     * @param subscriptionUser 订阅人
     * @param systemCreateUserId 创建用户
     * @return Integer 订阅表统计
     */
    Integer countForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId);

    /**
     * 订阅列表
     * @param appId 应用编号
     * @param subscriptionTarget 目标编号
     * @param subscriptionTargetType 目标的类型
     * @param subscriptionAction 订阅动作（订阅动作：comment/like/post/update）
     * @param subscriptionUser 订阅人
     * @param systemCreateUserId 创建用户
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Subscription> 订阅列表
     */
    List<Subscription> listForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId, Integer pageIndex, Integer pageSize);


    // 获取用户的订阅记录
    List<Subscription> getSubscriptionByUserId(String appId,String userId);
}
