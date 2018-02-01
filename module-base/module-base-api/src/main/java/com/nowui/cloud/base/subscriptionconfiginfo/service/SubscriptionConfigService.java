package com.nowui.cloud.base.subscriptionconfiginfo.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.subscriptionconfiginfo.entity.SubscriptionConfig;

import java.util.List;

/**
 * 订阅配置业务接口
 *
 * @author shawn
 *
 * 2018-01-28
 */
public interface SubscriptionConfigService extends BaseService<SubscriptionConfig> {

    /**
     * 订阅配置统计
     *
     * @param appId 应用编号
     * @param subscriptionAction 用户的设置
     * @param subscriptionUserId 订阅用户Id
     * @return Integer 订阅配置统计
     */
    Integer countForAdmin(String appId, String subscriptionAction, String subscriptionUserId);

    /**
     * 订阅配置列表
     *
     * @param appId 应用编号
     * @param subscriptionAction 用户的设置
     * @param subscriptionUserId 订阅用户Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<SubscriptionConfig> 订阅配置列表
     */
    List<SubscriptionConfig> listForAdmin(String appId, String subscriptionAction, String subscriptionUserId, Integer pageIndex, Integer pageSize);
}
