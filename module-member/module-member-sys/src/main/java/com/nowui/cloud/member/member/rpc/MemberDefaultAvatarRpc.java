package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员默认头像服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberDefaultAvatarRpc")
@FeignClient(name = "module-member")
public interface MemberDefaultAvatarRpc {

}
