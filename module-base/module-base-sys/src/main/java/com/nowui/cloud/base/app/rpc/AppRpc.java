package com.nowui.cloud.base.app.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.base.app.entity.App;

/**
 * 应用服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "appRpc")
@FeignClient(name = "module-base")
public interface AppRpc {
    
    /**
     * 应用查找
     *
     * @param appId 文件编号
     * @return app 文件
     */
    @RequestMapping(value = "/app/system/v1/find", method = RequestMethod.GET)
    App find(@RequestParam(value = "appId", required = true) String appId);

}
