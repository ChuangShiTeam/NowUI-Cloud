package com.nowui.cloud.cms.advertisement.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

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
    
}
