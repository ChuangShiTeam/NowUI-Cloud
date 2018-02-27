package com.nowui.cloud.wawi.pet.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 宠物服务调用
 *
 * @author hucy
 *
 * 2018-01-21
 */
@Component(value = "petRpc")
@FeignClient(name = "module-wawi")
public interface PetRpc {

}