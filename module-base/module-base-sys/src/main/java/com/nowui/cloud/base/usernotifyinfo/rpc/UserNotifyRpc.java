package com.nowui.cloud.base.usernotifyinfo.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户消息队列表服务调用
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Component(value = "userNotifyRpc")
@FeignClient(name = "module-base")
public interface UserNotifyRpc {

}