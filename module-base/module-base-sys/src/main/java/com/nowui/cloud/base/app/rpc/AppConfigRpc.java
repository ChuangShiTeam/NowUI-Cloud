package com.nowui.cloud.base.app.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 应用配置服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "appConfigRpc")
@FeignClient(name = "module-base")
public interface AppConfigRpc {

}
