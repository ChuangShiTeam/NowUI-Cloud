package com.nowui.cloud.member.member.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.member.member.rpc.MemberFollowRpc;
import com.nowui.cloud.member.member.service.MemberService;

import io.swagger.annotations.Api;

/**
 * 会员关注系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员关注", description = "会员关注系统端接口管理")
@RestController
public class MemberFollowSystemController implements MemberFollowRpc {
    
    @Autowired
    private MemberService memberService;

    @Override
    public Boolean checkIsFollowV1(String userId, String followUserId) {
        // TODO Auto-generated method stub
        return null;
    }

}   