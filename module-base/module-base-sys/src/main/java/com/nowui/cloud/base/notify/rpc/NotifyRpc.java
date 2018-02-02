package com.nowui.cloud.base.notify.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 消息服务调用
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component(value = "notifyRpc")
@FeignClient(name = "module-base")
public interface NotifyRpc {

}