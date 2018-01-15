package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员背景服务调用
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Component(value = "memberBackgroudRpc")
@FeignClient(name = "module-member")
public interface MemberBackgroundRpc {

}