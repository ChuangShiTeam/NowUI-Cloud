package com.nowui.cloud.cms.advertisement.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 广告移动端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "广告", description = "广告移动端接口管理")
@RestController
public class AdvertisementController extends BaseController {
    
    @Autowired
    private AdvertisementService advertisementService;

}
