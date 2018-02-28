package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 	会员签名服务调用
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Component(value = "memberSignatureRpc")
@FeignClient(name = "module-member")
public interface MemberSignatureRpc {

}