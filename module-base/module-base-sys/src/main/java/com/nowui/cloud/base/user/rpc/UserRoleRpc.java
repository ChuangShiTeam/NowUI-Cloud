package com.nowui.cloud.base.user.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户角色服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "userRoleRpc")
@FeignClient(name = "module-base")
public interface UserRoleRpc {

}