package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员对话服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberDialogueRpc")
@FeignClient(name = "module-member")
public interface MemberDialogueRpc {

}