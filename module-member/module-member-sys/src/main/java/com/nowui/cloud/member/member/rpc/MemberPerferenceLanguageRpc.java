package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员偏好语言服务调用
 *
 * @author marcus
 *
 * 2018-01-29
 */
@Component(value = "memberPerferenceLanguageRpc")
@FeignClient(name = "module-member")
public interface MemberPerferenceLanguageRpc {

}