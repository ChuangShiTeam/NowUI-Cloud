package com.nowui.cloud.base.user.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 用户邮箱服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "userEmailRpc")
@FeignClient(name = "module-base")
public interface UserEmailRpc {

}