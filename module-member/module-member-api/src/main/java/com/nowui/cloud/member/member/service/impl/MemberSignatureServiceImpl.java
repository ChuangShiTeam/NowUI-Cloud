package com.nowui.cloud.member.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.mapper.MemberSignatureMapper;
import com.nowui.cloud.member.member.service.MemberSignatureService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 会员签名业务实现
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Service
public class MemberSignatureServiceImpl extends BaseServiceImpl<MemberSignatureMapper, MemberSignature> implements MemberSignatureService {

    @Override
    public MemberSignature findByMemberId(String memberId) {
        
        if (Util.isNullOrEmpty(memberId)) {
            return null;
        }
        
        MemberSignature memberSignature = find(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignature.MEMBER_ID, memberId)
                        .eq(MemberSignature.SYSTEM_STATUS, true)
        );
        
        return memberSignature;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberSignature> memberSignatureList = list(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignature.MEMBER_ID, memberId)
                        .eq(MemberSignature.SYSTEM_STATUS, true)
        );
        
        if (memberSignatureList != null && memberSignatureList.size() > 0) {
            memberSignatureList.stream()
                                .forEach(memberSignature -> delete(memberSignature.getMemberSignatureId(), systemRequestUserId, memberSignature.getSystemVersion()));
        }
    }

}