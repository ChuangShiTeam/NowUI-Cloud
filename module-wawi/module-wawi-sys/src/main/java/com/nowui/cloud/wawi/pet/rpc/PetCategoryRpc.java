package com.nowui.cloud.wawi.pet.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 宠物分类服务调用
 *
 * @author marcus
 *
 * 2018-01-16
 */
@Component(value = "petCategoryRpc")
@FeignClient(name = "module-wawi")
public interface PetCategoryRpc {

}