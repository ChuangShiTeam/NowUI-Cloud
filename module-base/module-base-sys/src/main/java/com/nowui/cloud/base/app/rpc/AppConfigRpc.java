package com.nowui.cloud.base.app.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

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
    
    /**
     * 根据应用编号和应用配置分类编码查询应用配置信息
     *
     * @param appId 应用编号
     * @param appConfigCategoryCode 应用配置分类编码
     * @return 应用配置信息
     */
    @RequestMapping(value = "/app/config/system/v1/list", method = RequestMethod.GET)
    JSONObject findByCategoryCode(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "appConfigCategoryCode", required = true) String appConfigCategoryCode
    );

}
