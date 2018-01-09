package com.nowui.cloud.base.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员关注服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "memberFollowRpc")
@FeignClient(name = "module-base")
public interface MemberFollowRpc {

}