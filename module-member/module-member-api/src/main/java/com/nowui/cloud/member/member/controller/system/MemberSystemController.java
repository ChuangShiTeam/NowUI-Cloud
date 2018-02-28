package com.nowui.cloud.member.member.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.MemberAddress;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.service.MemberAddressService;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 会员系统端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-08
 */
@Api(value = "会员", description = "会员系统端接口管理")
@RestController
public class MemberSystemController extends BaseController implements MemberRpc {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserRpc userRpc;

    @Autowired
    private MemberAddressService memberAddressService;
    
    @Autowired
    private MemberSignatureService memberSignatureService;
    
    @Autowired
    private MemberBackgroundService memberBackgroundService;

    @Override
    public String wechatLoginV1(String appId, UserWechat userWechat, String systemRequestUserId) {


        return null;
    }

    @Override
    public MemberView findByUserIdV1(String userId) {
        return memberService.findByUserId(userId);
    }

    @Override
    public List<MemberView> listByUserIdsV1(String userIds) {
        if (Util.isNullOrEmpty(userIds)) {
            return null;
        }
        List<String> userIdList = JSONArray.parseArray(userIds, String.class);

        return memberService.listByUserIds(userIdList);
    }

    @Override
    public Boolean update(
            String userId, String userAvatar, String userAvatarPath,
            String userNickName, String userSex, String memberSignature,
            String memberAddressCity, String systemRequestUserId) {
        MemberView memberView = memberService.findByUserId(userId);

        if (memberView == null) {
            return false;
        }
        
        if (!memberAddressCity.equals(memberView.getMemberAddressCity())) {
            MemberAddress memberAddress = new MemberAddress();
            memberAddress.setMemberAddressProvince(memberView.getMemberAddressProvince());
            memberAddress.setMemberAddressArea(memberView.getMemberAddressArea());
            memberAddress.setMemberAddressCity(memberAddressCity);
            memberAddress.setMemberAddressAddress(memberView.getMemberAddressAddress());
            
            memberService.updateMemberAddress(memberView.getAppId(), memberView.getMemberId(), memberAddress, systemRequestUserId);
        } 

        if (!memberSignature.equals(memberView.getMemberSignature())) {
            memberService.updateMemberSignature(memberView.getAppId(), memberView.getMemberId(), memberSignature, systemRequestUserId);
        }

        userRpc.updateUserNickNameV1(memberView.getAppId(), userId, userNickName, systemRequestUserId);
        userRpc.updateUserAvatarV1(memberView.getAppId(), userId, userAvatar, userAvatarPath, systemRequestUserId);
        userRpc.updateUserSexV1(memberView.getAppId(), userId, userSex, systemRequestUserId);

        return true;
    }

}