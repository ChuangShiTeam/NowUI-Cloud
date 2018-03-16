package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员收货地址服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberDeliveryAddressRpc")
@FeignClient(name = "module-member")
public interface MemberDeliveryAddressRpc {

}
