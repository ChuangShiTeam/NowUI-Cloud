package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberPreferenceLanguage;
import com.nowui.cloud.member.member.mapper.MemberPreferenceLanguageMapper;
import com.nowui.cloud.member.member.repository.MemberPreferenceLanguageRepository;
import com.nowui.cloud.member.member.service.MemberPreferenceLanguageService;
import com.nowui.cloud.member.member.view.MemberPreferenceLanguageView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员偏好语言业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-29
 */
@Service
public class MemberPreferenceLanguageServiceImpl extends BaseServiceImpl<MemberPreferenceLanguageMapper, MemberPreferenceLanguage, MemberPreferenceLanguageRepository, MemberPreferenceLanguageView> implements MemberPreferenceLanguageService {

    @Override
    public MemberPreferenceLanguage findByMemberId(String memberId) {
        MemberPreferenceLanguage memberPreferenceLanguage = find(
                new BaseWrapper<MemberPreferenceLanguage>()
                        .eq(MemberPreferenceLanguageView.MEMBER_ID, memberId)
                        .eq(MemberPreferenceLanguageView.SYSTEM_STATUS, true)
        );
        return memberPreferenceLanguage;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberPreferenceLanguage> memberPreferenceLanguageList = list(
                new BaseWrapper<MemberPreferenceLanguage>()
                        .eq(MemberPreferenceLanguageView.MEMBER_ID, memberId)
                        .eq(MemberPreferenceLanguageView.SYSTEM_STATUS, true)
        );

        if (memberPreferenceLanguageList != null && memberPreferenceLanguageList.size() > 0) {
            memberPreferenceLanguageList.stream()
                    .forEach(memberPreferenceLanguage -> delete(memberPreferenceLanguage.getMemberPreferenceLanguageId(), memberPreferenceLanguage.getAppId(), systemRequestUserId, memberPreferenceLanguage.getSystemVersion()));
        }
    }

}