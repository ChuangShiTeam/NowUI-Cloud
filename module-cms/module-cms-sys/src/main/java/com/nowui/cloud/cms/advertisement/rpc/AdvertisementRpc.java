package com.nowui.cloud.cms.advertisement.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.cms.advertisement.entity.Advertisement;

/**
 * 广告服务调用
 * 
 * @author marcus
 *
 * 2018年1月15日
 */
@Component(value = "advertisementRpc")
@FeignClient(name = "module-cms")
public interface AdvertisementRpc {
    
    /**
     * 根据广告分类编码查询广告列表信息
     * 
     * @param appId 应用编号编号
     * @return Member 会员信息
     */
    @RequestMapping(value = "/advertisment/system/v1/list/by/category/code", method = RequestMethod.POST)
    List<Advertisement> listByCategoryCodeV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "advertisementCategoryCode", required = true) String advertisementCategoryCode
    );
    
}
