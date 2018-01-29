package com.nowui.cloud.base.subscriptioninfo.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.subscriptioninfo.entity.Subscription;
import com.nowui.cloud.base.subscriptioninfo.mapper.SubscriptionMapper;
import com.nowui.cloud.base.subscriptioninfo.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户订阅表业务实现
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Service
public class SubscriptionServiceImpl extends BaseServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService {

    @Override
    public Integer countForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId) {
        Integer count = count(
                new BaseWrapper<Subscription>()
                        .eq(Subscription.APP_ID, appId)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_TARGET, subscriptionTarget)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_TARGET_TYPE, subscriptionTargetType)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_ACTION, subscriptionAction)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_USER, subscriptionUser)
                        .likeAllowEmpty(Subscription.SYSTEM_CREATE_USER_ID, systemCreateUserId)
                        .eq(Subscription.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Subscription> listForAdmin(String appId, String subscriptionTarget, String subscriptionTargetType, String subscriptionAction, String subscriptionUser, String systemCreateUserId, Integer pageIndex, Integer pageSize) {
        List<Subscription> subscriptionList = list(
                new BaseWrapper<Subscription>()
                        .eq(Subscription.APP_ID, appId)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_TARGET, subscriptionTarget)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_TARGET_TYPE, subscriptionTargetType)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_ACTION, subscriptionAction)
                        .likeAllowEmpty(Subscription.SUBSCRIPTION_USER, subscriptionUser)
                        .likeAllowEmpty(Subscription.SYSTEM_CREATE_USER_ID, systemCreateUserId)
                        .eq(Subscription.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Subscription.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return subscriptionList;
    }

    @Override
    public List<Subscription> getSubscriptionByUserId(String appId, String userId) {

        List<Subscription> subscriptions = list(
                new BaseWrapper<Subscription>()
                        .eq(Subscription.APP_ID, appId)
                        .eq(Subscription.SUBSCRIPTION_USER, userId)
                        .eq(Subscription.SYSTEM_STATUS, true)
        );

        return subscriptions;
    }
}