package com.nowui.cloud.base.message.rpc.fallback;

import com.nowui.cloud.base.message.entity.Message;
import com.nowui.cloud.base.message.rpc.MessageRpc;
import org.springframework.stereotype.Component;

/**
 * 消息服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "MessageRpcFallback")
public class MessageRpcFallback implements MessageRpc {

}
