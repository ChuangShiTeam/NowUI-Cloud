package com.nowui.cloud.base.subscriptioninfo.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户订阅表服务调用
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component(value = "subscriptionRpc")
@FeignClient(name = "module-base")
public interface SubscriptionRpc {

}