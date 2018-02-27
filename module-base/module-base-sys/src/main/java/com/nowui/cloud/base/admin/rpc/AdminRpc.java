package com.nowui.cloud.base.admin.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 管理员服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "adminRpc")
@FeignClient(name = "module-base")
public interface AdminRpc {

}