package com.nowui.cloud.base.subscription.service.impl;

import java.util.Arrays;
import java.util.List;

import com.nowui.cloud.base.subscription.repository.SubscriptionConfigRepository;
import com.nowui.cloud.base.subscription.view.SubscriptionConfigView;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.subscription.entity.SubscriptionConfig;
import com.nowui.cloud.base.subscription.mapper.SubscriptionConfigMapper;
import com.nowui.cloud.base.subscription.service.SubscriptionConfigService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.SuperServiceImpl;

/**
 * 订阅配置业务实现
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Service
public class SubscriptionConfigServiceImpl extends SuperServiceImpl<SubscriptionConfigMapper, SubscriptionConfig,SubscriptionConfigRepository,SubscriptionConfigView> implements SubscriptionConfigService {

    @Override
    public Integer countForAdmin(String appId, String subscriptionAction, String subscriptionUserId) {
        Integer count = count(
                new BaseWrapper<SubscriptionConfig>()
                        .eq(SubscriptionConfig.APP_ID, appId)
                        .likeAllowEmpty(SubscriptionConfig.SUBSCRIPTION_ACTION, subscriptionAction)
                        .likeAllowEmpty(SubscriptionConfig.SUBSCRIPTION_USER_ID, subscriptionUserId)
                        .eq(SubscriptionConfig.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<SubscriptionConfig> listForAdmin(String appId, String subscriptionAction, String subscriptionUserId, Integer pageIndex, Integer pageSize) {
        List<SubscriptionConfig> subscriptionConfigList = list(
                new BaseWrapper<SubscriptionConfig>()
                        .eq(SubscriptionConfig.APP_ID, appId)
                        .likeAllowEmpty(SubscriptionConfig.SUBSCRIPTION_ACTION, subscriptionAction)
                        .likeAllowEmpty(SubscriptionConfig.SUBSCRIPTION_USER_ID, subscriptionUserId)
                        .eq(SubscriptionConfig.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(SubscriptionConfig.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return subscriptionConfigList;
    }

}