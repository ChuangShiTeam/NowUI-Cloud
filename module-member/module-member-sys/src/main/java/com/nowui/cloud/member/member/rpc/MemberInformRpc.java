package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员举报服务调用
 *
 * @author marcus
 *
 * 2018-01-09
 */
@Component(value = "memberInformRpc")
@FeignClient(name = "module-member")
public interface MemberInformRpc {

}