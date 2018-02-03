package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import com.nowui.cloud.member.member.repository.MemberPerferenceLanguageRepository;
import com.nowui.cloud.member.member.view.MemberPerferenceLanguageView;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberPerferenceLanguage;
import com.nowui.cloud.member.member.mapper.MemberPerferenceLanguageMapper;
import com.nowui.cloud.member.member.service.MemberPerferenceLanguageService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员偏好语言业务实现
 *
 * @author marcus
 *
 * 2018-01-29
 */
@Service
public class MemberPerferenceLanguageServiceImpl extends SuperServiceImpl<MemberPerferenceLanguageMapper, MemberPerferenceLanguage,MemberPerferenceLanguageRepository,MemberPerferenceLanguageView> implements MemberPerferenceLanguageService {

    @Override
    public MemberPerferenceLanguage findByMemberId(String memberId) {
        MemberPerferenceLanguage memberPerferenceLanguage = find(
                new BaseWrapper<MemberPerferenceLanguage>()
                        .eq(MemberPerferenceLanguage.MEMBER_ID, memberId)
                        .eq(MemberPerferenceLanguage.SYSTEM_STATUS, true)
        );
        return memberPerferenceLanguage;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberPerferenceLanguage> memberPerferenceLanguageList = list(
                new BaseWrapper<MemberPerferenceLanguage>()
                        .eq(MemberPerferenceLanguage.MEMBER_ID, memberId)
                        .eq(MemberPerferenceLanguage.SYSTEM_STATUS, true)
        );
        
        if (memberPerferenceLanguageList != null && memberPerferenceLanguageList.size() > 0) {
            memberPerferenceLanguageList.stream()
                                .forEach(memberPerferenceLanguage -> delete(memberPerferenceLanguage.getMemberPreferenceLanguageId(), systemRequestUserId, memberPerferenceLanguage.getSystemVersion()));
        }
        
    }


}