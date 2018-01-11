package com.nowui.cloud.member.member.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.base.user.entity.UserWechat;

/**
 * 会员服务调用
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Component(value = "memberRpc")
@FeignClient(name = "module-base")
public interface MemberRpc {
    
    /**
     * 微信登录
     * 
     * @param userId 用户编号
     * @param userPassword 用户密码
     * @return true 正确   false 不正确 
     */
    @RequestMapping(value = "/user/system/v1/check/user/password", method = RequestMethod.POST)
    String wechatLogin(
            @RequestParam(value = "appId", required = true) String appId,
            UserWechat userWechat,
            @RequestParam(value = "systemRequestUserId", required = true) String systemRequestUserId
    );

}