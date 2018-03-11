package com.nowui.cloud.cms.toolbar.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 工具栏服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "toolbarRpc")
@FeignClient(name = "module-cms")
public interface ToolbarRpc {
    
}
