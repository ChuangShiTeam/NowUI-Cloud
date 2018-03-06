package com.nowui.cloud.cms.advertisement.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.advertisement.rpc.AdvertisementRpc;

import io.swagger.annotations.Api;

/**
 * 广告系统端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "广告", description = "广告系统端接口管理")
@RestController
public class AdvertisementSystemController implements AdvertisementRpc {
    
}
