package com.nowui.cloud.base.user.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户消息服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "userMessageRpc")
@FeignClient(name = "module-base")
public interface UserMessageRpc {

}