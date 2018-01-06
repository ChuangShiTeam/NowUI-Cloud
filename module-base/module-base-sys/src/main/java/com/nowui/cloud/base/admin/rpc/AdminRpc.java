package com.nowui.cloud.base.admin.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 管理员服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "AdminRpc")
@FeignClient(name = "module-base")
public interface AdminRpc {

}