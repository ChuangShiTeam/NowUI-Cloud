package com.nowui.cloud.base.role.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 角色服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "RoleRpc")
@FeignClient(name = "module-base")
public interface RoleRpc {

}