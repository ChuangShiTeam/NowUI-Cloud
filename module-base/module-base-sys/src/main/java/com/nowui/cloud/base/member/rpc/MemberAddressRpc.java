package com.nowui.cloud.base.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员地址服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberAddressRpc")
@FeignClient(name = "module-base")
public interface MemberAddressRpc {

}