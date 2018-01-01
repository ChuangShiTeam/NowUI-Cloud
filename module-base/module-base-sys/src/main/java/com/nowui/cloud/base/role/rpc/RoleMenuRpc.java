package com.nowui.cloud.base.role.rpc;

import com.nowui.cloud.base.role.entity.RoleMenu;
import com.nowui.cloud.base.role.rpc.fallback.RoleMenuRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 角色菜单服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "RoleMenuRpc")
@FeignClient(name = "module-base", fallback = RoleMenuRpcFallback.class)
public interface RoleMenuRpc {

}