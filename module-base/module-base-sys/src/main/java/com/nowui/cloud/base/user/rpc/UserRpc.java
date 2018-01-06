package com.nowui.cloud.base.user.rpc;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.fallback.UserRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "UserRpc")
@FeignClient(name = "module-base", fallback = UserRpcFallback.class)
public interface UserRpc {

}