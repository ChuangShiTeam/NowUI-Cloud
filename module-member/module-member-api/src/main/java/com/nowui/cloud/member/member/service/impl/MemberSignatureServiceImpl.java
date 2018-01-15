package com.nowui.cloud.member.member.service.impl;

import java.util.Arrays;
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
    public Integer countForAdmin(String appId, String memberId, String memberSignature) {
        Integer count = count(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignature.APP_ID, appId)
                        .likeAllowEmpty(MemberSignature.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberSignature.MEMBER_SIGNATURE, memberSignature)
                        .eq(MemberSignature.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberSignature> listForAdmin(String appId, String memberId, String memberSignature, Integer pageIndex, Integer pageSize) {
        List<MemberSignature> memberSignatureList = list(
                new BaseWrapper<MemberSignature>()
                        .eq(MemberSignature.APP_ID, appId)
                        .likeAllowEmpty(MemberSignature.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberSignature.MEMBER_SIGNATURE, memberSignature)
                        .eq(MemberSignature.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberSignature.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberSignatureList;
    }

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

}