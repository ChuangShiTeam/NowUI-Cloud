package com.nowui.cloud.base.member.rpc;

import com.nowui.cloud.base.member.entity.MemberBookmark;
import com.nowui.cloud.base.member.rpc.fallback.MemberBookmarkRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 会员收藏服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "MemberBookmarkRpc")
@FeignClient(name = "module-base", fallback = MemberBookmarkRpcFallback.class)
public interface MemberBookmarkRpc {

}