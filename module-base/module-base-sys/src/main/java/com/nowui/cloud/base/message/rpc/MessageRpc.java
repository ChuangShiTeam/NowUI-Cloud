package com.nowui.cloud.base.message.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 消息服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "messageRpc")
@FeignClient(name = "module-base")
public interface MessageRpc {

}