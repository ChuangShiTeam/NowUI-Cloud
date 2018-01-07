package com.nowui.cloud.cms.navigationBar.rpc;
import com.nowui.cloud.cms.navigationBar.entity.NavigationBar;
import com.nowui.cloud.cms.navigationBar.rpc.fallback.NavigationBarRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 导航栏服务调用
 *
 * @author shawn
 *
 * 2017-12-31
 */
@Component(value = "NavigationBarRpc")
@FeignClient(name = "module-cms", fallback = NavigationBarRpcFallback.class)
public interface NavigationBarRpc{
}