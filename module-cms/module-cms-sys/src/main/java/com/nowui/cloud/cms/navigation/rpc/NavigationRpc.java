package com.nowui.cloud.cms.navigation.rpc;

import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.rpc.fallback.NavigationRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 导航栏服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "NavigationRpc")
@FeignClient(name = "module-cms", fallback = NavigationRpcFallback.class)
public interface NavigationRpc {

}