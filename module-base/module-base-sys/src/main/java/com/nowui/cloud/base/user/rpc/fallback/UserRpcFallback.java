package com.nowui.cloud.base.user.rpc.fallback;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.UserRpc;
import org.springframework.stereotype.Component;

/**
 * 用户服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "UserRpcFallback")
public class UserRpcFallback implements UserRpc {

}
