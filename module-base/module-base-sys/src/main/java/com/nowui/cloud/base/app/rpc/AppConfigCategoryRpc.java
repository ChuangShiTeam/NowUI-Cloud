package com.nowui.cloud.base.app.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 应用配置分类服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "appConfigCategoryRpc")
@FeignClient(name = "module-base")
public interface AppConfigCategoryRpc {

}
