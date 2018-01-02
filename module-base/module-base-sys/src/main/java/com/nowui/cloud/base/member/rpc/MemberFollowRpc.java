package com.nowui.cloud.base.member.rpc;

import com.nowui.cloud.base.member.entity.MemberFollow;
import com.nowui.cloud.base.member.rpc.fallback.MemberFollowRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会员关注服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "MemberFollowRpc")
@FeignClient(name = "module-base", fallback = MemberFollowRpcFallback.class)
public interface MemberFollowRpc {

}