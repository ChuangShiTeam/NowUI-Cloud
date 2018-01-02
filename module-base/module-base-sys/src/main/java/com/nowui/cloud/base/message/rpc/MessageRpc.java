package com.nowui.cloud.base.message.rpc;

import com.nowui.cloud.base.message.entity.Message;
import com.nowui.cloud.base.message.rpc.fallback.MessageRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消息服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "MessageRpc")
@FeignClient(name = "module-base", fallback = MessageRpcFallback.class)
public interface MessageRpc {

}