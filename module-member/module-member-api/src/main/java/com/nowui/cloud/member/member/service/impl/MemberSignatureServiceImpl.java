package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.mapper.MemberSignatureMapper;
import com.nowui.cloud.member.member.repository.MemberSignatureRepository;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.member.member.view.MemberSignatureView;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 会员签名业务实现
 *
 * @author marcus
 * <p>
 * 2018-01-14
 */
@Service
public class MemberSignatureServiceImpl extends BaseServiceImpl<MemberSignatureMapper, MemberSignature, MemberSignatureRepository, MemberSignatureView> implements MemberSignatureService {

    @Override
    public MemberSignature findByMemberId(String memberId) {

        MemberSignature memberSignature = find(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignatureView.MEMBER_ID, memberId)
                        .eq(MemberSignatureView.SYSTEM_STATUS, true)
        );

        return memberSignature;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberSignature> memberSignatureList = list(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignatureView.MEMBER_ID, memberId)
                        .eq(MemberSignatureView.SYSTEM_STATUS, true)
        );

        if (memberSignatureList != null && memberSignatureList.size() > 0) {
            memberSignatureList.stream()
                    .forEach(memberSignature -> delete(memberSignature.getMemberSignatureId(), memberSignature.getAppId(), systemRequestUserId, memberSignature.getSystemVersion()));
        }
    }

}