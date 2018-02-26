package com.nowui.cloud.member.member.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.view.MemberView;

/**
 * 会员服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberRpc")
@FeignClient(name = "module-member")
public interface MemberRpc {
    
    /**
     * 微信登录
     * 
     * @param userId 用户编号
     * @param userPassword 用户密码
     * @return true 正确   false 不正确 
     */
    @RequestMapping(value = "/member/system/v1/wechat/login", method = RequestMethod.POST)
    String wechatLoginV1(
            @RequestParam(value = "appId", required = true) String appId,
            UserWechat userWechat,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
    /**
     * 根据用户编号查询会员信息
     * 
     * @param userId 用户编号
     * @return Member 会员信息
     */
    @RequestMapping(value = "/member/system/v1/find/by/user/id", method = RequestMethod.POST)
    MemberView findByUserIdV1(@RequestParam(value = "userId", required = true) String userId);
    
    /**
     * 根据用户编号列表查询会员列表
     * 
     * @param userIds 用户编号集合JSON数组字符串
     * @return List<Member> 会员列表
     */
    @RequestMapping(value = "/member/system/v1/list/by/user/ids", method = RequestMethod.POST)
    List<MemberView> listByUserIdsV1(@RequestParam(value = "userIds", required = true) String userIds);
    
    /**
     * 会员个人信息更新
     * 
     * @param userId 用户编号
     * @param userAvatar 用户头像编号
     * @param userAvatarPath 用户头像路径
     * @param userNickName 用户昵称
     * @param userSex 用户性别
     * @param memberSignature 会员签名
     * @param memberAddressCity 会员所在城市
     * @param systemRequestUserId 请求用户编号
     * @return
     */
    @RequestMapping(value = "/member/system/v1/update", method = RequestMethod.POST)
    Boolean update(
            @RequestParam(value = "userId", required = true) String userId, 
            @RequestParam(value = "userAvatar", required = true) String userAvatar, 
            @RequestParam(value = "userAvatarPath", required = true) String userAvatarPath, 
            @RequestParam(value = "userNickName", required = true) String userNickName, 
            @RequestParam(value = "userSex", required = true) String userSex, 
            @RequestParam(value = "memberSignature", required = true) String memberSignature, 
            @RequestParam(value = "memberAddressCity", required = true) String memberAddressCity, 
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );
    
}