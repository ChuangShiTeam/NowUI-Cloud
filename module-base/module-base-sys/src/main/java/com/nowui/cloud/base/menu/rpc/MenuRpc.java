package com.nowui.cloud.base.menu.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 菜单服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "MenuRpc")
@FeignClient(name = "module-base")
public interface MenuRpc {

}