package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员默认头像服务调用
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Component(value = "memberDefaultAvatarRpc")
@FeignClient(name = "module-member")
public interface MemberDefaultAvatarRpc {

}