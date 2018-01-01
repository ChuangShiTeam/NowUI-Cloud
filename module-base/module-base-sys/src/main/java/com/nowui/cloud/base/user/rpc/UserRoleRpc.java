package com.nowui.cloud.base.user.rpc;

import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.rpc.fallback.UserRoleRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户角色服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "UserRoleRpc")
@FeignClient(name = "module-base", fallback = UserRoleRpcFallback.class)
public interface UserRoleRpc {

}