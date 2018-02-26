package com.nowui.cloud.member.member.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.member.member.rpc.MemberFollowRpc;
import com.nowui.cloud.member.member.service.MemberFollowService;
import com.nowui.cloud.member.member.view.MemberFollowView;

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
    private MemberFollowService memberFollowService;

    @Override
    public Boolean checkIsFollowV1(String userId, String followUserId) {
        return memberFollowService.checkIsFollow(userId, followUserId);
    }

    @Override
    public Integer countFollow(String userId) {
        return memberFollowService.countFollow(userId);
    }

    @Override
    public Integer countBeFollowed(String userId) {
        return memberFollowService.countBeFollowed(userId);
    }

    @Override
    public List<String> followUserIdList(String userId) {
        
        List<MemberFollowView> memberFollowViewList = memberFollowService.listByUserId(userId);
        
        if (memberFollowViewList == null || memberFollowViewList.size() == 0) {
            return new ArrayList<>();
        }
        
        return memberFollowViewList.stream().map(memberFollowView -> memberFollowView.getFollowUserId()).collect(Collectors.toList());
    }

}   