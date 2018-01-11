package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.mapper.MemberMapper;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 会员业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements MemberService {
    
    @Autowired
    private UserRpc userRpc;

    @Override
    public Integer countForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed) {
        Integer count = count(
                new BaseWrapper<Member>()
                        .eq(Member.APP_ID, appId)
                        .eqAllowEmpty(Member.MEMBER_IS_TOP, memberIsTop)
                        .eqAllowEmpty(Member.MEMBER_IS_RECOMMED, memberIsRecommed)
                        .eq(Member.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Member> listForAdmin(String appId, Boolean memberIsTop, Boolean memberIsRecommed, Integer pageIndex, Integer pageSize) {
        List<Member> memberList = list(
                new BaseWrapper<Member>()
                        .eq(Member.APP_ID, appId)
                        .eqAllowEmpty(Member.MEMBER_IS_TOP, memberIsTop)
                        .eqAllowEmpty(Member.MEMBER_IS_RECOMMED, memberIsRecommed)
                        .eq(Member.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Member.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberList;
    }

    @Override
    public Member wechatSaveOrUpdate(String appId, UserWechat userWechat, String systemRequestUserId) {
        
        String memberId = "";
        
        User user = userRpc.fingByUserWechat(appId, UserType.MEMBER.getKey(), userWechat.getWechatOpenId(), userWechat.getWechatUnionId());
                
        if (user == null) {
            memberId = Util.getRandomUUID();
            String userId = Util.getRandomUUID();
            
            Member member = new Member();
            member.setAppId(appId);
            member.setMemberId(memberId);
            member.setUserId(userId);
            member.setMemberIsRecommed(false);
            member.setMemberIsTop(false);
            member.setMemberTopEndTime(new Date());
            member.setMemberTopLevel(0);
            member.setMemberDescription("");

            Boolean isSave = save(member, memberId, systemRequestUserId);

            if (!isSave) {
                throw new RuntimeException("保存不成功");
            }
            
            isSave = userRpc.saveUserWechat(appId, userId, memberId, UserType.MEMBER.getKey(), userWechat, systemRequestUserId);
            
            if (!isSave) {
                throw new RuntimeException("保存不成功");
            }
        } else {
            Boolean isUpdate = userRpc.updateUserWechat(user.getUserId(), userWechat, systemRequestUserId);
            
            if (!isUpdate) {
                throw new RuntimeException("更新不成功");
            }

            memberId = user.getObjectId();
        }

        return find(memberId);
    }

}