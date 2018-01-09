package com.nowui.cloud.base.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员举报服务调用
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Component(value = "memberInformRpc")
@FeignClient(name = "module-base")
public interface MemberInformRpc {

}