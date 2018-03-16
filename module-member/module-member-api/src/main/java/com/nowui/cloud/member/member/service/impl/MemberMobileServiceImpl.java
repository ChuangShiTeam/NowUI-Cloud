package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberMobile;
import com.nowui.cloud.member.member.mapper.MemberMobileMapper;
import com.nowui.cloud.member.member.repository.MemberMobileRepository;
import com.nowui.cloud.member.member.service.MemberMobileService;
import com.nowui.cloud.member.member.view.MemberMobileView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员手机号码业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberMobileServiceImpl extends BaseServiceImpl<MemberMobileMapper, MemberMobile, MemberMobileRepository, MemberMobileView> implements MemberMobileService {

    @Override
    public MemberMobile findByMemberId(String memberId) {
        MemberMobile memberMobile = find(
                new BaseWrapper<MemberMobile>()
                        .eq(MemberMobileView.MEMBER_ID, memberId)
                        .eq(MemberMobileView.SYSTEM_STATUS, true)
        );
        return memberMobile;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberMobile> memberMobileList = list(
                new BaseWrapper<MemberMobile>()
                        .eq(MemberMobileView.MEMBER_ID, memberId)
                        .eq(MemberMobileView.SYSTEM_STATUS, true)
        );

        if (memberMobileList != null && memberMobileList.size() > 0) {
            memberMobileList.stream().forEach(memberMobile -> delete(memberMobile.getMemberMobileId(), memberMobile.getAppId(), systemRequestUserId, memberMobile.getSystemVersion()));
        }
        
    }

}