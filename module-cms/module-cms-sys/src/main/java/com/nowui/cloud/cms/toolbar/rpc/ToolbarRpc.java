package com.nowui.cloud.cms.toolbar.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;

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
    
    /**
     * 工具栏列表
     * 
     * @param appId 应用编号
     * @return List<Toolbar> 工具栏列表
     */
    @RequestMapping(value = "/toolbar/system/v1/list", method = RequestMethod.POST)
    List<Toolbar> list(@RequestParam(value = "appId", required = true) String appId);

}
