package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.mapper.MemberMapper;
import com.nowui.cloud.member.member.service.MemberService;
import com.nowui.cloud.member.member.service.MemberSignatureService;
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
    private MemberSignatureService memberSignatureService;
    
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
    public Member findByUserId(String userId) {
        Member member = find(
                new BaseWrapper<Member>()
                .eq(Member.USER_ID, userId)
                .eq(Member.SYSTEM_STATUS, true)
        );
        
        member.put(Member.MEMBER_SIGNATURE, memberSignatureService.findByMemberId(member.getMemberId()));
        
        return member;
    }

    @Override
    public Member findWithCacheUserByUserId(String userId) {
        Member member = findByUserId(userId);
        
        if (member == null) {
            return null;
        }
        
        String userTableName = Util.getTableName(User.class);
        
        if (!Util.isNullOrEmpty(userTableName)) {
            
            User user = (User) redis.opsForValue().get(getItemCacheName(userTableName, userId));
            
            member.put(Member.MEMBER_USER, user);
        }
        
        return member;
    }

}