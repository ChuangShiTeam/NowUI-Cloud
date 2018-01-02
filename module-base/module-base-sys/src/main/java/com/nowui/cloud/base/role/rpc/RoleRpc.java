package com.nowui.cloud.base.role.rpc;

import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.rpc.fallback.RoleRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 角色服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "RoleRpc")
@FeignClient(name = "module-base", fallback = RoleRpcFallback.class)
public interface RoleRpc {

}