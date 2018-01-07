package com.nowui.cloud.cms.navigation.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 导航栏服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "NavigationRpc")
@FeignClient(name = "module-cms")
public interface NavigationRpc {

}