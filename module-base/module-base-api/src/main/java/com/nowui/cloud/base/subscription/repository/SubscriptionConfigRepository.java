package com.nowui.cloud.base.subscription.repository;

import com.nowui.cloud.base.subscription.view.SubscriptionConfigView;
import com.nowui.cloud.repository.BaseRepository;
import org.springframework.stereotype.Component;

/**
 * 订阅配置表视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-04
 */
@Component
public interface SubscriptionConfigRepository extends BaseRepository<SubscriptionConfigView> {

}
