package com.nowui.cloud.base.menu.rpc;

import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.rpc.fallback.MenuRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 菜单服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "MenuRpc")
@FeignClient(name = "module-base", fallback = MenuRpcFallback.class)
public interface MenuRpc {

}