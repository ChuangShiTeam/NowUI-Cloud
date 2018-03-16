package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberPassword;
import com.nowui.cloud.member.member.mapper.MemberPasswordMapper;
import com.nowui.cloud.member.member.repository.MemberPasswordRepository;
import com.nowui.cloud.member.member.service.MemberPasswordService;
import com.nowui.cloud.member.member.view.MemberPasswordView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员密码业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberPasswordServiceImpl extends BaseServiceImpl<MemberPasswordMapper, MemberPassword, MemberPasswordRepository, MemberPasswordView> implements MemberPasswordService {

    @Override
    public MemberPassword findByMemberId(String memberId) {
        MemberPassword memberPassword = find(
                new BaseWrapper<MemberPassword>()
                        .eq(MemberPasswordView.MEMBER_ID, memberId)
                        .eq(MemberPasswordView.SYSTEM_STATUS, true)
        );
        return memberPassword;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberPassword> memberPasswordList = list(
                new BaseWrapper<MemberPassword>()
                        .eq(MemberPasswordView.MEMBER_ID, memberId)
                        .eq(MemberPasswordView.SYSTEM_STATUS, true)
        );

        if (memberPasswordList != null && memberPasswordList.size() > 0) {
            memberPasswordList.stream().forEach(memberPassword -> delete(memberPassword.getMemberPasswordId(), memberPassword.getAppId(), systemRequestUserId, memberPassword.getSystemVersion()));
        }
        
    }

}