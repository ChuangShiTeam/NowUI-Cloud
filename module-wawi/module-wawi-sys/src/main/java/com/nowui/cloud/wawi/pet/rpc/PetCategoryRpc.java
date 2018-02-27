package com.nowui.cloud.wawi.pet.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 宠物分类服务调用
 *
 * @author marcus
 *
 * 2018-01-24
 */
@Component(value = "petCategoryRpc")
@FeignClient(name = "module-wawi")
public interface PetCategoryRpc {

}