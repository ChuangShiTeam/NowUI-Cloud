package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberEmail;
import com.nowui.cloud.member.member.mapper.MemberEmailMapper;
import com.nowui.cloud.member.member.repository.MemberEmailRepository;
import com.nowui.cloud.member.member.service.MemberEmailService;
import com.nowui.cloud.member.member.view.MemberEmailView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员邮箱业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberEmailServiceImpl extends BaseServiceImpl<MemberEmailMapper, MemberEmail, MemberEmailRepository, MemberEmailView> implements MemberEmailService {

    @Override
    public MemberEmail findByMemberId(String memberId) {
        MemberEmail memberEmail = find(
                new BaseWrapper<MemberEmail>()
                        .eq(MemberEmailView.MEMBER_ID, memberId)
                        .eq(MemberEmailView.SYSTEM_STATUS, true)
        );
        return memberEmail;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberEmail> memberEmailList = list(
                new BaseWrapper<MemberEmail>()
                        .eq(MemberEmailView.MEMBER_ID, memberId)
                        .eq(MemberEmailView.SYSTEM_STATUS, true)
        );

        if (memberEmailList != null && memberEmailList.size() > 0) {
            memberEmailList.stream().forEach(memberEmail -> delete(memberEmail.getMemberEmailId(), memberEmail.getAppId(), systemRequestUserId, memberEmail.getSystemVersion()));
        }
        
    }

}