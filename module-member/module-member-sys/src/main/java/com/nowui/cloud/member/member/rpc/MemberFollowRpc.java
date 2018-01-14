package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    /**
     * 
     * 
     * @param userId
     * @param followUserId
     * @return
     */
    Boolean checkIsFollow(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "followUserId", required = true) String followUserId
    );

}