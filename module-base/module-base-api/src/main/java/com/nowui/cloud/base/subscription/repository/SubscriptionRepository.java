package com.nowui.cloud.base.subscription.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.subscription.view.SubscriptionView;
import org.springframework.stereotype.Component;

/**
 * 订阅视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface SubscriptionRepository extends BaseRepository<SubscriptionView> {

}
