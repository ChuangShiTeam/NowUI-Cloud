package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.mapper.MemberBackgroundMapper;
import com.nowui.cloud.member.member.service.MemberBackgroundService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员背景业务实现
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Service
public class MemberBackgroundServiceImpl extends BaseServiceImpl<MemberBackgroundMapper, MemberBackground> implements MemberBackgroundService {

    @Override
    public MemberBackground findByMemberId(String memberId) {
        
        MemberBackground memberBackground = find(
                new BaseWrapper<MemberBackground>()
                        .eq(MemberBackground.MEMBER_ID, memberId)
                        .eq(MemberBackground.SYSTEM_STATUS, true)
        );

        return memberBackground;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberBackground> memberBackgroundList = list(
                new BaseWrapper<MemberBackground>()
                .eq(MemberBackground.MEMBER_ID, memberId)
                .eq(MemberBackground.SYSTEM_STATUS, true)
        );
        
        if (memberBackgroundList != null && memberBackgroundList.size() > 0) {
            memberBackgroundList.stream()
                                .forEach(memberBackground -> delete(memberBackground.getMemberBackgroundId(), systemRequestUserId, memberBackground.getSystemVersion()));
        }
    }

}