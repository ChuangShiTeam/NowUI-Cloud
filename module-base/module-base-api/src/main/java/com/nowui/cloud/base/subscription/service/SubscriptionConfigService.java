package com.nowui.cloud.base.subscription.service;
import java.util.List;

import com.nowui.cloud.base.subscription.entity.SubscriptionConfig;
import com.nowui.cloud.base.subscription.view.SubscriptionConfigView;
import com.nowui.cloud.service.BaseService;

/**
 * 订阅配置业务接口
 *
 * @author shawn
 *
 * 2018-01-28
 */
public interface SubscriptionConfigService extends BaseService<SubscriptionConfig,SubscriptionConfigView> {

    /**
     * 订阅配置统计
     *
     * @param appId 应用编号
     * @param subscriptionAction 用户的设置
     * @param subscriptionUserId 订阅用户编号
     * @return Integer 订阅配置统计
     */
    Integer countForAdmin(String appId, String subscriptionAction, String subscriptionUserId);

    /**
     * 订阅配置列表
     *
     * @param appId 应用编号
     * @param subscriptionAction 用户的设置
     * @param subscriptionUserId 订阅用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<SubscriptionConfig> 订阅配置列表
     */
    List<SubscriptionConfig> listForAdmin(String appId, String subscriptionAction, String subscriptionUserId, Integer pageIndex, Integer pageSize);
}
