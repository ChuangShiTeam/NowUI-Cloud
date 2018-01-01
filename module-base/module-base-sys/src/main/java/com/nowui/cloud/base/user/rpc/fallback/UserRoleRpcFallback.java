package com.nowui.cloud.base.user.rpc.fallback;

import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.rpc.UserRoleRpc;
import org.springframework.stereotype.Component;

/**
 * 用户角色服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "UserRoleRpcFallback")
public class UserRoleRpcFallback implements UserRoleRpc {

}
