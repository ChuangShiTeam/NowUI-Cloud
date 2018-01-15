package com.nowui.cloud.member.member.rpc;

import java.util.List;

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
@Component(value = "memberFollowRpc")
@FeignClient(name = "module-member")
public interface MemberFollowRpc {
    
    /**
     * 判断是否关注会员
     * 
     * @param userId 用户编号
     * @param followUserId 关注用户编号
     * @return
     */
    @RequestMapping(value = "/member/follow/system/v1/check/is/follow", method = RequestMethod.POST)
    Boolean checkIsFollowV1(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "followUserId", required = true) String followUserId
    );
    
    /**
     * 统计会员关注数
     * 
     * @param userId 用户编号
     * @return Integer 会员关注数
     */
    @RequestMapping(value = "/member/follow/system/v1/count/follow", method = RequestMethod.POST)
    Integer countFollow(String userId);
    
    /**
     * 统计会员被关注数
     * 
     * @param userId 用户编号
     * @return Integer 会员被关注数
     */
    @RequestMapping(value = "/member/follow/system/v1/count/be/followed", method = RequestMethod.POST)
    Integer countBeFollowed(String userId);
    
    /**
     * 会员关注会员编号列表
     * 
     * @param userId
     * @return
     */
    @RequestMapping(value = "/member/follow/system/v1/follow/user/id/list", method = RequestMethod.POST)
    List<String> followUserIdList(String userId);

}