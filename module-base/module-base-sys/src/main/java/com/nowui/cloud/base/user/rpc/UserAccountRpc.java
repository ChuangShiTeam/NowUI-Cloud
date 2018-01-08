package com.nowui.cloud.base.user.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户账号服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "userAccountRpc")
@FeignClient(name = "module-base")
public interface UserAccountRpc {

}