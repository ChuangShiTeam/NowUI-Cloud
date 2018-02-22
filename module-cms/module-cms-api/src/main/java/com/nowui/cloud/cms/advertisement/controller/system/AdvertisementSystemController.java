package com.nowui.cloud.cms.advertisement.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.advertisement.rpc.AdvertisementRpc;
import com.nowui.cloud.cms.advertisement.service.AdvertisementService;
import com.nowui.cloud.util.Util;

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
    
    @Autowired
    private AdvertisementService advertisementService;

    @Override
    public List<Advertisement> listByCategoryCodeV1(String appId, String advertisementCategoryCode) {
       
        List<Advertisement> advertisementList = advertisementService.listByCategoryCode(appId, advertisementCategoryCode);
        
        if (Util.isNullOrEmpty(advertisementList)) {
            return new ArrayList<>();
        }
        
        String fileIds = Util.beanToFieldString(advertisementList, Advertisement.ADVERTISEMENT_IMAGE_ID);
        
        return advertisementList;
    }

}
