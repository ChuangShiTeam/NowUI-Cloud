package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberAccount;
import com.nowui.cloud.member.member.mapper.MemberAccountMapper;
import com.nowui.cloud.member.member.repository.MemberAccountRepository;
import com.nowui.cloud.member.member.service.MemberAccountService;
import com.nowui.cloud.member.member.view.MemberAccountView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员账号业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberAccountServiceImpl extends BaseServiceImpl<MemberAccountMapper, MemberAccount, MemberAccountRepository, MemberAccountView> implements MemberAccountService {

    @Override
    public MemberAccount findByMemberId(String memberId) {
        MemberAccount memberAccount = find(
                new BaseWrapper<MemberAccount>()
                        .eq(MemberAccountView.MEMBER_ID, memberId)
                        .eq(MemberAccountView.SYSTEM_STATUS, true)
        );
        return memberAccount;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberAccount> memberAccountList = list(
                new BaseWrapper<MemberAccount>()
                        .eq(MemberAccountView.MEMBER_ID, memberId)
                        .eq(MemberAccountView.SYSTEM_STATUS, true)
        );

        if (memberAccountList != null && memberAccountList.size() > 0) {
            memberAccountList.stream().forEach(memberAccount -> delete(memberAccount.getMemberAccountId(), memberAccount.getAppId(), systemRequestUserId, memberAccount.getSystemVersion()));
        }
        
    }

}