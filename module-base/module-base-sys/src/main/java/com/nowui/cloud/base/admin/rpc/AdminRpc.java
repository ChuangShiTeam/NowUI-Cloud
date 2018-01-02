package com.nowui.cloud.base.admin.rpc;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.rpc.fallback.AdminRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理员服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "AdminRpc")
@FeignClient(name = "module-base", fallback = AdminRpcFallback.class)
public interface AdminRpc {

}